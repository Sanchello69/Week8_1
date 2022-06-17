package com.vas.feature_details_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vas.feature_details_screen.domain.useCase.GetDetailsUseCase

class DetailsViewModelFactory(val getDetailsUseCase: GetDetailsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(
                getDetailsUseCase = getDetailsUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}