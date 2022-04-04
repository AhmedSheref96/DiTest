package com.example.ditest.ui.home.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ditest.R
import com.example.ditest.base.FragmentBinding
import com.example.ditest.databinding.ProductsFragmentBinding
import com.example.ditest.utils.Collector
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ProductsFragment : FragmentBinding() {
    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding<ProductsFragmentBinding>(inflater, R.layout.products_fragment, container)
            .apply {
                viewModel = this@ProductsFragment.viewModel
                lifecycleOwner = this@ProductsFragment
                executePendingBindings()

            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.getProducts()
        viewModel.getProducts2()

        viewModel.isLoading.observe(viewLifecycleOwner) {
            Timber.d("-------------------loaddddd $it")
        }

        lifecycleScope.launch {
            viewModel.state.collect(Collector(
                onLoading = {
                    Timber.d("--------------- loading  $it")
                    viewModel.isLoading.postValue(it)
                },
                onError = { msg: String ->
                    Timber.d("--------------- error $msg")
                },
                onSuccess = { data ->
                    Timber.d("--------------- success $data")
                }
            ))
        }


    }

}