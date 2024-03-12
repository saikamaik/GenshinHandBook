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

data class CharacterCard(
    val id: String,
    val name: String,
    val url: String,
    val rarity: Int
)

//data class SkillTalent (
//    val name: String,
//    val unlock: String,
//    val description: String,
//    val upgrades: List<Upgrade>,
//    val type: String
//)
//
//data class Upgrade (
//    val name: String,
//    val value: String
//)