package com.example.pestotodoapplication.fragment

import android.view.View
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.pestotodoapplication.R
import com.example.pestotodoapplication.data.models.Priority
import com.example.pestotodoapplication.data.models.ToDoData
//import com.example.pestotodoapplication.fragment.list.ListFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * author lgarg on 4/24/24.
 */
class BindingAdapters {

    companion object {
        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if (navigate) {
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }

        @BindingAdapter("android:sendDataToUpdateFragment")
        @JvmStatic
        fun navigateToAddFragment(view: ConstraintLayout, currentItem: ToDoData) {
            view.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                view.findNavController().navigate(action)
            }
        }

        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>) {
            when (emptyDatabase.value) {
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
                null -> view.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("android:parsePriorityToInt")
        @JvmStatic
        fun parsePriorityToInt(spinner: Spinner, priority: Priority) {
            when (priority) {
                Priority.High -> spinner.setSelection(0)
                Priority.Medium -> spinner.setSelection(1)
                Priority.Low -> spinner.setSelection(2)
            }
        }

        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(cardView: CardView, priority: Priority) {
            when (priority) {
                Priority.High -> cardView.setCardBackgroundColor(
                    cardView.context.getColor(
                        R.color.red
                    )
                )
                Priority.Medium -> cardView.setCardBackgroundColor(
                    cardView.context.getColor(
                        R.color.yellow
                    )
                )
                Priority.Low -> cardView.setCardBackgroundColor(
                    cardView.context.getColor(
                        R.color.green
                    )
                )
            }
        }
    }
}