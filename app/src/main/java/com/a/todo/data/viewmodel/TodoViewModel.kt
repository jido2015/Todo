package com.a.todo.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.a.todo.data.TodoDatabase
import com.a.todo.data.model.TodoData
import com.a.todo.data.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application){

    private val todoDao = TodoDatabase.getDatabase(application).todoDoa()
    private val repository: TodoRepository
    private val getAllData: LiveData<List<TodoData>>

    init {
        repository = TodoRepository(todoDao)
        getAllData = repository.getAllData
    }

    fun insertData(todoData: TodoData){
        viewModelScope.launch( Dispatchers.IO){
            repository.insertData(todoData)
        }
    }
}