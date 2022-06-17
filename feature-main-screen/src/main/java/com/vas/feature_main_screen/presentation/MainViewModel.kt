package com.vas.feature_main_screen.presentation

import androidx.lifecycle.ViewModel
import com.vas.feature_main_screen.domain.useCase.GetHeroesListUseCase

class MainViewModel(private val getHeroesListUseCase: GetHeroesListUseCase) : ViewModel() {

    val heroesList = getHeroesListUseCase.execute()

}