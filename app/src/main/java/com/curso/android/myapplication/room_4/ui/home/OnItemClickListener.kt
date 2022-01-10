package com.curso.android.myapplication.room_4.ui.home

import com.curso.android.myapplication.room_2.database.WordEntity

interface OnItemClickListener {
    fun onItemClick(word: WordEntity)
    fun onLongItemClick(word: WordEntity)

}