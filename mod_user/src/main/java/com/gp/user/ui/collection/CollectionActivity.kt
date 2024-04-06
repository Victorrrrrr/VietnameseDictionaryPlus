package com.gp.user.ui.collection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.USER_ACTIVITY_COLLECTION
import com.gp.framework.base.BaseMvvmActivity
import com.gp.mod_user.R
import com.gp.mod_user.databinding.ActivityCollectionBinding

@Route(path = USER_ACTIVITY_COLLECTION)
class CollectionActivity : BaseMvvmActivity<ActivityCollectionBinding, CollectionViewModel>() {


    override fun initView(savedInstanceState: Bundle?) {

    }
}