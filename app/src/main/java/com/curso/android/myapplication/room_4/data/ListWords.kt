package com.curso.android.myapplication.room_4.data

import com.curso.android.myapplication.room_2.database.WordEntity

class ListWords {

    fun getListWords(words:List<WordEntity>){
        val fechaTarea = words.groupBy ({it.fecha}, {it.word})
        val fechaHora = words.groupBy ({it.fecha},{})
    }


}