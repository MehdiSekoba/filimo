package com.mehdisekoba.filimo.utils.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtils<T>(private val oldList: List<T>, private val newList: List<T>):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(
        oldListPosition: Int,
        newItemPosition: Int,
    ): Boolean {
        return oldList[oldListPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(
        oldListPosition: Int,
        newItemPosition: Int,
    ): Boolean {
        return oldList[oldListPosition] === newList[newItemPosition]
    }
}