package com.example.genshinhandbook.utils

class NameConverter {

    //странная апи, я не знаю как лучше это сделать

    fun nameConverter(name: String): String {
        if (name == "Kamisato Ayaka" || name == "Kamisato Ayato" || name == "Kaedehara Kazuha"
            || name == "Sangonomiya Kokomi" || name == "Kujou Sara"
        ) {
            return name.substringAfter(" ").lowercase()
        } else if (name == "Raiden Shogun") return name.substringBefore(" ").lowercase()
        else if (name.contains(" ")) {
            return name.replace(" ", "-").lowercase()
        }
        return name.lowercase()
    }

}