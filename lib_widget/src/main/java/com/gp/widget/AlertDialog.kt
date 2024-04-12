package com.gp.widget

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.view.Gravity
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.annotation.StyleRes
import com.gp.lib_widget.R


class AlertDialog(context: Context, @StyleRes themeResId: Int) :
    Dialog(context, themeResId) {
    private val mAlert: AlertController

    init {
        mAlert = AlertController(this, window!!)
    }

    fun setText(viewId: Int, text: CharSequence?) {
        mAlert.setText(viewId, text)
    }

    fun setEditTextHint(viewId: Int, text: CharSequence?) {
        mAlert.setEditTextHint(viewId, text)
    }

    fun <T : View?> getView(viewId: Int): T? {
        return mAlert.getView<T>(viewId)
    }

    fun setOnClickListener(viewId: Int, onClickListener: View.OnClickListener?) {
        mAlert.setOnClickListener(viewId, onClickListener)
    }

    //----------------------------------------------------------------------------------------------
    class Builder @JvmOverloads constructor(context: Context?, themeResId: Int = R.style.dialog) {
        private val P: AlertController.AlertParams

        init {
            P = AlertController.AlertParams(context!!, themeResId)
        }

        /**
         * 设置对话框布局
         *
         * @param view
         * @return
         */
        fun setContentView(view: View?): Builder {
            P.mView = view
            P.mLayoutResId = 0
            return this
        }

        /**
         * @param layoutId
         * @return
         */
        fun setContentView(layoutId: Int): Builder {
            P.mView = null
            P.mLayoutResId = layoutId
            return this
        }

        /**
         * 设置文本
         *
         * @param viewId
         * @param text
         * @return
         */
        fun setText(viewId: Int, text: CharSequence?): Builder {
            P.mTextArray.put(viewId, text)
            return this
        }


        fun setEditTextHint(viewId: Int, text: CharSequence?) : Builder {
            P.mEditTextHintArray.put(viewId, text)
            return this
        }

        /**
         * 设置文本颜色
         *
         * @param viewId
         * @param color
         * @return
         */
        fun setTextColor(viewId: Int, color: Int): Builder {
            P.mTextColorArray.put(viewId, color)
            return this
        }

        /**
         * 设置图标
         *
         * @param iconId
         * @return
         */
        fun setIcon(iconId: Int, resId: Int): Builder {
            P.mIconArray.put(iconId, resId)
            return this
        }

        /**
         * 设置图片
         *
         * @param viewId
         * @return
         */
        fun setBitmap(viewId: Int, bitmap: Bitmap?): Builder {
            P.mBitmapArray.put(viewId, bitmap)
            return this
        }

        /**
         * 设置对话框宽度占满屏幕
         *
         * @return
         */
        fun fullWidth(): Builder {
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT
            return this
        }

        /**
         * 对话框底部弹出
         *
         * @param isAnimation
         * @return
         */
        fun fromBottom(isAnimation: Boolean): Builder {
            if (isAnimation) {
                P.mAnimation = R.style.dialog_from_bottom_anim
            }
            P.mGravity = Gravity.BOTTOM
            return this
        }

        /**
         * 对话框右部弹出
         *
         * @param isAnimation
         * @return
         */
        fun fromRight(isAnimation: Boolean): Builder {
            if (isAnimation) {
                P.mAnimation = R.style.dialog_scale_anim
            }
            P.mGravity = Gravity.RIGHT
            return this
        }

        /**
         * 设置对话框宽高
         *
         * @param width
         * @param heigth
         * @return
         */
        fun setWidthAndHeight(width: Int, heigth: Int): Builder {
            P.mWidth = width
            P.mHeight = heigth
            return this
        }

        /**
         * 设置对话框宽高
         *
         * @param width
         * @param heigth
         * @return
         */
        fun setWidthAndHeightMargin(
            width: Int,
            heigth: Int,
            heightMargin: Int,
            widthMargin: Int
        ): Builder {
            P.mWidth = width
            P.mHeight = heigth
            P.mHeightMargin = heightMargin
            P.mWidthMargin = widthMargin
            return this
        }

        /**
         * 添加默认动画
         *
         * @return
         */
        fun addDefaultAnimation(): Builder {
            P.mAnimation = R.style.dialog_scale_anim
            return this
        }

        /**
         * 设置动画
         *
         * @param styleAnimation
         * @return
         */
        fun setAnimation(styleAnimation: Int): Builder {
            P.mAnimation = styleAnimation
            return this
        }

        /**
         * 设置点击事件
         *
         * @param viewId
         * @param onClickListener
         * @return
         */
        fun setOnClickListener(viewId: Int, onClickListener: View.OnClickListener?): Builder {
            P.mClickArray.put(viewId, onClickListener)
            return this
        }

        fun setOnLongClickListener(
            viewId: Int,
            onLongClickListener: OnLongClickListener?
        ): Builder {
            P.mLondClickArray.put(viewId, onLongClickListener)
            return this
        }

        /**
         * Sets whether the dialog is cancelable or not.  Default is true.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        fun setCancelable(cancelable: Boolean): Builder {
            P.mCancelable = cancelable
            return this
        }

        fun setOnCancelListener(onCancelListener: DialogInterface.OnCancelListener?): Builder {
            P.mOnCancelListener = onCancelListener
            return this
        }

        fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener?): Builder {
            P.mOnDismissListener = onDismissListener
            return this
        }

        fun setOnKeyListener(onKeyListener: DialogInterface.OnKeyListener?): Builder {
            P.mOnKeyListener = onKeyListener
            return this
        }

        fun create(): AlertDialog {
            // Context has already been wrapped with the appropriate theme.
            val dialog = AlertDialog(P.mContext, P.mThemeResId)
            P.apply(dialog.mAlert)
            dialog.setCancelable(P.mCancelable)
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true)
            }
            dialog.setOnCancelListener(P.mOnCancelListener)
            dialog.setOnDismissListener(P.mOnDismissListener)
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener)
            }
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            return dialog
        }

        fun show(): AlertDialog {
            val dialog = create()
            dialog.show()
            return dialog
        }
    }
}