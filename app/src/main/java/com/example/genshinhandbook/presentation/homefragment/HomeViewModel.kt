package com.example.genshinhandbook.presentation.homefragment

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.genshinhandbook.data.remotedatasource.CharacterRepositoryImpl
import com.example.genshinhandbook.presentation.entity.Character
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: CharacterRepositoryImpl
) : ViewModel() {

    private val _charactersPhotoList = MutableStateFlow<List<Character>>(listOf())
    val charactersPhotoList = _charactersPhotoList

    private val _navigateToItemInfo = MutableStateFlow<String?>(null)
    val navigateToItemInfo = _navigateToItemInfo

    private var charactersNameList = listOf<String>()

    @SuppressLint("CheckResult")
    fun getAllCharactersDTO() {
        repository.getAllCharactersName().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                charactersNameList = it
                repository.getAllCharacters()
            }
            .subscribe { characterDTOList ->
                characterDTOList.map { character ->
                    for (name in charactersNameList) {
                        if (character.name?.contains(name.replace("-", " "), true) == true) {
                            character.id = name
                        }
                    }
                }
                _charactersPhotoList.value = characterDTOList
            }
    }

    fun doneNavigating() {
        _navigateToItemInfo.value = null
    }

    fun onCardClicked(item: String) {
        _navigateToItemInfo.value = item
    }
}