package com.curso.android.myapplication.room_2.database

import android.content.Context
import androidx.room.*
import com.curso.android.myapplication.room_4.database.TaskDao
import com.curso.android.myapplication.room_4.database.TaskEntity


@Database(entities = [WordEntity::class,TaskEntity::class],version = 1,exportSchema = false)
abstract class WordRoomDatabase:RoomDatabase() {

    abstract fun wordDao():WordDao
    abstract fun taskDao():TaskDao

    companion object{
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE:WordRoomDatabase? = null
        fun getInstance(context: Context):WordRoomDatabase{
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "wordDatabase4"
                ).build()
                INSTANCE=instance
                //return instance
                instance
            }
        }
    }
}
