package com.example.ditest.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.ditest.R

@BindingAdapter("app:bindImg")
fun bindImg(v: ImageView, url: String) {
    try {
        Glide.with(v.context)
            .load(url)
            .centerInside()
            .placeholder(R.color.white)
            .into(v)
    } catch (e: Exception) {}
}

@BindingAdapter("bindImgH", "placeHolder")
fun bindImgWithPlaceHolder(v: ImageView, url: String, drawable: Drawable) {
    try {
        Glide.with(v.context)
            .load(url)
            .centerInside()
            .placeholder(drawable)
            .into(v)
    } catch (e: Exception) {
    }
}