package com.example.genshinhandbook.presentation.homefragment

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.genshinhandbook.data.remotedatasource.CharacterRepositoryImpl
import com.example.genshinhandbook.presentation.entity.CharacterDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: CharacterRepositoryImpl
) : ViewModel() {

    private val _charactersPhotoList = MutableStateFlow<List<CharacterDTO>>(listOf())
    val charactersPhotoList = _charactersPhotoList

    private val _navigateToItemInfo = MutableStateFlow<String?>(null)
    val navigateToItemInfo = _navigateToItemInfo

    @SuppressLint("CheckResult")
    fun getAllCharacters() {
        repository.getAllCharacters().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _charactersPhotoList.value = it
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

}