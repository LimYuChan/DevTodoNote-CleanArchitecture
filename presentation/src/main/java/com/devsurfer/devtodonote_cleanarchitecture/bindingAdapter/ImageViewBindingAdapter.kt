package com.devsurfer.devtodonote_cleanarchitecture.bindingAdapter

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.domain.enums.BranchState

object ImageViewBindingAdapter {
    @SuppressLint("CheckResult")
    @JvmStatic
    @BindingAdapter(value = ["loadUrl", "isCenterCrop", "isCircle", "cornerRadius", "isFile"], requireAll = false)
    fun bindLoadImageView(
        imageView: ImageView,
        loadUrl: String?,
        isCenterCrop: Boolean = true,
        isCircle: Boolean = false,
        cornerRadius: Int,
        isFile: Boolean = false
    ){
        loadUrl?.let {
            val absoluteUrl =
                if(isFile)
                    "${imageView.context.externalCacheDir?.absoluteFile}/$it"
                else
                    it
            val glide = Glide.with(imageView.rootView).load(absoluteUrl).apply{
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

    @JvmStatic
    @BindingAdapter(value = ["branchImageState"])
    fun bindBranchImageState(
        imageView: ImageView,
        state: Int = 0
    ){
        when(state){
            BranchState.COMMIT.value -> imageView.setColorFilter(imageView.resources.getColor(R.color.commit));
            BranchState.MERGE.value -> imageView.setColorFilter(imageView.resources.getColor(R.color.merge));
            else->imageView.setColorFilter(imageView.resources.getColor(R.color.gray));
        }
    }
}