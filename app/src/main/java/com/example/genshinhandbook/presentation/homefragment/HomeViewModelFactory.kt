package com.example.genshinhandbook.presentation.homefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.genshinhandbook.App
import com.example.genshinhandbook.data.remotedatasource.CharacterRepository
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(
    val app: App,
    val repository: CharacterRepository
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(app, repository) as T
    }

}