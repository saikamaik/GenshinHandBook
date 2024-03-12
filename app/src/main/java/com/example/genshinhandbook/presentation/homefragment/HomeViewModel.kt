package com.example.genshinhandbook.presentation.homefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.genshinhandbook.App
import com.example.genshinhandbook.data.model.Character
import com.example.genshinhandbook.data.remotedatasource.CharacterRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val app: App,
    val repository: CharacterRepository
) : ViewModel() {

    fun getAllCharacters(): Single<List<Character>> {
        return repository.getAllCharacters()
    }

    fun getAllCharactersNames(): Single<List<String>> {
        return repository.getAllCharactersName()
    }

    private val _navigateToItemInfo = MutableLiveData<String?>()
    val navigateToItemInfo: LiveData<String?>
        get() = _navigateToItemInfo

    fun doneNavigating() {
        _navigateToItemInfo.value = null
    }

    fun onCardClicked(item: String) {
        _navigateToItemInfo.value = item
    }

}