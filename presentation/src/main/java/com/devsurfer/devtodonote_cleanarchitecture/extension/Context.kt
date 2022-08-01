package com.devsurfer.devtodonote_cleanarchitecture.extension

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.devsurfer.devtodonote_cleanarchitecture.R
import java.util.*

fun Context.getLanguageColor(language: String?):Int{
    return if(language.isNullOrEmpty()){
        R.color.language_default_color
    }else{
        when(language.lowercase(Locale.getDefault())){
            "kotlin"->R.color.kotlin_color
            "java"->R.color.java_color
            "javascript"->R.color.java_script_color
            "swift"->R.color.swift_color
            "html"->R.color.html_color
            "css"->R.color.css_color
            "php"->R.color.php_color
            "python"->R.color.python_color
            "vue"->R.color.vue_color
            "c"->R.color.c_color
            "c#"->R.color.c_hash_color
            "c++"->R.color.c_2plus_color
            "ruby"->R.color.ruby_color
            "dart"->R.color.dart_color
            else->R.color.language_default_color
        }
    }
}

fun Context.resolveThemeAttr(@AttrRes attrRes: Int): TypedValue {
    val typedValue = TypedValue()
    theme.resolveAttribute(attrRes, typedValue, true)
    return typedValue
}
@ColorInt
fun Context.resolveColorAttr(@AttrRes colorAttr: Int): Int {
    val resolvedAttr = resolveThemeAttr(colorAttr)
    val colorRes = if (resolvedAttr.resourceId != 0) resolvedAttr.resourceId else resolvedAttr.data
    return ContextCompat.getColor(this, colorRes)
}
