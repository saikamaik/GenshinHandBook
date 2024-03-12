package com.example.genshinhandbook.presentation.iteminfofragment

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.genshinhandbook.data.model.Character
import com.example.genshinhandbook.data.remotedatasource.CharacterRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("CheckResult")
class ItemInfoViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _characterData = MutableLiveData<Character>()

    fun getOneCharacter(id: String): LiveData<Character> {
        repository.getOneCharacterData(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _characterData.value = it
            }, {
                it.printStackTrace()
            })
        return _characterData
    }

}