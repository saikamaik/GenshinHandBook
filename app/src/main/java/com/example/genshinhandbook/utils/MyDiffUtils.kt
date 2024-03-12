package com.example.genshinhandbook.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.genshinhandbook.data.model.CharacterCard

class MyDiffUtil(
    private val oldList: List<CharacterCard>,
    private val newList: List<CharacterCard>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                false
            }

            oldList[oldItemPosition].url != newList[newItemPosition].url
            -> {
                false
            }

            else -> true
        }
    }
}
