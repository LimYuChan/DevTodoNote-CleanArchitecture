package com.devsurfer.devtodonote_cleanarchitecture.bindingAdapter

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

object ImageViewBindingAdapter {
    @SuppressLint("CheckResult")
    @JvmStatic
    @BindingAdapter(value = ["loadUrl", "isCenterCrop", "isCircle","cornerRadius"], requireAll = false)
    fun bindLoadImageView(
        imageView: ImageView,
        loadUrl: String?,
        isCenterCrop: Boolean = true,
        isCircle: Boolean = false,
        cornerRadius: Int
    ){
        loadUrl?.let {
            val glide = Glide.with(imageView.rootView).load(it).apply{
                if(isCenterCrop){
                    this.centerCrop()
                }
                if(isCircle){
                    this.circleCrop()
                }
                if(cornerRadius > 0){
                    this.transform(RoundedCorners(cornerRadius))
                }
            }

            glide.into(imageView)
        }
    }
}