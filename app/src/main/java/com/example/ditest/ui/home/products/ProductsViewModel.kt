package com.example.ditest.ui.home.products

import android.view.View
import androidx.lifecycle.viewModelScope
import com.example.ditest.base.BaseViewModel
import com.example.ditest.base.RecyclerAdapterBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repository: ProductsRepository) :
    BaseViewModel(), RecyclerAdapterBinding.ItemClickListener {

    val adapter = ProductsAdapter(this)

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
            repository.getProducts({
                isLoading.postValue(it)
            }, {
                Timber.d("------- error $it")

            }, {
                isEmpty.postValue(it)
            }, {
                adapter.setData(it)
            })
        }
    }

    override fun onItemClickListener(v: View, pos: Int) {

    }

}