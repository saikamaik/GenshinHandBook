package com.example.genshinhandbook.presentation.iteminfofragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.genshinhandbook.data.remotedatasource.CharacterRepository
import javax.inject.Inject

class ItemInfoViewModelFactory @Inject constructor(
    private val repository: CharacterRepository
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemInfoViewModel(repository) as T
    }

}
