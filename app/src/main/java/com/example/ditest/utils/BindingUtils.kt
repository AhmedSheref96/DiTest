package com.example.ditest.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.ditest.R

@BindingAdapter("app:bindImg")
fun bindImg(v: ImageView, url: String) {
    try {
        Glide.with(v.context)
            .load(url)
            .centerCrop()
            .placeholder(R.color.white)
            .into(v)
    } catch (e: Exception) {
    }
}