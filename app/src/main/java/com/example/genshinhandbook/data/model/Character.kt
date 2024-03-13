package com.example.genshinhandbook.data.model

data class Character(
    val name: String,
    val title: String,
    val vision: String,
    val weapon: String,
    val gender: String,
    val nation: String,
    val affiliation: String,
    val rarity: Int,
    val release: String,
    val constellation: String,
    val birthday: String,
    val description: String,
    val skillTalents: List<Talents>,
    val passiveTalents: List<Talents>,
    val constellations: List<Talents>,
    val visionKey: String,
    val weaponType: String
)

data class Talents (
    val name: String,
    val unlock: String,
    val description: String,
    val level: Int? = null,
    val type: String? = null
)