package com.gp.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.KEY_ID
import com.gp.common.constant.KEY_INDEX
import com.gp.common.constant.MAIN_ACTIVITY_HOME

@Route(path = MAIN_ACTIVITY_HOME)
class MainActivity : AppCompatActivity() {


    companion object {
        fun start(context: Context, index: Int = 0) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(KEY_INDEX, index)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}