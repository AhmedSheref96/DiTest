package com.example.ditest.ui.home.products

import android.view.View
import androidx.lifecycle.viewModelScope
import com.example.ditest.base.BaseViewModel
import com.example.ditest.base.RecyclerAdapterBinding
import com.example.ditest.utils.Response
import com.example.ditest.utils.getData
import com.example.models.products.ResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repository: ProductsRepository) :
    BaseViewModel(), RecyclerAdapterBinding.ItemClickListener {
    val adapter = ProductsAdapter(this)

    val state = MutableStateFlow<Response<Array<ResponseItem>>>(Response.Loading())

    fun getProductsCategories() {
        viewModelScope.launch {
            repository.getProductsCategories({
                isLoading.postValue(it)
            }, {
                for (i in it) {
                    Timber.d("------- error $i")
                }
            }, {
                isEmpty.postValue(it)
            }, {
                for (i in it) {
                    Timber.d("------- data $i")
                }
            })
        }
    }


    fun getProducts() {
        viewModelScope.launch {
            getData(repository.getProducts(), onSuccess = {

            }, onLoading = {

            }, onError = {

            })
        }
    }

    fun getProducts2() {
        /*viewModelScope.launch {
            when (val response = repository.getProducts()) {
                is Response.Success -> {
                    response.data?.let { adapter.setData(it) }
                    Timber.d("------------- success  ${response.data}")
                }
                is Response.Error -> {
                    isLoading.postValue(false)
                    Timber.d("--------------- error ${response.message}")
                }
                is Response.Loading -> {
                    Timber.d("------------- loading -----")
                    isLoading.postValue(true)
                }
            }
        }*/
        viewModelScope.launch {
            state.emit(repository.getProducts())
        }
    }

    override fun onItemClickListener(v: View, pos: Int) {

    }

}