package com.gp.framework.utils

import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import com.gp.framework.helper.VDHelper


object MediaHelper {
    var mediaPlayer: MediaPlayer? = null

    private const val TAG = "MediaHelper"
    fun play(wordVoiceUrl: String) {
        if (mediaPlayer != null) {
            releasePlayer()
            mediaPlayer = MediaPlayer()
        } else mediaPlayer = MediaPlayer()
        try {
            mediaPlayer!!.setDataSource(wordVoiceUrl)
            mediaPlayer!!.prepareAsync()
            mediaPlayer!!.setOnPreparedListener { mediaPlayer!!.start() }
            mediaPlayer!!.setOnCompletionListener {
                if (mediaPlayer != null) {
                    mediaPlayer!!.release()
                    mediaPlayer = null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun pause() {
        mediaPlayer?.pause()
    }

    fun toContinue() {
        mediaPlayer?.start()
    }


    fun getDuration() : String {
        val millSecond =  mediaPlayer?.duration
        val minute = millSecond?.div(1000*60) ?: 0
        val second = millSecond?.rem((1000*60)) ?: 0
        var minuteStr = ""
        var secondStr = ""
        if(minute < 10) {
            minuteStr = "0${minute}"
        }
        if(second < 10) {
            secondStr = "0${second}"
        }
        return "$minuteStr : $secondStr"
    }


    fun playInternetSource(address: String?) {
        if (mediaPlayer != null) {
            releasePlayer()
            mediaPlayer = MediaPlayer()
        } else mediaPlayer = MediaPlayer()
        try {
            mediaPlayer!!.setDataSource(address)
            mediaPlayer!!.prepareAsync()
            mediaPlayer!!.setOnPreparedListener { mediaPlayer!!.start() }
            mediaPlayer!!.setOnCompletionListener {
                if (mediaPlayer != null) {
                    mediaPlayer!!.release()
                    mediaPlayer = null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun releasePlayer() {
        if (mediaPlayer != null) {
            if (mediaPlayer!!.isPlaying) {
                mediaPlayer!!.stop()
            }
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }

    fun playLocalFile(sourceAddress: Int) {
        if (mediaPlayer != null) {
            releasePlayer()
            mediaPlayer = MediaPlayer()
        } else mediaPlayer = MediaPlayer()
        try {
            val fileDescriptor: AssetFileDescriptor =
                VDHelper.getApplication().getResources().openRawResourceFd(sourceAddress)
            mediaPlayer!!.setDataSource(
                fileDescriptor.fileDescriptor,
                fileDescriptor.startOffset,
                fileDescriptor.length
            )
            mediaPlayer!!.prepareAsync()
            mediaPlayer!!.setOnPreparedListener { mediaPlayer!!.start() }
            mediaPlayer!!.setOnCompletionListener {
                if (mediaPlayer != null) {
                    mediaPlayer!!.release()
                    mediaPlayer = null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playLocalFileRepeat(sourceAddress: Int) {
        if (mediaPlayer != null) {
            releasePlayer()
            mediaPlayer = MediaPlayer()
        } else mediaPlayer = MediaPlayer()
        try {
            val fileDescriptor: AssetFileDescriptor =
                VDHelper.getApplication().getResources().openRawResourceFd(sourceAddress)
            mediaPlayer!!.setDataSource(
                fileDescriptor.fileDescriptor,
                fileDescriptor.startOffset,
                fileDescriptor.length
            )
            mediaPlayer!!.prepareAsync()
            mediaPlayer!!.setOnPreparedListener { mediaPlayer!!.start() }
            mediaPlayer!!.setOnCompletionListener {
                playLocalFileRepeat(
                    sourceAddress
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

