package com.example.genshinhandbook.utils

interface Mapper<D, E> {

    fun fromEntity(entity: E): D
    fun toEntity(data: D): E

}