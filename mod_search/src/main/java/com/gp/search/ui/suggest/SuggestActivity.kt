package com.gp.search.ui.suggest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.SEARCH_ACTIVITY_SUGGEST
import com.gp.common.constant.SUGGEST_TYPE
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.toast.TipsToast
import com.gp.mod_search.databinding.ActivitySuggestBinding
import com.gp.search.ui.search.SearchViewModel

@Route(path = SEARCH_ACTIVITY_SUGGEST)
class SuggestActivity : BaseMvvmActivity<ActivitySuggestBinding, SearchViewModel>() {

    companion object {
        const val ERROR_WORD_DEFINITION = 1
        const val ERROR_SENTENCE = 2
        const val ERROR_BAD_INFORMATION = 0

        fun startSuggest(context: Context, error: Int) {
            val intent = Intent(context, SuggestActivity::class.java)
            intent.putExtra(SUGGEST_TYPE, error)
            context.startActivity(intent)
        }

    }


    override fun initView(savedInstanceState: Bundle?) {
        val type = intent.getIntExtra(SUGGEST_TYPE, -1)

        mBinding.tvSuggestType.text = when(type) {
            ERROR_SENTENCE-> {
                "例句有误"
            }
            ERROR_WORD_DEFINITION -> {
                "释义有误"
            }
            ERROR_BAD_INFORMATION -> {
                "存在违规等不良信息"
            }
            else -> ""
        }




    }
}