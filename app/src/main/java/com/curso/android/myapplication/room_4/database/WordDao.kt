package com.curso.android.myapplication.room_4.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao{
    @Query("SELECT * FROM word_table ORDER BY date ASC")
    fun getAlphabetizedWords():Flow<List<WordEntity>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word:WordEntity)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Query("DELETE FROM word_table WHERE date=:fecha")
    suspend fun deleteWord(fecha:String)
}
