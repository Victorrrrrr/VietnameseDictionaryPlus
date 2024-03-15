package com.gp.search.ui.suggest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.SEARCH_ACTIVITY_SUGGEST
import com.gp.framework.base.BaseMvvmActivity
import com.gp.mod_search.R
import com.gp.mod_search.databinding.ActivitySuggestBinding
import com.gp.search.ui.word.WordPageViewModel

@Route(path = SEARCH_ACTIVITY_SUGGEST)
class SuggestActivity : BaseMvvmActivity<ActivitySuggestBinding, WordPageViewModel>() {

    companion object {

        const val ERROR_WORD_DEFINITION = 0
        const val ERROR_SENTENCE = 1
        const val ERROR_BAD_INFORMATION = 2

        fun startSuggest(context: Context, error: Int) {

        }

    }


    override fun initView(savedInstanceState: Bundle?) {


    }
}