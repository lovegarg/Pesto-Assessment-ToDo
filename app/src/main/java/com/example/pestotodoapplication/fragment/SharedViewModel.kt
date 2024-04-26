package com.example.pestotodoapplication.fragment

import android.app.Application
import android.content.res.Resources
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pestotodoapplication.R
import com.example.pestotodoapplication.data.models.Priority
import com.example.pestotodoapplication.data.models.ToDoData

/**
 * author lgarg on 4/24/24.
 */
class SharedViewModel (application: Application): AndroidViewModel(application) {

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(false)

    fun isDatabaseEmpty(toDoData: List<ToDoData>) {
        emptyDatabase.value = toDoData.isEmpty()
    }

    val listener: AdapterView.OnItemSelectedListener = object:
        AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            when(position) {
                0 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))}
                1 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))}
                2 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))}
            }
        }

        override fun onNothingSelected(
            parent: AdapterView<*>?
        ) {

        }
    }

    fun verifyDataFromUser(title: String, desc: String): Boolean {
        return !(title.isNullOrEmpty() || desc.isNullOrEmpty())
    }

    fun parsePriorityString(priority: String): Priority {
        return when(priority) {
            Resources.getSystem().getString(R.string.priority_high) -> Priority.High
            Resources.getSystem().getString(R.string.priority_low) -> Priority.Low
            Resources.getSystem().getString(R.string.priority_medium) -> Priority.Medium
            else -> Priority.Low
        }
    }
}