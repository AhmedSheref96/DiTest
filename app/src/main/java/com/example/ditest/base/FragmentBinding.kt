package com.example.ditest.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

open class FragmentBinding : Fragment() {

    protected inline fun <reified T : ViewDataBinding> binding(inflater: LayoutInflater,layout: Int,container: ViewGroup?): T =
        DataBindingUtil.inflate<T>(inflater, layout, container, false)

}