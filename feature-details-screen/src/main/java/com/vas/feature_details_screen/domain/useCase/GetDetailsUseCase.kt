package com.vas.feature_details_screen.domain.useCase

import androidx.lifecycle.LiveData
import com.vas.core.utils.Result
import com.vas.feature_details_screen.domain.model.Hero
import com.vas.feature_details_screen.domain.repository.DetailsRepository

class GetDetailsUseCase(private val detailsRepository: DetailsRepository) {

    fun execute(name: String): LiveData<Result<Hero>> {
        return detailsRepository.getDetailsResult(name)
    }
}