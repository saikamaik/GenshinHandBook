package com.example.genshinhandbook.presentation.iteminfofragment

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.genshinhandbook.data.remotedatasource.CharacterRepositoryImpl
import com.example.genshinhandbook.presentation.entity.CharacterDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@SuppressLint("CheckResult")
class ItemInfoViewModel @Inject constructor(
    private val repository: CharacterRepositoryImpl
) : ViewModel() {

    private val _characterData = MutableStateFlow<CharacterDTO?>(null)
    val characterData = _characterData

    fun getOneCharacter(id: String) {
        repository.getOneCharacterData(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _characterData.value = it
            }, {
                it.printStackTrace()
            })
    }

}