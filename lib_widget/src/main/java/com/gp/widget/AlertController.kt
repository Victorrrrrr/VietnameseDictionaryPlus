package com.gp.widget

import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.util.SparseArray
import android.view.Gravity
import android.view.View
import android.view.View.OnLongClickListener
import android.view.Window
import android.widget.EditText


class AlertController(alertDialog: AlertDialog, window: Window) {
    private val mAlertDialog: AlertDialog
    val window: Window
    private var mViewHelper: DialogViewHelper? = null

    init {
        mAlertDialog = alertDialog
        this.window = window
    }

    fun setDialogViewHelper(dialogViewHelper: DialogViewHelper?) {
        mViewHelper = dialogViewHelper
    }

    fun setText(viewId: Int, text: CharSequence?) {
        mViewHelper!!.setText<View>(viewId, text)
    }

    fun setEditTextHint(viewId: Int, text: CharSequence?) {
        mViewHelper!!.setTextHint<EditText>(viewId, text)
    }

    fun setIcon(viewId: Int, resId: Int) {
        mViewHelper!!.setIcon(viewId, resId)
    }

    fun <T : View?> getView(viewId: Int): T? {
        return mViewHelper!!.getView<T>(viewId)
    }

    fun setOnClickListener(viewId: Int, onClickListener: View.OnClickListener?) {
        mViewHelper!!.setOnClickListener(viewId, onClickListener)
    }

    val dialog: AlertDialog
        get() = mAlertDialog

    //-------------------------------------------------------------------------------------------------
    class AlertParams(
        var mContext: Context, //对话框主题背景
        var mThemeResId: Int
    ) {
        var mCancelable = false
        var mOnCancelListener: DialogInterface.OnCancelListener? = null
        var mOnDismissListener: DialogInterface.OnDismissListener? = null
        var mOnKeyListener: DialogInterface.OnKeyListener? = null

        //文本颜色
        var mTextColorArray = SparseArray<Int>()

        //存放文本的更改
        var mTextArray = SparseArray<CharSequence>()

        // 存放文本提示
        var mEditTextHintArray = SparseArray<CharSequence>()

        //存放点击事件
        var mClickArray = SparseArray<View.OnClickListener>()

        //存放长按点击事件
        var mLondClickArray = SparseArray<OnLongClickListener>()

        //存放对话框图标
        var mIconArray = SparseArray<Int>()

        //存放对话框图片
        var mBitmapArray = SparseArray<Bitmap>()

        //对话框布局资源id
        var mLayoutResId = 0

        //对话框的view
        var mView: View? = null

        //对话框宽度
        var mWidth = 0

        //对话框高度
        var mHeight = 0

        //对话框垂直外边距
        var mHeightMargin = 0

        //对话框横向外边距
        var mWidthMargin = 0

        //动画
        var mAnimation = 0

        //对话框显示位置
        var mGravity = Gravity.CENTER
        fun apply(alert: AlertController) {
            //设置对话框布局
            var dialogViewHelper: DialogViewHelper? = null
            if (mLayoutResId != 0) {
                dialogViewHelper = DialogViewHelper(mContext, mLayoutResId)
            }
            if (mView != null) {
                dialogViewHelper = DialogViewHelper()
                dialogViewHelper.contentView = mView
            }
            requireNotNull(dialogViewHelper) { "please set layout" }
            //将对话框布局设置到对话框
            dialogViewHelper.contentView?.let { alert.dialog.setContentView(it) }

            //设置DialogViewHelper辅助类
            alert.setDialogViewHelper(dialogViewHelper)
            //设置文本
            for (i in 0 until mTextArray.size()) {
                alert.setText(mTextArray.keyAt(i), mTextArray.valueAt(i))
            }
            //设置图标
            for (i in 0 until mIconArray.size()) {
                alert.setIcon(mIconArray.keyAt(i), mIconArray.valueAt(i))
            }
            //设置点击
            for (i in 0 until mClickArray.size()) {
                alert.setOnClickListener(mClickArray.keyAt(i), mClickArray.valueAt(i))
            }
            for (i in 0 until mEditTextHintArray.size()) {
                alert.setEditTextHint(mEditTextHintArray.keyAt(i), mEditTextHintArray.valueAt(i))
            }


            //配置自定义效果，底部弹出，宽高，动画，全屏
            val window = alert.window
            window.setGravity(mGravity) //显示位置
            if (mAnimation != 0) {
                window.setWindowAnimations(mAnimation) //设置动画
            }
            //设置宽高
            val params = window.attributes
            params.width = mWidth
            params.height = mHeight
            params.verticalMargin = mHeightMargin.toFloat()
            params.horizontalMargin = mWidthMargin.toFloat()
            window.attributes = params
        }
    }
}