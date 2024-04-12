package com.gp.search.ui.word

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.gp.common.constant.SEARCH_ACTIVITY_WORD
import com.gp.common.model.AddWordToFolderRequest
import com.gp.common.model.FolderListBean
import com.gp.common.model.SentenceBean
import com.gp.common.model.WordBeanItem
import com.gp.common.model.WordInfo
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.MediaHelper
import com.gp.framework.utils.getDrawableFromResource
import com.gp.framework.utils.getStringFromResource
import com.gp.mod_search.R
import com.gp.mod_search.databinding.ActivityWordPageBinding
import com.gp.network.constant.KEY_WORD_ID
import com.gp.network.manager.WordBookIdManager
import com.gp.search.ui.search.SearchViewModel
import com.gp.search.ui.suggest.SuggestActivity

@Route(path = SEARCH_ACTIVITY_WORD)
class WordPageActivity : BaseMvvmActivity<ActivityWordPageBinding, SearchViewModel>() {
    private var suggestDialog : BottomSheetDialog? = null
    private lateinit var suggestDialogView : View

    private var folderDialog: BottomSheetDialog? = null
    private lateinit var folderDialogView : View

    private var folderDialogAdapter = FolderDialogAdapter()

    private lateinit var mAdapter: SentencesAdapter
    private var id : String = ""
    private var word : WordBeanItem? = null
    private var isFav = false

    override fun initView(savedInstanceState: Bundle?) {
        id = intent.getStringExtra(KEY_WORD_ID).toString()

        initRecyclerView()
        initEvent()


    }

    override fun initData() {
        val data = mutableListOf (
            SentenceBean(sentenceC = "4个工人在安装钢琴，待会儿他们要搬运大提琴。", sentenceV = "Bốn công nhân đang lắp đàn, và họ sẽ di chuyển chiếc đàn Cello sau đó"),
            SentenceBean(sentenceC = "现在第五电视频道在播放足球赛", sentenceV = "Bây giờ kênh truyền hình thứ năm đang phát sóng một trận đấu bóng đá.")
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

    override fun onResume() {
        super.onResume()
        // 单词状态
        mViewModel.getWordDetail(id).observe(this) {
            mBinding.word = it
            word = it
            mBinding.source = it.source
            mBinding.lexicon = it.lexicon
        }

        // 收藏状态
        mViewModel.getFolderWordList(WordBookIdManager.getFavFolderId(), 1, 100, word?.wordVi?:"")
            .observe(this) { list ->
                for (fWord in list.data) {
                    if(fWord.id == word?.id) {
                        mBinding.ivFav.setImageDrawable(getDrawableFromResource(com.gp.lib_widget.R.drawable.ic_fav_already))
                        isFav = true
                    } else {
                        mBinding.ivFav.setImageDrawable(getDrawableFromResource(com.gp.lib_widget.R.drawable.ic_fav))
                        isFav = false
                    }
                    mBinding.ivFav.parent.requestLayout()
                }
            }
    }

    private fun initEvent() {
        mBinding.ivBack.onClick {
            onBackPressed()
        }

        mBinding.ivSuggest.onClick {
            // 弹出反馈弹窗
            showBottomSheetSuggestDialog()
        }

        mBinding.ivSound.onClick {
            MediaHelper.playInternetSource(word?.pronounceVi)
        }

        mBinding.ivFolder.onClick {
            showBottomSheetFolderDialog()
        }


        mBinding.ivFav.onClick {
            if(isFav) {
                mViewModel.deleteWordInFolder(WordBookIdManager.getFavFolderId(), word?.id ?: -1) {
                    TipsToast.showTips("取消收藏")
                    mBinding.ivFav.setImageDrawable(getDrawableFromResource(com.gp.lib_widget.R.drawable.ic_fav))
                    isFav = false
                }.observe(this) {

                }
            } else {
                val wordInfo = WordInfo("", word?.id ?:-1)
                val list = ArrayList<WordInfo>()
                list.add(wordInfo)
                val addWordToFolderRequest = AddWordToFolderRequest(WordBookIdManager.getFavFolderId(), list)
                mViewModel.addWordToFolder(addWordToFolderRequest) {
                    TipsToast.showTips("成功收藏")
                    mBinding.ivFav.setImageDrawable(getDrawableFromResource(com.gp.lib_widget.R.drawable.ic_fav_already))
                    isFav = true
                }.observe(this){}
            }


        }


    }


    private fun showBottomSheetFolderDialog() {
        if(folderDialog == null) {
            folderDialog = BottomSheetDialog(this, com.gp.lib_widget.R.style.BottomSheetDialog)
            folderDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_folder, null, false)
            folderDialog?.setContentView(folderDialogView)

            initFolderDialogEvent(folderDialogView)
        }

        folderDialog?.show()
    }

    private fun initFolderDialogEvent(folderDialogView: View?) {
        val rvFolder = folderDialog?.findViewById<RecyclerView>(R.id.rv_folder)
        folderDialogAdapter = FolderDialogAdapter()
        rvFolder?.adapter = folderDialogAdapter
        rvFolder?.layoutManager = LinearLayoutManager(this)
        var filterData = listOf<FolderListBean>()
        mViewModel.getFolderList().observe(this) {
            for (folderListBean in it.data) {
                filterData = it.data.filter { item ->
                    item.id != WordBookIdManager.getFavFolderId()
                }
            }
            folderDialogAdapter.setData(filterData)
            folderDialogAdapter.notifyDataSetChanged()
        }

        val btnConfirm = folderDialog?.findViewById<Button>(R.id.btn_confirm)
        btnConfirm?.onClick {
            val folderId = getFolderId()
            if(folderId != null){
                var list = ArrayList<WordInfo>()
                list.add(WordInfo("" , id.toInt()))
                val addWordToFolderRequest = AddWordToFolderRequest(folderId, list)
                mViewModel.addWordToFolder(addWordToFolderRequest){
                    TipsToast.showTips("加入成功")
                    folderDialog?.dismiss()
                }.observe(this){}
            } else {
                TipsToast.showTips("请选择一个单词夹进行添加")
            }
        }


    }

    private fun getFolderId() : Int?{
        for (folderListBean in folderDialogAdapter.getData()) {
            if(folderListBean.isSelected) {
                return folderListBean.id
            }
        }
        return null
    }


    private fun showBottomSheetSuggestDialog() {
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
            suggestDialog?.dismiss()
        }

        suggestDialogView?.findViewById<TextView>(com.gp.lib_widget.R.id.tv_sentence_error)?.onClick {
            SuggestActivity.startSuggest(this, SuggestActivity.ERROR_SENTENCE)
            suggestDialog?.dismiss()
        }

        suggestDialogView?.findViewById<TextView>(com.gp.lib_widget.R.id.tv_bad_info)?.onClick {
            SuggestActivity.startSuggest(this, SuggestActivity.ERROR_BAD_INFORMATION)
            suggestDialog?.dismiss()
        }

        suggestDialogView?.findViewById<TextView>(com.gp.lib_widget.R.id.tv_sentence_quality_problem)?.onClick {
            TipsToast.showTips(getStringFromResource(com.gp.lib_widget.R.string.tip_feedback))
            suggestDialog?.dismiss()
        }


        suggestDialogView?.findViewById<TextView>(com.gp.lib_widget.R.id.tv_sound_error)?.onClick {
            TipsToast.showTips(getStringFromResource(com.gp.lib_widget.R.string.tip_feedback))
            suggestDialog?.dismiss()
        }



    }


}