package com.gp.search.ui.word

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.gp.common.constant.SEARCH_ACTIVITY_WORD
import com.gp.common.model.SentenceBean
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.getStringFromResource
import com.gp.mod_search.R
import com.gp.mod_search.databinding.ActivityWordPageBinding
import com.gp.network.constant.KEY_WORD
import com.gp.search.ui.suggest.SuggestActivity

@Route(path = SEARCH_ACTIVITY_WORD)
class WordPageActivity : BaseMvvmActivity<ActivityWordPageBinding, WordPageViewModel>() {
    private var suggestDialog : BottomSheetDialog? = null
    private lateinit var suggestDialogView : View
    private lateinit var mAdapter: SentencesAdapter

    override fun initView(savedInstanceState: Bundle?) {
        TipsToast.showTips(intent.getStringExtra(KEY_WORD))

        initRecyclerView()
        initEvent()


    }

    override fun initData() {
        val data = mutableListOf (
            SentenceBean(sentenceC = "sfsfsfsdfsdfsdf", sentenceV = "sdfsdfsdfsdfgsgsdgsd"),
            SentenceBean(sentenceC = "131212", sentenceV = "23r23r234")
        )
        mAdapter.setData(data)
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        mAdapter = SentencesAdapter()
        mBinding.rvSentence.apply {
            layoutManager = manager
            adapter = mAdapter
        }


    }

    private fun initEvent() {
        mBinding.ivBack.onClick {
            onBackPressed()
        }

        mBinding.ivSuggest.onClick {
            // 弹出反馈弹窗
            showBottomSheetDialog()
        }



    }

    private fun showBottomSheetDialog() {
        if(suggestDialog == null) {
            suggestDialog = BottomSheetDialog(this, com.gp.lib_widget.R.style.BottomSheetDialog)
            suggestDialogView = LayoutInflater.from(this).inflate(com.gp.lib_widget.R.layout.dialog_suggest_bottom_sheet, null, false)
            suggestDialog?.setContentView(suggestDialogView)

            initBottomDialogEvent(suggestDialogView)
        }

        suggestDialog?.show()
    }

    /**
     * 设置底部监听事件
     *
     * @param suggestDialogView
     */
    private fun initBottomDialogEvent(suggestDialogView: View?) {
        suggestDialogView?.findViewById<ImageView>(com.gp.lib_widget.R.id.iv_chacha)?.onClick {
            suggestDialog?.dismiss()
        }


        suggestDialogView?.findViewById<TextView>(com.gp.lib_widget.R.id.tv_meaning_error)?.onClick {
            SuggestActivity.startSuggest(this, SuggestActivity.ERROR_WORD_DEFINITION)
        }

        suggestDialogView?.findViewById<TextView>(com.gp.lib_widget.R.id.tv_sentence_error)?.onClick {
            SuggestActivity.startSuggest(this, SuggestActivity.ERROR_SENTENCE)
        }

        suggestDialogView?.findViewById<TextView>(com.gp.lib_widget.R.id.tv_bad_info)?.onClick {
            SuggestActivity.startSuggest(this, SuggestActivity.ERROR_BAD_INFORMATION)
        }

        suggestDialogView?.findViewById<TextView>(com.gp.lib_widget.R.id.tv_sentence_quality_problem)?.onClick {
            TipsToast.showTips(getStringFromResource(com.gp.lib_widget.R.string.tip_feedback))
        }


        suggestDialogView?.findViewById<TextView>(com.gp.lib_widget.R.id.tv_sound_error)?.onClick {
            TipsToast.showTips(getStringFromResource(com.gp.lib_widget.R.string.tip_feedback))
        }



    }


}