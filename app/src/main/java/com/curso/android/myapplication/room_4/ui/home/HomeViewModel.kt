package com.curso.android.myapplication.room_4.ui.home


import androidx.lifecycle.*
import com.curso.android.myapplication.room_4.database.TaskEntity
import com.curso.android.myapplication.room_4.database.WordEntity
import com.curso.android.myapplication.room_4.repository.WordRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class HomeViewModel(private val mRepository: WordRepository) : ViewModel() {

    val allWords:LiveData<List<WordEntity>> = mRepository.allWords.asLiveData()

    fun insert(word:WordEntity) = viewModelScope.launch{
        mRepository.insert(word)
    }

    fun deleteWord(fecha: Long) = viewModelScope.launch {
        mRepository.deleteWord(fecha)
    }

    val allTasks:LiveData<List<TaskEntity>> = mRepository.allTasks.asLiveData()

    fun insertTask(task: TaskEntity) = viewModelScope.launch {
        mRepository.insertTask(task)
    }
    fun deleteTask(task: TaskEntity ) = viewModelScope.launch {
        mRepository.deleteTask(task)
    }

    fun upDateTask(task: TaskEntity) = viewModelScope.launch {
        mRepository.upDateTask(task)
    }

}

class HomeViewModelFactory(private val repository: WordRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            @Suppress("UNCHECKET_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}