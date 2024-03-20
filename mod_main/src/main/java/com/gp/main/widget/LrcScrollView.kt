package com.gp.main.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView


class LrcScrollView(context:Context, attributeSet: AttributeSet) : ScrollView(context, attributeSet) {

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        when(ev?.action) {
//            MotionEvent.ACTION_SCROLL -> {
//                parent.requestDisallowInterceptTouchEvent(true)
//            }
//            else -> {
//                parent.requestDisallowInterceptTouchEvent(false)
//            }
//        }
        return super.dispatchTouchEvent(ev)

    }
}