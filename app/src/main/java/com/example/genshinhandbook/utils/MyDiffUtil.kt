package com.example.genshinhandbook.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.genshinhandbook.presentation.entity.Character

class MyDiffUtil(
    private val oldList: List<Character>,
    private val newList: List<Character>
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

            oldList[oldItemPosition].rarity != newList[newItemPosition].rarity
            -> {
                false
            }

            else -> true
        }
    }
}
