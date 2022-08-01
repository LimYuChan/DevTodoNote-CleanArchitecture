package com.devsurfer.devtodonote_cleanarchitecture.bindingAdapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.devsurfer.devtodonote_cleanarchitecture.R
import com.devsurfer.domain.enums.BranchState

object TextViewBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["branchTextState"])
    fun bindBrandTextState(
        textView: TextView,
        state: Int = 0
    ){
        when(state){
            BranchState.COMMIT.value ->{
                textView.text = textView.context.getString(R.string.branch_commit)
                textView.setTextColor(textView.context.getColor(R.color.commit))
            }
            BranchState.MERGE.value ->{
                textView.text = textView.context.getString(R.string.branch_merge)
                textView.setTextColor(textView.context.getColor(R.color.merge))
            }
            else-> textView.text = ""
        }
    }
}