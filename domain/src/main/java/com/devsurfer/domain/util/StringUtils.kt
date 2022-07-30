package com.devsurfer.domain.util

import android.annotation.SuppressLint
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object StringUtils {
    fun getRandomString(length: Int): String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }
    fun getRandomBranchNumberToString(): String{
        return IntegerUtils.rand(1000, 10000).toString()
    }
    @SuppressLint("SimpleDateFormat")
    fun String.convertCurrentDateTime(): String{
        val receiveFormat = SimpleDateFormat(Constants.FORMAT_RECEIVE_DATE_TIME)
        val convertFormat = SimpleDateFormat(Constants.FORMAT_CONVERT_DATE_TIME)
        convertFormat.timeZone = TimeZone.getDefault()

        var timeStamp: Date? = null

        try{
            timeStamp = receiveFormat.parse(this)
        }catch (e: ParseException){
            Log.d("convertError", "convertCurrentDateTime: date parse error")
            return ""
        }

        return if(timeStamp == null){
            ""
        }else{
            convertFormat.format(timeStamp)
        }
    }
}