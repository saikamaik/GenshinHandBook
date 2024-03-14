package com.example.genshinhandbook.data.model

data class CharacterDTO(
    val name: String? = "",
    val title: String? = "",
    val vision: String? = "",
    val weapon: String? = "",
    val gender: String? = "",
    val nation: String? = "",
    val affiliation: String? = "",
    val rarity: Int? = null,
    val release: String? = "",
    val constellation: String? = "",
    val birthday: String? = "",
    val description: String? = "",
    val skillTalents: List<Talents?> = listOf(),
    val passiveTalents: List<Talents?> = listOf(),
    val constellations: List<Talents?> = listOf(),
    val visionKey: String? = "",
    val weaponType: String? = ""
)

data class Talents (
    val name: String,
    val unlock: String,
    val description: String,
    val level: Int? = null,
    val type: String? = null
)