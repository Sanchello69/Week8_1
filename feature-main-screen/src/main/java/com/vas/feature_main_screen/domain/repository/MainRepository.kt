package com.vas.feature_main_screen.domain.repository

import androidx.lifecycle.LiveData
import com.vas.core.utils.Result
import com.vas.feature_main_screen.domain.model.Hero

interface MainRepository {
    fun getHeroesListResult() : LiveData<Result<List<Hero>>>
}