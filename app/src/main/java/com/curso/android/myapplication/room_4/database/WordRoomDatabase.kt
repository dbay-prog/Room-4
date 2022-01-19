package com.curso.android.myapplication.room_4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [WordEntity::class,TaskEntity::class],version = 1,exportSchema = false)
abstract class WordRoomDatabase:RoomDatabase() {

    abstract fun taskDao():TaskDao
    abstract fun wordDao():WordDao

    companion object{
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE:WordRoomDatabase? = null
        fun getInstance(context:Context):WordRoomDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "wordDatabase10"
                ).build()
                INSTANCE = instance
                //return instance
                instance
            }
        }

    }

}