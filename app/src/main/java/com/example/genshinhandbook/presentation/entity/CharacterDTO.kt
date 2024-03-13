package com.example.genshinhandbook.presentation.entity

import com.example.genshinhandbook.data.model.Character
import com.example.genshinhandbook.utils.NameConverter

val nameConverter: NameConverter = NameConverter()

data class CharacterDTO(
    val id: String? = "",
    val name: String? = "",
    val url: String? = "",
    val rarity: Int? = null,
    val title: String? = ""
)

fun Character.toCharacterDTO() =
    CharacterDTO(
        id = nameConverter.nameConverter(name),
        name,
        "https://genshin.jmp.blue/characters/${nameConverter.nameConverter(name)}/icon-big",
        rarity,
        title
    )

