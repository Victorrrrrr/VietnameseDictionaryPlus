package com.gp.search.ui.suggest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.SEARCH_ACTIVITY_SUGGEST
import com.gp.common.constant.SUGGEST_TYPE
import com.gp.common.model.SuggestListItem
import com.gp.common.model.SuggestRequest
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.toast.TipsToast
import com.gp.mod_search.databinding.ActivitySuggestBinding
import com.gp.network.constant.KEY_WORD_ID
import com.gp.search.ui.search.SearchViewModel

@Route(path = SEARCH_ACTIVITY_SUGGEST)
class SuggestActivity : BaseMvvmActivity<ActivitySuggestBinding, SuggestViewModel>() {

    companion object {
        const val ERROR_WORD_DEFINITION = 1
        const val ERROR_SENTENCE = 2
        const val ERROR_BAD_INFORMATION = 0

        fun startSuggest(context: Context, error: Int, wordId : Int) {
            val intent = Intent(context, SuggestActivity::class.java)
            intent.putExtra(SUGGEST_TYPE, error)
            intent.putExtra(KEY_WORD_ID, wordId)
            context.startActivity(intent)
        }

    }

    private var type = -1
    private var wordId = -1


    override fun initView(savedInstanceState: Bundle?) {
        type = intent.getIntExtra(SUGGEST_TYPE, -1)
        wordId = intent.getIntExtra(KEY_WORD_ID, -1)
        when(type) {
            ERROR_SENTENCE-> {
                mBinding.tvSuggestType.text = "例句有误"
                mBinding.etType.hint = "请正确的例句"
                mBinding.etSuggestContent.hint = "请输入正确翻译"
            }
            ERROR_WORD_DEFINITION -> {
                mBinding.tvSuggestType.text = "释义有误"
                mBinding.etType.hint = "请输入正确词性"
                mBinding.etSuggestContent.hint = "请输入正确释义"
            }
            ERROR_BAD_INFORMATION -> {
                mBinding.tvSuggestType.text = "存在违规等不良信息"
                mBinding.etType.hint = "请输入违规行为"
                mBinding.etSuggestContent.hint = "请输入具体描述"
            }
            else -> ""
        }



        mBinding.btnFinish.onClick {
            val suggestType = mBinding.etType.text.toString()
            val content = mBinding.etSuggestContent.text.toString()
            if(suggestType.isEmpty() || content.isEmpty()) {
                TipsToast.showTips("请填写完整信息")
            }
            val suggestListItem = SuggestRequest(content = "$suggestType $content", type = type, dataId = wordId )
            mViewModel.suggest(suggestListItem) {
                TipsToast.showTips("反馈成功")
                finish()
            }.observe(this) {

            }
        }

    }
}