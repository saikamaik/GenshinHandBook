package com.example.genshinhandbook.presentation.homefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.genshinhandbook.data.remotedatasource.CharacterRepositoryImpl
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(
    private val repository: CharacterRepositoryImpl
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }

}