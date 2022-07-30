package com.devsurfer.devtodonote_cleanarchitecture.extension

import android.content.Context
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