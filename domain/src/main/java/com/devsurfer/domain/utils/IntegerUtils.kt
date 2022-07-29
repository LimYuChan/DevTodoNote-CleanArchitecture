package com.devsurfer.domain.utils

import java.util.*

object IntegerUtils {
    fun rand(from: Int, to: Int): Int{
        val random = Random()
        return random.nextInt(to - from) + from
    }
}