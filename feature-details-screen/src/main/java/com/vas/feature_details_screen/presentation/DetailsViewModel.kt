package com.vas.feature_details_screen.presentation

import androidx.lifecycle.ViewModel
import com.vas.feature_details_screen.domain.useCase.GetDetailsUseCase

class DetailsViewModel(private val getDetailsUseCase: GetDetailsUseCase) : ViewModel() {

    fun getDetails(name: String) = getDetailsUseCase.execute(name)
}