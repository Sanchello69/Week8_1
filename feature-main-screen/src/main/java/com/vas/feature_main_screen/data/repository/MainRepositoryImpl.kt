package com.vas.feature_main_screen.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.vas.core.utils.resultLiveData
import com.vas.feature_main_screen.data.local.MainLocalFile
import com.vas.feature_main_screen.data.network.NetworkClient
import com.vas.feature_main_screen.domain.model.Hero
import com.vas.feature_main_screen.domain.repository.MainRepository
import com.vas.core.utils.Result

class MainRepositoryImpl(private val networkClient: NetworkClient, private val mainLocalFile: MainLocalFile)
    : MainRepository {

    private val resultLiveData = resultLiveData(
        fileQuery = {mainLocalFile.getHeroesFile()},
        networkCall = {networkClient.getHeroesList()},
        saveCallResult = {mainLocalFile.saveHeroesFile(it)}
    ).map { it ->
        Result<List<Hero>>(
            status = it.status,
            data = it.data?.map {
                Hero(
                    id = it.id,
                    name = it.name,
                    urlIcon = it.urlIcon
                )
            },
            message = it.message
        )
    }

    override fun getHeroesListResult() : LiveData<Result<List<Hero>>>  {
            return resultLiveData
        }
}