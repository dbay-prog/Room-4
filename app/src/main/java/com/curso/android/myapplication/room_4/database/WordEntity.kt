package com.curso.android.myapplication.room_2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Timestamp

@Entity(tableName = "word_table")
data class WordEntity(
    @PrimaryKey
    @ColumnInfo (name = "word") val word:String,
    @ColumnInfo (name = "date") val fecha:String,
    @ColumnInfo (name = "hours") val hours:String,

)