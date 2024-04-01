package com.gp.main.ui.daily.music

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnScrollChangeListener
import android.widget.MediaController.MediaPlayerControl
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import com.gp.common.model.MusicDaily
import com.gp.common.model.PersonDaily
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.LogUtil
import com.gp.framework.utils.MediaHelper
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.main.R
import com.gp.main.databinding.ActivityDailyMusicBinding
import com.gp.main.ui.daily.music.fragment.MusicCoverFragment
import com.gp.main.ui.daily.music.fragment.MusicLrcFragment
import com.gp.main.ui.daily.viewmodel.DailyViewModel
import com.gp.main.utils.ConvertUtils
import java.io.File
import java.util.Timer
import java.util.TimerTask

class DailyMusicActivity : BaseMvvmActivity<ActivityDailyMusicBinding, DailyViewModel>() {

    companion object{
        const val PLAY_STATUS_PLAYING = 0
        const val PLAY_STATUS_PAUSE = 1
        const val PLAY_STATUS_NORMAL = 2
    }

    private var playStatus = PLAY_STATUS_NORMAL


    // 专辑封面 Fragment
    private val mCoverFragment by lazy { MusicCoverFragment() }

    // 歌词 Fragment
    private val mLrcFragment by lazy { MusicLrcFragment() }

    // 记录当前的 Fragment
    private var mCurrentView: Fragment = mCoverFragment

    // 记录当前显示的是“专辑封面”还是“歌词”
    private var showCover = false

    // 是否正在拖动进度条，是的情况下不会根据事件更新进度条
    private var isSeeking = false

    // 当前播放歌曲的引用
    private var mCurrentPlay: MusicDaily? = null
    // 播放列表
    private var musicList: MutableList<MusicDaily>? = null

    private var playIndex : Int = 0

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarSettingHelper.setStatusBarTranslucent(this)
        // 初始的专辑封面
        supportFragmentManager.beginTransaction()
            .add(R.id.mCoverLrcView, mCoverFragment).commit()
        mCurrentView = mCoverFragment
        showCover = true


