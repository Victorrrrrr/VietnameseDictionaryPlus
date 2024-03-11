package com.gp.learn.utils

import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import com.gp.framework.helper.VDHelper
import com.gp.common.constant.YOU_DAO_VOICE_EN
import com.gp.common.constant.YOU_DAO_VOICE_USA


object MediaHelper {
    var mediaPlayer: MediaPlayer? = null

    // 英文发音
    const val ENGLISH_VOICE = 1

    // 美国发音
    const val AMERICA_VOICE = 0

    // 默认
    const val DEFAULT_VOICE = ENGLISH_VOICE
    private const val TAG = "MediaHelper"
    fun play(type: Int, wordName: String) {
        if (mediaPlayer != null) {
            releasePlayer()
            mediaPlayer = MediaPlayer()
        } else mediaPlayer = MediaPlayer()
        try {
            if (ENGLISH_VOICE == type)
                mediaPlayer!!.setDataSource(YOU_DAO_VOICE_EN + wordName)
            else
                mediaPlayer!!.setDataSource(YOU_DAO_VOICE_USA + wordName)
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

    fun play(wordName: String) {
        play(DEFAULT_VOICE, wordName)
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

