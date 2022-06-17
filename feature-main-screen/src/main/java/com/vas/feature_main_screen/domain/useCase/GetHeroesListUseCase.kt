package com.vas.feature_main_screen.domain.useCase

import androidx.lifecycle.LiveData
import com.vas.core.utils.Result
import com.vas.feature_main_screen.domain.model.Hero
import com.vas.feature_main_screen.domain.repository.MainRepository

class GetHeroesListUseCase(private val mainRepository: MainRepository) {

    fun execute(): LiveData<Result<List<Hero>>> {
        return mainRepository.getHeroesListResult()
    }
}