package com.vas.feature_details_screen.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.vas.core.utils.Result
import com.vas.core.utils.resultLiveData
import com.vas.feature_details_screen.data.local.DetailsLocalFile
import com.vas.feature_details_screen.data.network.NetworkClientDetails
import com.vas.feature_details_screen.domain.model.Hero
import com.vas.feature_details_screen.domain.repository.DetailsRepository

class DetailsRepositoryImpl(private val networkClient: NetworkClientDetails,
                            private val detailsLocalFile: DetailsLocalFile) : DetailsRepository {

    override fun getDetailsResult(name: String): LiveData<Result<Hero>> {

        val resultDetailsLiveData = resultLiveData(
            fileQuery = {detailsLocalFile.getHeroesDetailsFile(name)},
            networkCall = {networkClient.getDetailsHero(name)},
            saveCallResult = {detailsLocalFile.saveHeroesDetailsFile(it)}
        )

        return resultDetailsLiveData.map {
            Result(
                status = it.status,
                message = it.message,
                data = it.data?.let { heroData ->
                    Hero(
                        id = heroData.id,
                        name = heroData.name,
                        urlImg = heroData.urlImg,
                        attackType = heroData.attackType,
                        roles = heroData.roles,
                        health = heroData.health,
                        healthRegen = heroData.healthRegen,
                        mana = heroData.mana,
                        manaRegen = heroData.manaRegen,
                        armor = heroData.armor,
                        str = heroData.str,
                        strGain = heroData.strGain,
                        agi = heroData.agi,
                        agiGain = heroData.agiGain,
                        int = heroData.int,
                        intGain = heroData.intGain,
                        attackMin = heroData.attackMin,
                        attackMax = heroData.attackMax
                    )
                }
            )
        }
    }
}