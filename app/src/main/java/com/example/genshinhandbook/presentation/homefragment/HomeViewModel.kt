package com.example.genshinhandbook.presentation.homefragment

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.genshinhandbook.App
import com.example.genshinhandbook.data.Character
import com.example.genshinhandbook.data.remotedatasource.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var repository: CharacterRepository
) : ViewModel() {

    init {
//        (application as App).getAppComponent().inject(this)
    }

    fun getAllCharacters(): Single<List<Character>> {
        return repository.getAllCharacters()
    }

    fun getAllCharactersNames(): Single<List<String>>{
        return repository.getAllCharactersName()
    }

}