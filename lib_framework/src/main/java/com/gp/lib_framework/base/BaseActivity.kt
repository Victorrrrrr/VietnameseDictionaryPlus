package com.gp.lib_framework.base

import androidx.appcompat.app.AppCompatActivity

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/1/29:00:47.
 * @Desrciption: Activity基类
 */
abstract class BaseActivity : AppCompatActivity() {
    protected var TAG: String? = this::class.java.simpleName



}