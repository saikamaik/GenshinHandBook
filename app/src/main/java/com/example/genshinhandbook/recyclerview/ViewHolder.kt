package com.example.genshinhandbook.recyclerview

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.genshinhandbook.data.model.CharacterCard
import com.example.genshinhandbook.databinding.RecyclerviewItemBinding

class ViewHolder(
    private val binding: RecyclerviewItemBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("ResourceAsColor")
    fun bind(characterCard: CharacterCard) {

        binding.tvCharacterName.text = characterCard.name
        binding.mainImg.load(characterCard.url)

    }

}