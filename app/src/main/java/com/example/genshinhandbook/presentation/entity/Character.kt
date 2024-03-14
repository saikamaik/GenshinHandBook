package com.example.genshinhandbook.presentation.entity

data class Character(
    var id: String? = "",
    val name: String? = "",
    val url: String? = "",
    val rarity: Int? = null,
    val title: String? = "",
    val vision: String? = ""
)

