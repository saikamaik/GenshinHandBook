package com.example.genshinhandbook.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.genshinhandbook.data.model.CharacterCard
import com.example.genshinhandbook.databinding.RecyclerviewItemBinding
import com.example.genshinhandbook.utils.MyDiffUtil

class RecyclerViewAdapter(
    private var characters: List<CharacterCard>,
    private val callback: Callback
) :
    RecyclerView.Adapter<ViewHolder>() {

    interface Callback {
        fun onItemClicked(item: CharacterCard)
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

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(characters[position])
        holder.itemView.setOnClickListener {
            callback.onItemClicked(characters[position])
        }
    }

    fun setData(newCharacterList: List<CharacterCard>) {
        val diffUtil = MyDiffUtil(characters, newCharacterList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        characters = newCharacterList
        diffResults.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}