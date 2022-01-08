package com.curso.android.myapplication.room_4.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var name:String = " ",
    var isDOne:Boolean = false
)