package com.example.pestotodoapplication.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pestotodoapplication.data.ToDoDatabase
import com.example.pestotodoapplication.data.models.ToDoData
import com.example.pestotodoapplication.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * author lgarg on 4/24/24.
 */
class ToDoViewModel(application: Application): AndroidViewModel(application) {

    private val todoDao = ToDoDatabase.getDatabase(application).todoDao()
    private val repository: ToDoRepository = ToDoRepository(todoDao)
    val getAllData: LiveData<List<ToDoData>> = repository.getAllData
    val sortedDataHigh: LiveData<List<ToDoData>> = repository.sortByHighPriority()
    val sortedDataLow: LiveData<List<ToDoData>> = repository.sortByLowPriority()

    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }

    fun updateData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(toDoData)
        }
    }

    fun deleteItem(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(toDoData)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun searchDatabase(searchQuery: String) = repository.searchDatabase(searchQuery)
}