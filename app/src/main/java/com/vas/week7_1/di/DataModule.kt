package com.vas.week7_1.di

import android.content.Context
import com.vas.feature_details_screen.data.local.DetailsLocalFile
import com.vas.feature_details_screen.data.network.NetworkClientDetails
import com.vas.feature_details_screen.data.repository.DetailsRepositoryImpl
import com.vas.feature_details_screen.domain.repository.DetailsRepository
import com.vas.feature_main_screen.data.local.MainLocalFile
import com.vas.feature_main_screen.data.network.NetworkClient
import com.vas.feature_main_screen.data.repository.MainRepositoryImpl
import com.vas.feature_main_screen.domain.repository.MainRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideNetworkClient(): NetworkClient{
        return NetworkClient()
    }

    @Provides
    fun provideMainRepository(networkClient: NetworkClient, mainLocalFile: MainLocalFile): MainRepository {
        return MainRepositoryImpl(networkClient = networkClient, mainLocalFile = mainLocalFile)
    }

    @Provides
    fun provideMainLocalFile(context: Context): MainLocalFile {
        return MainLocalFile(context)
    }

    @Provides
    fun provideNetworkClientDetails(): NetworkClientDetails{
        return NetworkClientDetails()
    }

    @Provides
    fun provideDetailsRepository(networkClient: NetworkClientDetails,
                                 detailsLocalFile: DetailsLocalFile): DetailsRepository {
        return DetailsRepositoryImpl(networkClient = networkClient,
            detailsLocalFile = detailsLocalFile)
    }

    @Provides
    fun provideDetailsLocalFile(context: Context): DetailsLocalFile {
        return DetailsLocalFile(context)
    }
}