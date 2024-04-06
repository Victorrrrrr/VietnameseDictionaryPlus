package com.gp.main.widget

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.widget.ScrollView


class LrcScrollView(context:Context, attributeSet: AttributeSet) : ScrollView(context, attributeSet) {





//    private var y1 : Float = 0f
//    private var y2 : Float = 0f
//    private var x1 : Float = 0f
//    private var x2 : Float = 0f
//
//    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//        when(ev?.action) {
//            MotionEvent.ACTION_DOWN -> {
//                x1 = ev.x
//                y1 = ev.y
//            }
//            MotionEvent.ACTION_MOVE -> {
////                x1 = ev.x
////                y1 = ev.y
//            }
//            MotionEvent.ACTION_UP -> {
//                x2 = ev.x
//                y2 = ev.y
//                if(Math.abs(y1 - y2) < 10) {
//                    return false
//                }
//                if(Math.abs(y1 - y2) > 60) {
//                    return true
//                }
//            }
//        }
//        return false
//    }

//    mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//        public float y1,y2,x2,x1;
//
//        @Override
//        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//            if (e.getAction() == MotionEvent.ACTION_DOWN) {
//                x1 = e.getX();
//                y1 = e.getY();
//            }
//            if (e.getAction() == MotionEvent.ACTION_MOVE) {
//                x1 = e.getX();
//                y1 = e.getY();
//            }
//            if (e.getAction() == MotionEvent.ACTION_UP) {
//                x2 = e.getX();
//                y2 = e.getY();
//                if (Math.abs(x1 - x2) < 6) {
//                    return false;// 距离较小，当作click事件来处理
//                }
//                if(Math.abs(x1 - x2) >60){  // 真正的onTouch事件
//                    return true;
//                }
//            }
//            return false;
//
//        }
//
//        @Override
//        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//        }
//
//        @Override
//        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//        }
//    });



}