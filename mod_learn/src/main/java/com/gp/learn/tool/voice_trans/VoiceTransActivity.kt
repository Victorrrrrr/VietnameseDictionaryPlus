package com.gp.learn.tool.voice_trans

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View.OnTouchListener
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.LEARN_ACTIVITY_VOICE
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.Base64Utils.encode
import com.gp.framework.utils.LogUtil
import com.gp.framework.utils.PermissionUtils
import com.gp.mod_learn.databinding.ActivityVoiceTransBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException


@Route(path = LEARN_ACTIVITY_VOICE)
class VoiceTransActivity : BaseMvvmActivity<ActivityVoiceTransBinding, VoiceTransViewModel>() {

    var startX = 0f
    var audioChannel: AudioChannel? = null


    override fun initView(savedInstanceState: Bundle?) {
        initAudioChannel()
        initEvent()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initEvent() {
        mBinding.btnStart.setOnTouchListener((OnTouchListener { view, event ->
            if(!hasPermission(PermissionUtils.RECORD_AUDIO)) {
                requestPermission(PermissionUtils.RECORD_AUDIO)
                return@OnTouchListener false
            }

            try {
                val endX: Float
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startX = event.x
                        audioChannel!!.startLive()
                    }

                    MotionEvent.ACTION_UP -> {
                        endX = event.x
                        if (startX - endX > 100) {   // 判断手势是否为右滑
                            audioChannel!!.stopLive(-1) //取消
                            return@OnTouchListener false
                        }
                        audioChannel!!.stopLive(0) //正常结束
                    }
                }
            } catch (e: Exception) {
                audioChannel!!.stopLive(-1)
                TipsToast.showTips("已取消")
                e.printStackTrace()
            }
            true
        }))
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionUtils.REQUEST_RECORD_AUDIO -> {
//                if(grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    TipsToast.showTips("您拒绝了录音文件权限，无法打开相册，抱歉。")
//                    return
//                }
                // 录音权限完成后的操作
            }

            else -> {}
        }
    }


    private fun initAudioChannel() {
        audioChannel = AudioChannel(48000, 2, this)
        audioChannel!!.onResult(object : onResult {
            override fun update(seaconds: Int, db: Double, hz: Double) {
                LogUtil.e(
                   "录制信息: " +
                            "时长=" + seaconds + "秒, " +
                            "分贝=" + db + "db, " +
                            "频率=" + hz + "HZ"
                )
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun finish(filename: String) {
                LogUtil.e( "保存路径: $filename")
                val file = File(filename)
                val bytes = ByteArray(file.length().toInt())
                var fis: FileInputStream? = null
                try {
                    fis = FileInputStream(file)
                    fis.read(bytes)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                val base64 = encode(bytes)

                LogUtil.e("Base64的值为:" + base64.toString())

                mViewModel.sendVoiceTranslate(
                    "zh",
                    "vie",
                    base64.toString(),
                    "wav"
                ).observe(this@VoiceTransActivity) {
                    mBinding.tvPath.text = "${it?.voiceTranslateResult?.source}   ${it?.voiceTranslateResult?.target}"

                    LogUtil.d(it.toString())
                }

                file.delete()
            }

            override fun cancel() {
                LogUtil.e("已取消")
            }
        })
    }

    //将图片文件或音频文件转化为字节数组字符串，并对其进行Base64编码处理
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun fileToBase64(filepath: String?): String? {
//        var `in`: InputStream? = null
//        var data: ByteArray? = null
//        //读取文件字节数组
//        try {
//            `in` = FileInputStream(filepath)
//            data = ByteArray(`in`.available())
//            `in`.read(data)
//            `in`.close()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        //对字节数组Base64编码
//        val base64 = Base64()
//        return base64.encodeToString(data)
//    }



    override fun onDestroy() {
        super.onDestroy()
        audioChannel!!.stopLive(-1)
    }
}