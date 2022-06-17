package com.vas.feature_main_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vas.feature_main_screen.domain.useCase.GetHeroesListUseCase

class MainViewModelFactory(val getHeroesListUseCase: GetHeroesListUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                getHeroesListUseCase = getHeroesListUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}