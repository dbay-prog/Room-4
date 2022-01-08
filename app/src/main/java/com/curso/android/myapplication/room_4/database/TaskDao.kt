package com.curso.android.myapplication.room_4.database

import kotlinx.coroutines.flow.Flow
import androidx.room.*
import com.curso.android.myapplication.room_2.database.WordEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun getAllTask():Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(taskEntity : TaskEntity)

    @Query("SELECT * FROM task_table where id like :id")
    suspend fun getTaskById(id: Long): TaskEntity

    @Update
    suspend fun updateTask(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)

}