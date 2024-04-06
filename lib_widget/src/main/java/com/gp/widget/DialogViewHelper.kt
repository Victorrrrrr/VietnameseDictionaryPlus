package com.gp.widget

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.lang.ref.WeakReference


class DialogViewHelper() {
    var contentView: View? = null
    private val mViews: SparseArray<WeakReference<View>>

    constructor(context: Context?, layoutResId: Int) : this() {
        contentView = LayoutInflater.from(context).inflate(layoutResId, null)
    }

    init {
        mViews = SparseArray()
    }

    fun <T : View?> setText(viewId: Int, text: CharSequence?) {
        val tv = getView<TextView>(viewId)
        if (tv != null) {
            tv.text = text
        }
    }


    fun <T: EditText?> setTextHint(viewId: Int, text: CharSequence?) {
        val et = getView<EditText>(viewId)
        if(et != null) {
            et.hint = text
        }
    }

    fun <T : View?> getView(viewId: Int): T? {
        val weakReference = mViews[viewId]
        var view: View? = null
        if (weakReference != null) {
            view = weakReference.get()
        }
        if (view == null) {
            view = contentView!!.findViewById(viewId)
            if (view != null) {
                mViews.put(viewId, WeakReference(view))
            }
        }
        return view as T?
    }

    fun setOnClickListener(viewId: Int, onClickListener: View.OnClickListener?) {
        val view = getView<View>(viewId)
        view?.setOnClickListener(onClickListener)
    }

    fun setIcon(viewId: Int, resId: Int) {
        val iv = getView<ImageView>(viewId)
        iv?.setImageResource(resId)
    }
}