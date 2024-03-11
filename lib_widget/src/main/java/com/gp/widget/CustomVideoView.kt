package com.gp.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView


class CustomVideoView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : VideoView(context,attributeSet, defStyle)
 {

     override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
         val width = getDefaultSize(0, widthMeasureSpec)
         val height = getDefaultSize(0, heightMeasureSpec)
         setMeasuredDimension(width, height)
     }
}