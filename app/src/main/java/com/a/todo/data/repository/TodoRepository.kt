package com.a.todo.data.repository

import androidx.lifecycle.LiveData
import com.a.todo.data.TodoDao
import com.a.todo.data.model.TodoData

class TodoRepository (private val todoDao: TodoDao){

    val getAllData: LiveData<List<TodoData>> = todoDao.getAllData()

    suspend fun insertData(todoData: TodoData){
        todoDao.insertData(todoData)
    }


    suspend fun updateData(todoData: TodoData){
        todoDao.updateData(todoData)
    }
}