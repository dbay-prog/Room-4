package com.curso.android.myapplication.room_4.utils

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi

class GetDate {
    @RequiresApi(Build.VERSION_CODES.N)
    fun newDate():String{
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        val fechaActual = "$day/${month+1}/$year"
        return  fechaActual

    }
}