package com.devsurfer.domain.utils

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
}