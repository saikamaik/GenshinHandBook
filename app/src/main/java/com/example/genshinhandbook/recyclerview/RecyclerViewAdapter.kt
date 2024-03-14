package com.example.genshinhandbook.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.genshinhandbook.databinding.RecyclerviewItemBinding
import com.example.genshinhandbook.presentation.entity.Character
import com.example.genshinhandbook.utils.MyDiffUtil

class RecyclerViewAdapter(
    private var characters: List<Character>,
    private val callback: Callback
) :
    RecyclerView.Adapter<ViewHolder>() {

    interface Callback {
        fun onItemClicked(item: Character)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            RecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
        holder.itemView.setOnClickListener {
            callback.onItemClicked(characters[position])
        }
    }

    fun setData(newCharacterList: List<Character>) {
        val diffUtil = MyDiffUtil(characters, newCharacterList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        characters = newCharacterList
        diffResults.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}