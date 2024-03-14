package com.example.genshinhandbook.data.mapper

import com.example.genshinhandbook.data.model.CharacterDTO
import com.example.genshinhandbook.presentation.entity.Character
import com.example.genshinhandbook.utils.Const.BASE_URL
import com.example.genshinhandbook.utils.Const.CHARACTERS
import com.example.genshinhandbook.utils.Mapper

class CharacterMapper : Mapper<CharacterDTO, Character> {
    override fun fromEntity(entity: Character): CharacterDTO {
        return CharacterDTO(
            entity.name,
            entity.title,
            entity.vision
        )
    }

    override fun toEntity(data: CharacterDTO): Character {
        return Character(
            id = "",
            data.name,
            url = BASE_URL + CHARACTERS,
            data.rarity,
            data.title,
            data.vision
        )
    }


}