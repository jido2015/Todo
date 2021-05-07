package com.a.todo.fragments

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.a.todo.data.model.Priority
import com.a.todo.data.model.TodoData
import org.apache.commons.lang3.mutable.Mutable

class SharedViewModel (application: Application): AndroidViewModel(application ) {

    val emptyDatabase: MutableLiveData<Boolean> =MutableLiveData(true)

    fun checkIfDatabaseIsEmpty(todoData: List<TodoData>){
        emptyDatabase.value = todoData.isEmpty()
    }

    fun verifyDataFromUser(mTitle: String, mDescription: String): Boolean {
        return if (TextUtils.isEmpty(mTitle) || TextUtils.isEmpty(mDescription)){
            false
        } else !(mTitle.isEmpty() || mDescription.isEmpty())
    }

    fun parsPriority(priority: String): Priority {
        return when (priority) {
            "High Priority" -> {
                Priority.HIGH}
            "Medium Priority" -> {
                Priority.MEDIUM}
            "Low Priority" -> {
                Priority.LOW}
            else -> Priority.LOW
        }
    }

    fun parsePriorityToInt(priority: Priority): Int{
        return when(priority){
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }
}