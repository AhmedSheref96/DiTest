package com.example.ditest.ui.home.products

import android.annotation.SuppressLint
import com.example.ditest.R
import com.example.ditest.base.RecyclerAdapterBinding
import com.example.ditest.databinding.ProductItemBinding
import com.example.models.products.ResponseItem

class ProductsAdapter(itemClickListener: ItemClickListener) :
    RecyclerAdapterBinding<ProductItemBinding>(
        itemClickListener = itemClickListener,
        layout = R.layout.product_item
    ) {

    private var list: Array<ResponseItem>? = null

    override fun onBindViewHolder(holder: MainViewHolder<ProductItemBinding>, position: Int) {
        holder.binding.productItem = list?.get(position)
        holder.bindListener()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: Array<ResponseItem>) {
        list = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list?.size ?: 0
}