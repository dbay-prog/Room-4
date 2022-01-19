package com.curso.android.myapplication.room_4.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "word_table")
data class WordEntity(
    @PrimaryKey
    @ColumnInfo (name = "word") val word:String,
    @ColumnInfo (name = "date") val fecha:Long,
    @ColumnInfo (name = "hours") val hours:String,

)