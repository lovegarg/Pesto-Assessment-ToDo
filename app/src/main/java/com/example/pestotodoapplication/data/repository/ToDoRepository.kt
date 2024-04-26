package com.example.pestotodoapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.pestotodoapplication.data.ToDoDao
import com.example.pestotodoapplication.data.models.ToDoData

/**
 * author lgarg on 4/24/24.
 */
class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData) = toDoDao.insertData(toDoData)

    suspend fun updateData(toDoData: ToDoData) = toDoDao.updateData(toDoData)

    suspend fun deleteItem(toDoData: ToDoData) = toDoDao.deleteItem(toDoData)

    suspend fun deleteAll() = toDoDao.deleteAll()

    fun searchDatabase(searchQuery: String) = toDoDao.searchDatabase(searchQuery)

    fun sortByHighPriority() = toDoDao.sortByHighPriority()

    fun sortByLowPriority() = toDoDao.sortByLowPriority()
}