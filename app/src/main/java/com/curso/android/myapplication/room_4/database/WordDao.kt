package com.curso.android.myapplication.room_2.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface WordDao {
    @Query("SELECT * FROM word_table ORDER BY date ASC")
    fun getAlphabetizedWords():Flow<List<WordEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word:WordEntity)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

}