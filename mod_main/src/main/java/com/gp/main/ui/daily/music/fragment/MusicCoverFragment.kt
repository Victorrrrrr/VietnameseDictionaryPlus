package com.gp.main.ui.daily.music.fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.gp.framework.base.BaseMvvmFragment
import com.gp.main.R
import com.gp.main.databinding.FragmentMusicCoverBinding
import com.gp.main.ui.daily.viewmodel.DailyViewModel


class MusicCoverFragment : BaseMvvmFragment<FragmentMusicCoverBinding, DailyViewModel>() {

    private var discObjectAnimator: ObjectAnimator? = null
    private var neddleObjectAnimator: ObjectAnimator? = null



    fun startAnim() {
        discObjectAnimator!!.start()
        neddleObjectAnimator!!.start()
    }

    fun pause() {
        discObjectAnimator!!.cancel()
        neddleObjectAnimator!!.reverse()
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        //最外部的半透明边线
        val ovalShape0 = OvalShape()
        val drawable0 = ShapeDrawable(ovalShape0)
        drawable0.paint.color = 0x10000000
        drawable0.paint.style = Paint.Style.FILL
        drawable0.paint.isAntiAlias = true

        //黑色唱片边框
        val drawable1 = RoundedBitmapDrawableFactory.create(
            resources, BitmapFactory.decodeResource(
                resources, R.drawable.cover_bg
            )
        )
        drawable1.isCircular = true
        drawable1.setAntiAlias(true)

        //内层黑色边线
        val ovalShape2 = OvalShape()
        val drawable2 = ShapeDrawable(ovalShape2)
        drawable2.paint.color = Color.BLACK
        drawable2.paint.style = Paint.Style.FILL
        drawable2.paint.isAntiAlias = true

        //最里面的图像
        val drawable3 = RoundedBitmapDrawableFactory.create(
            resources, BitmapFactory.decodeResource(
                resources, R.drawable.daily_person_default
            )
        )
        drawable3.isCircular = true
        drawable3.setAntiAlias(true)
        val layers = arrayOfNulls<Drawable>(4)
        layers[0] = drawable0
        layers[1] = drawable1
        layers[2] = drawable2
        layers[3] = drawable3
        val layerDrawable = LayerDrawable(layers)
        val width = 10
        //针对每一个图层进行填充，使得各个圆环之间相互有间隔，否则就重合成一个了。
        //layerDrawable.setLayerInset(0, width, width, width, width);
        layerDrawable.setLayerInset(1, width, width, width, width)
        layerDrawable.setLayerInset(2, width * 11, width * 11, width * 11, width * 11)
        layerDrawable.setLayerInset(3, width * 12, width * 12, width * 12, width * 12)
        val discView = mBinding?.ivCover
        discView?.setBackgroundDrawable(layerDrawable)
        val needleImage = mBinding?.ivCoverL

        discObjectAnimator = ObjectAnimator.ofFloat(discView, "rotation", 0f, 360f)
        discObjectAnimator?.setDuration(20000)
        //使ObjectAnimator动画匀速平滑旋转
        discObjectAnimator?.setInterpolator(LinearInterpolator())
        //无限循环旋转
        discObjectAnimator?.setRepeatCount(ValueAnimator.INFINITE)
        discObjectAnimator?.setRepeatMode(ValueAnimator.RESTART)
        neddleObjectAnimator = ObjectAnimator.ofFloat(needleImage, "rotation", 0f, 25f)
        needleImage?.pivotX = 0f
        needleImage?.pivotY = 0f
        neddleObjectAnimator?.setDuration(800)
        neddleObjectAnimator?.setInterpolator(LinearInterpolator())

    }




}