        bindView()
        // 初始化监听事件
        initListener()

    }

    override fun initData() {
        mViewModel.getMusicDailyData().observe(this) {
            musicList = mutableListOf()
            it?.forEach {m ->
                musicList?.add(m!!)
            }
            playMusic()
        }
    }

    private fun playMusic() {
        mBinding.mProcessBar.progress = 0
        mCurrentPlay = musicList?.get(playIndex)
        MediaHelper.playInternetSource(mCurrentPlay?.url)
        Handler().postDelayed({
            mBinding.mTvTotalTime.text = ConvertUtils.getTimeWithProcess(MediaHelper.duration)
            mBinding.mProcessBar.max = MediaHelper.duration
        }, 1000)
        playStatus = PLAY_STATUS_PLAYING
        mBinding.mTvMusicTitle.text = mCurrentPlay?.name
        mBinding.mTvArtist.text = "未知歌手"
        mBinding.mIvPlay.setImageResource(R.mipmap.ic_pause_main)
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                if(!isSeeking) {
                    runOnUiThread {
                        mBinding.mProcessBar.progress = MediaHelper.getCurrentPosition() ?: 0
                        mBinding.mTvCurrentTime.text = ConvertUtils.getTimeWithProcess(MediaHelper.getCurrentPosition() ?: 0)
                    }
                }
            }
        },0,50)

        val bundle = Bundle()
        bundle.putString("lyric", mCurrentPlay?.lyric)
        mLrcFragment.arguments = bundle

        mCoverFragment.startAnim()
    }


    private fun playPrevious() {
        if(playIndex - 1 >= 0) {
            playIndex--
        } else {
            playIndex = musicList?.size?.minus(1) ?: 0
        }
        playMusic()
    }

    private fun playNext() {
        if(playIndex + 1 < (musicList?.size ?: 0)) {
            playIndex++
        } else {
            playIndex = 0
        }

        playMusic()
    }

    private fun pause() {
        playStatus = PLAY_STATUS_PAUSE
        MediaHelper.pause()
        mCoverFragment.pause()
    }

    private fun toContinue() {
        playStatus = PLAY_STATUS_PLAYING
        MediaHelper.toContinue()
        mCoverFragment.startAnim()
    }


    /**
     * 初始化各控件事件监听
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun initListener() {
        // 播放/暂停
        mBinding.mIvPlay.onClick {
            if(playStatus == PLAY_STATUS_PAUSE) {
                mBinding.mIvPlay.setImageResource(R.mipmap.ic_pause_main)
                toContinue()
            } else if (playStatus == PLAY_STATUS_PLAYING) {
                mBinding.mIvPlay.setImageResource(R.mipmap.ic_play_w)
                pause()
            }
        }


        // 上一首/下一首
        mBinding.mIvPre.onClick {
            playPrevious()
        }

        mBinding.mIvNext.onClick {
            playNext()
        }


        // 进度条拖动事件
        mBinding.mProcessBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                isSeeking = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                isSeeking = false
                // 拖动结束时，发送进度更新指令
                MediaHelper.seekTo(mBinding.mProcessBar.progress)
                mBinding.mTvCurrentTime.text = ConvertUtils.getTimeWithProcess(MediaHelper.getCurrentPosition()
                    ?.div(1000) ?: 0)

            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mBinding.mTvCurrentTime.text =
                        ConvertUtils.getTimeWithProcess(progress)
                } else {
                    val position = MediaHelper.getCurrentPosition()
                    Log.d("gxy", "播放进度：${position}")
                    if (position != null) {
                        mBinding.mTvCurrentTime.text = ConvertUtils.getTimeWithProcess(position/1000)
                    }
                    val duration = MediaHelper.getDurationString()
                    mBinding.mTvTotalTime.text = duration
                    Log.d("gxy","total：${duration}")


                }
            }

        })


        // 返回前一页页面
        mBinding.mIvBack.onClick {
            finish()
        }

        mBinding.changeFrame.onClick {
            changeView()
        }

        mBinding.mCoverLrcView.setOnTouchListener { _, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    x1 = event.x
                    y1 = event.y
                }
                MotionEvent.ACTION_MOVE -> {

                }

                MotionEvent.ACTION_UP -> {
                    x2 = event.x
                    y2 = event.y

                    if(Math.abs(y2 - y1) < 10) {
                        changeView()
                    }
                    if(Math.abs(y2 - y1) > 100) {
                        false
                    }
                }
            }
            true
        }
    }

    private var x1 : Float = 0f
    private var y1 : Float = 0f
    private var x2 : Float = 0f
    private var y2 : Float = 0f



    /**
     * 绑定当前播放歌曲数据，更新界面
     */
    private fun bindView() {
        // 设置标题和歌手名（必不为空）
        val split = mCurrentPlay?.name?.split("-")
        mBinding.mTvMusicTitle.text = split?.get(0)
        mBinding.mTvArtist.text = split?.get(1)

        // 恢复进度条,背景,播放状态
//        mBinding.mProcessBar.max = 0
//        mBinding.mProcessBar.progress = 0
//        mBinding.mTvCurrentTime.text = getString(R.string.time_zero)
//        mBinding.mTvTotalTime.text = getString(R.string.time_zero)
        mBinding.mIvBackGround.setImageResource(R.drawable.default_play_bg)
        mBinding.mIvPlay.setImageResource(R.mipmap.ic_play_w)

        if (playStatus == PLAY_STATUS_PLAYING) {
            mBinding.mIvPlay.setImageResource(R.mipmap.ic_pause_main)
        } else {
            mBinding.mIvPlay.setImageResource(R.mipmap.ic_play_w)
        }

    }


    /**
     * 封面/歌词页面切换,由mLrcFragment，mCoverFragment 根据手势调用
     */

    private fun changeView() {
        if (showCover) {
            showCover = false
            switchFragment(mLrcFragment)
        } else {
            showCover = true
            switchFragment(mCoverFragment)
        }
    }

    /**
     * Fragment 跳转
     */
    private fun switchFragment(fragment: Fragment) {
        if (fragment != mCurrentView) {
            if (fragment.isAdded) {
                supportFragmentManager.beginTransaction()
                    .hide(mCurrentView).show(fragment).commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .hide(mCurrentView).add(R.id.mCoverLrcView, fragment).commit()
            }
            mCurrentView = fragment
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        MediaHelper.releasePlayer()
    }

}