package com.gp.search.ui.suggest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.KEY_INDEX
import com.gp.common.constant.SEARCH_ACTIVITY_SUGGEST
import com.gp.common.constant.SUGGEST_TYPE
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.toast.TipsToast
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
            val intent = Intent(context, SuggestActivity::class.java)
            intent.putExtra(SUGGEST_TYPE, error)
            context.startActivity(intent)
        }

    }


    override fun initView(savedInstanceState: Bundle?) {
        val type = intent.getIntExtra(SUGGEST_TYPE, -1)

        TipsToast.showTips("type : $type")

    }
}