package com.gp.framework.utils

import android.content.Context
import android.util.TypedValue
import androidx.annotation.Dimension
import com.gp.framework.helper.VDHelper

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/2/13:13:20.
 * @Desrciption: 描述
 */
object SizeUtils {
    fun dpToPx(dpValue: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dpValue,
            VDHelper.getApplication().resources.displayMetrics
        )
    }


    fun dpToPx(dpValue: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(),
            VDHelper.getApplication().resources.displayMetrics
        ).toInt()
    }

    fun dpToPx(context: Context, @Dimension(unit = Dimension.DP) dp: Int): Float {
        val r = context.resources
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics)
    }


    /**
     * 所有字体均使用dp
     */
    fun spToPx(spValue: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            spValue,
            VDHelper.getApplication().resources.displayMetrics
        )
    }


}