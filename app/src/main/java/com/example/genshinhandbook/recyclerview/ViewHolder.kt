package com.example.genshinhandbook.recyclerview

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.genshinhandbook.R
import com.example.genshinhandbook.databinding.RecyclerviewItemBinding
import com.example.genshinhandbook.presentation.entity.Character
import com.example.genshinhandbook.utils.Const.ICON_BIG

class ViewHolder(
    private val binding: RecyclerviewItemBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(characterCard: Character) {

        binding.tvCharacterName.text = characterCard.name
        binding.mainImg.load(characterCard.url + characterCard.id + ICON_BIG)

        if (characterCard.rarity != 5) {
            binding.mainImg.setBackgroundResource(R.color.purple)
        }
        else {
            binding.mainImg.setBackgroundResource(R.color.gold)
        }
    }

}