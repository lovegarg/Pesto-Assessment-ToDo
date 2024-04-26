package com.example.pestotodoapplication.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.pestotodoapplication.data.models.ToDoData

/**
 * author lgarg on 4/24/24.
 */
class PrestoDiffUtil(
    private val oldList: List<ToDoData>,
    private val newList: List<ToDoData>
): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ) = oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ) = oldList[oldItemPosition] == newList[newItemPosition]
}