package com.github.algamza.bucketplace.view.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.algamza.bucketplace.R

@BindingAdapter("goneUnless")
fun goneUnless(view: View, value: Boolean){
    view.visibility = when(value){
        true -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("loadImg")
fun loadImg(view: ImageView, url: String?){
    if ( url == null ) return
    Glide.with(view.context)
        .load(url)
        .error(R.drawable.img_error)
        .into(view)
}