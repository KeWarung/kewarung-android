package com.bangkit.kewarung.home.kelola

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.bangkit.kewarung.authentication.data.DataXXX

class MainStoryCallback(
    private val oldList: List<DataXXX>,
    private val newList: List<DataXXX>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldCourse: Int, newPosition: Int): Boolean {
        val (_, value, nameOld) = oldList[oldCourse]
        val (_, value1, nameNew) = newList[newPosition]
        return nameOld == nameNew && value == value1
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}