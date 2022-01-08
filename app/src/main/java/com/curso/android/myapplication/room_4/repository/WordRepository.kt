package com.curso.android.myapplication.room_2.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.curso.android.myapplication.room_2.database.WordDao
import com.curso.android.myapplication.room_2.database.WordEntity
import com.curso.android.myapplication.room_4.database.TaskDao
import com.curso.android.myapplication.room_4.database.TaskEntity
import kotlinx.coroutines.flow.Flow
// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO

class WordRepository(var wordDao: WordDao, var taskDao: TaskDao) {

    var allWords:Flow<List<WordEntity>> = wordDao.getAlphabetizedWords()
    var allTasks:Flow<List<TaskEntity>> = taskDao.getAllTask()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: WordEntity) {
        wordDao.insert(word)
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertTask(task: TaskEntity) {
        taskDao.addTask(task)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun upDateTask(task: TaskEntity) {
        taskDao.updateTask(task)
    }




    companion object{
        private var mInstance:WordRepository? = null
        @Synchronized
        fun getInstance(wordDao: WordDao, taskDao:TaskDao):WordRepository{
            if (mInstance == null){
                mInstance = WordRepository(wordDao, taskDao)
            }
            return mInstance as WordRepository
        }
    }






}