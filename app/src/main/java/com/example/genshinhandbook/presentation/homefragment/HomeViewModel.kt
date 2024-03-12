package com.example.genshinhandbook.presentation.homefragment

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.genshinhandbook.data.model.Character
import com.example.genshinhandbook.data.model.CharacterCard
import com.example.genshinhandbook.data.remotedatasource.CharacterRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: CharacterRepositoryImpl
) : ViewModel() {

    private val _charactersList = MutableStateFlow<List<Character>>(listOf())
    val charactersList = _charactersList

    private val _charactersPhotoList = MutableStateFlow<List<CharacterCard>>(listOf())
    val charactersPhotoList = _charactersPhotoList

    private val _navigateToItemInfo = MutableStateFlow<String?>(null)
    val navigateToItemInfo = _navigateToItemInfo

    @SuppressLint("CheckResult")
    fun getAllCharacters() {
        repository.getAllCharacters().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _charactersList.value = it
                _charactersPhotoList.value = _charactersList.value.map { character ->
                    CharacterCard(
                        nameConverter(character.name),
                        character.name,
                        "https://genshin.jmp.blue/characters/${nameConverter(character.name)}/icon-big",
                        character.rarity
                    )
                }
            }, {
                it.printStackTrace()
            })
    }

    fun doneNavigating() {
        _navigateToItemInfo.value = null
    }

    fun onCardClicked(item: String) {
        _navigateToItemInfo.value = item
    }

    // Странная апи, я не знаю как лучше реализовать

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