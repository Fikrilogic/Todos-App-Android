package com.fikrisandi.utils.time

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object TimeUtils{

    @SuppressLint("SimpleDateFormat")
    fun longToTime(format: String, value: Any): String{
        return SimpleDateFormat(format).format(value)
    }
}