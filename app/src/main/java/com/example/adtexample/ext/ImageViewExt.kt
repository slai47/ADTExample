package com.example.adtexample.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String?) {
    url?.let {
        Glide.with(this.context).load(url).into(this)
    }
}