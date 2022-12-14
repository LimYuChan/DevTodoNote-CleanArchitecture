package com.devsurfer.data.extension

import android.content.SharedPreferences

object Preference {
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit): Boolean{
        val editor = this.edit()
        operation(editor)
        return editor.commit()
    }

    operator fun SharedPreferences.set(key: String, value: Any?): Boolean{
        return when(value){
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("정의되지 않은 타입입니다.")
        }
    }

    inline operator fun <reified T: Any> SharedPreferences.get(key: String, defaultValue: T? = null) =
        when(T::class){
            String::class -> getString(key, defaultValue as? String ?: "") as T
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T
            else -> throw UnsupportedOperationException("정의되지 않은 타입입니다.")
        }
}