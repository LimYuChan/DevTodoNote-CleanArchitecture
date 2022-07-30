package com.devsurfer.devtodonote_cleanarchitecture.extension

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.widget.TextView
import androidx.core.content.ContextCompat

fun TextView.setDrawableTint(color: Int){
    this.compoundDrawablesRelative.forEach {
        it?.let {
            it.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(this.context, color), PorterDuff.Mode.SRC_IN)
        }
    }
}