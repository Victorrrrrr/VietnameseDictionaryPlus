package com.gp.learn.tool.voice_trans

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.gp.framework.utils.PcmToWavUtil
import org.jtransforms.fft.DoubleFFT_1D
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date


class AudioChannel(private val sampleRate: Int, channels: Int, var context: Context) {
    private val channelConfig: Int
    private val minBufferSize: Int
    private val buffer: ByteArray
    private var recordThread: Thread? = null
    private var audioRecord: AudioRecord? = null
    private var isRecoding = false
    private val sdf: SimpleDateFormat
    var filename: String? = null
    var startTime: Long = 0
    private var onResult: onResult? = null
    private val fft: DoubleFFT_1D

    init {
        channelConfig =
            if (channels == 2) AudioFormat.CHANNEL_IN_STEREO else AudioFormat.CHANNEL_IN_MONO
        minBufferSize =
            AudioRecord.getMinBufferSize(sampleRate, channelConfig, AudioFormat.ENCODING_PCM_16BIT)
        Log.i("AudioChannel", "minBufferSize: $minBufferSize")
        buffer = ByteArray(minBufferSize)
        sdf = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss")
        fft = DoubleFFT_1D((minBufferSize / 2).toLong())
    }

    private fun calculateRMS(audioBuffer: ByteArray): Double {
        var sum = 0.0
        for (sample in audioBuffer) {
            sum += (sample * sample).toDouble()
        }
        return Math.sqrt(sum / audioBuffer.size)
    }

    // 将RMS值转换为分贝值的方法
    private fun rmsToDB(rms: Double): Double {
        // 假设参考值为1（通常是最小可听声音的RMS值）
        val reference = 1.0
        return 20 * Math.log10(rms / reference)
    }

    private fun calculateHZ(buffer: ByteArray): Double {
        // Convert byte array to double array for FFT
        val fftBuffer = DoubleArray(buffer.size / 2)
        run {
            var i = 0
            while (i < buffer.size) {
                val sample = (buffer[i].toInt() shl 8 or (buffer[i + 1]
                    .toInt() and 0xFF)).toShort()
                fftBuffer[i / 2] = sample.toDouble()
                i += 2
            }
        }

        // 执行 FFT
        fft.realForward(fftBuffer)
        var maxAmplitude = 0.0
        var pitchIndex = 0
        for (i in 0 until fftBuffer.size - 1) {
            val amplitude = fftBuffer[i] * fftBuffer[i] + fftBuffer[i + 1] * fftBuffer[i + 1]
            if (i < fftBuffer.size / 2 && amplitude > maxAmplitude) {
                maxAmplitude = amplitude
                pitchIndex = i
            }
        }

        //计算hz，不过偏差比较大
        val frequency = pitchIndex.toDouble() * sampleRate / (fftBuffer.size / 2)
        return frequency / 100
    }

    fun initPremission() {
        ActivityCompat.requestPermissions(
            (context as Activity),
            arrayOf(Manifest.permission.RECORD_AUDIO),
            169
        )
    }

    fun initThread() {
        recordThread = object : Thread() {
            //开线程
            @SuppressLint("MissingPermission")
            override fun run() {
                audioRecord = AudioRecord(
                    MediaRecorder.AudioSource.MIC,
                    sampleRate, channelConfig, AudioFormat.ENCODING_PCM_16BIT, minBufferSize
                )
                audioRecord!!.startRecording()
                var writer: FileOutputStream? = null
                val current = Date()
                val time = sdf.format(current)
                val audioBuffer = ByteArray(minBufferSize) // 创建一个缓冲区
                try {
                    filename = context.cacheDir.toString() + "/" + time + ".pcm" //cache目录不需要权限
                    writer = FileOutputStream(filename, true)
                    while (!currentThread().isInterrupted && isRecoding) { //如果线程没有Interrupted而且isRecording变量为True代表在录制状态的情况
                        if (audioRecord!!.recordingState == AudioRecord.RECORDSTATE_RECORDING) {
                            audioRecord!!.read(audioBuffer, 0, minBufferSize) // 读取音频数据到缓冲区
                            val rms = calculateRMS(audioBuffer)
                            val db = rmsToDB(rms) //db的值
                            val hz = calculateHZ(audioBuffer)
                            val seaconds = (System.currentTimeMillis() - startTime).toInt() / 1000
                            if (isRecoding) {
                                (context as Activity).runOnUiThread {
                                    onResult!!.update(seaconds, db, hz) //如果还在线程运行状态把信息回调出来
                                }
                            }
                            writer.write(audioBuffer)
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    audioRecord!!.stop()
                    audioRecord!!.release()
                    audioRecord = null
                    try {
                        writer!!.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                PcmToWavUtil(
                    44100,
                    AudioFormat.CHANNEL_IN_STEREO,
                    2,
                    AudioFormat.ENCODING_PCM_16BIT
                ).pcmToWav(filename, filename!!.replace("pcm", "wav"))
            }
        }
    }

    fun startLive() { //录制
        initThread()
        initPremission()
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            isRecoding = true
            recordThread!!.start()
            startTime = System.currentTimeMillis()
        } else {
            Toast.makeText(context, "没有录音权限", Toast.LENGTH_LONG).show()
        }
    }

    fun stopLive(mode: Int) { //mode为-1时代表取消，为0代表取消
        if (!isRecoding) return
        try {
            isRecoding = false
            recordThread!!.join()
        } catch (e: Exception) {
            isRecoding = false
            e.printStackTrace()
        }
        File(filename).delete()
        when (mode) {
            0 -> onResult!!.finish(filename!!.replace("pcm", "wav")) //正常结束后pcm会被转换成wav
            -1 -> {
                File(filename!!.replace("pcm", "wav")).delete()
                onResult!!.cancel() //取消回调
            }
        }
    }

    fun onResult(onResult: onResult?) { //功能点击
        this.onResult = onResult
    }
}


interface onResult {
    //三个回调
    fun update(seaconds: Int, db: Double, hz: Double)
    fun finish(filename: String)
    fun cancel()
}