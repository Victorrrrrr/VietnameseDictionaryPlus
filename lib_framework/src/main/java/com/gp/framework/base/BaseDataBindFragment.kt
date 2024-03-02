package com.gp.framework.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.gp.framework.ext.saveAs
import com.gp.framework.ext.saveAsUnChecked
import java.lang.reflect.ParameterizedType

abstract class BaseDataBindFragment<DB : ViewDataBinding> : BaseFragment() {
    //This property is only valid between onCreateView and onDestroyView.
    var mBinding: DB? = null

    override fun getContentView(inflater: LayoutInflater, container: ViewGroup?): View {
//        mBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        val type = javaClass.genericSuperclass
        val vbClass: Class<DB> = type!!.saveAs<ParameterizedType>().actualTypeArguments[0].saveAs()
        val method = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        mBinding = method.invoke(this, layoutInflater)!!.saveAsUnChecked()
        return mBinding!!.root
    }

    override fun getLayoutResId(): Int = 0

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}