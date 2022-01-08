package com.curso.android.myapplication.room_4.utils

import android.content.Context
import com.curso.android.myapplication.room_2.database.WordRoomDatabase
import com.curso.android.myapplication.room_2.repository.WordRepository
import com.curso.android.myapplication.room_4.ui.home.HomeViewModelFactory

class InjectorUtils {

    companion object{
        private fun injectarRepository(context: Context):WordRepository{
            val database = WordRoomDatabase.getInstance(context.applicationContext)
            return WordRepository.getInstance(database.wordDao(),database.taskDao())
        }

        fun inyectarHomeViewModelFactory(context: Context):HomeViewModelFactory{
            val repository = injectarRepository(context.applicationContext)
            return HomeViewModelFactory(repository)
        }

    }

}