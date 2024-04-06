package com.gp.framework.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object SoftInputUtil {
    fun showSoftInput(view: View?) {
        if (view == null) return
        val inputMethodManager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, 0)
    }

    fun hideSoftInput(view: View?) {
        if (view == null) return
        val inputMethodManager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
