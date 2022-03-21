package com.example.ditest.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData(false)
    val isEmpty = MutableLiveData(false)
}