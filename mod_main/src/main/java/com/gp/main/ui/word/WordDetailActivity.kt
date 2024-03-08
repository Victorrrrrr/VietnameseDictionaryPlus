package com.gp.main.ui.word

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gp.framework.base.BaseMvvmActivity
import com.gp.main.databinding.ActivityWordDetailBinding
import com.gp.network.constant.KEY_WORD

class WordDetailActivity : BaseMvvmActivity<ActivityWordDetailBinding, WordDetailViewModel>() {

    companion object {
        fun start(context: Context, word: String) {
            val intent = Intent(context, WordDetailActivity::class.java)
            intent.putExtra(KEY_WORD, word)
            context.startActivity(intent)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {

    }


}