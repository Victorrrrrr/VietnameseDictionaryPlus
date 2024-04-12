package com.gp.framework.base

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.gp.framework.R
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.LanguageChangeUtils
import com.gp.framework.utils.LoadingUtils

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/1/29:00:47.
 * @Desrciption: Activity基类
 */
abstract class BaseActivity : AppCompatActivity() {
    protected var TAG: String? = this::class.java.simpleName

    private val dialogUtils by lazy {
        LoadingUtils(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preCreate()
        setContentLayout()
        initView(savedInstanceState)
        initData()
    }


    /**
     * 设置布局
     */
    open fun setContentLayout() {
        setContentView(getLayoutResId())
    }

    /**
     * 初始化视图
     * @return Int 布局id
     * 仅用于不继承BaseBindingActivity类的传递布局文件
     */
    abstract fun getLayoutResId(): Int


    /**
     * 初始化布局
     * @param savedInstanceState Bundle?
     */
    abstract fun initView(savedInstanceState: Bundle?)


    /**
     * 初始化数据
     */
    open fun initData() {

    }

    /**
     * setContentView之前的操作
     */
    open fun preCreate() {

    }


    /**
     * 加载中……弹框
     */
    fun showLoading() {
        showLoading(getString(R.string.default_loading))
    }

    /**
     * 加载提示框
     */
    fun showLoading(msg: String?) {
        dialogUtils.showLoading(msg)
    }

    /**
     * 加载提示框
     */
    fun showLoading(@StringRes res: Int) {
        showLoading(getString(res))
    }

    /**
     * 关闭提示框
     */
    fun dismissLoading() {
        dialogUtils.dismissLoading()
    }

    /**
     * Toast
     * @param msg Toast内容
     */
    fun showToast(msg: String) {
        TipsToast.showTips(msg)
    }

    /**
     * Toast
     * @param resId 字符串id
     */
    fun showToast(@StringRes resId: Int) {
        TipsToast.showTips(resId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * 上面这样切换，在Android7的系统上会有问题
     * 现在暂时的做法是在BaseActivity上重写getResources()方法
     */
//    override fun getResources(): Resources {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
//            return LanguageChangeUtils.changeAppLanguage(applicationContext).resources
//        }
//        return super.getResources()
//    }

    /**
     * 更改语言,此方法先于 onCreate()方法执行
     * android N以下，需要在application的onCreate里切换Locale,N及以上,在Activity的attachBaseContext去切换即可
     */
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { LanguageChangeUtils.changeAppLanguage(it) })
    }
}