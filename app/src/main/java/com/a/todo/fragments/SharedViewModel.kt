package com.a.todo.fragments

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import com.a.todo.data.model.Priority

class SharedViewModel (application: Application): AndroidViewModel(application ) {

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
}