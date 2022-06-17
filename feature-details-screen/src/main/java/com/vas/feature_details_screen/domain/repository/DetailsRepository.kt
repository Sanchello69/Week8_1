package com.vas.feature_details_screen.domain.repository

import androidx.lifecycle.LiveData
import com.vas.core.utils.Result
import com.vas.feature_details_screen.domain.model.Hero

interface DetailsRepository {
    fun getDetailsResult(name: String) : LiveData<Result<Hero>>
}