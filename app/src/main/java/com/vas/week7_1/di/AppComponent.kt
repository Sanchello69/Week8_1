package com.vas.week7_1.di

import com.vas.feature_details_screen.di.DetailsDeps
import com.vas.feature_details_screen.presentation.DetailsViewModelFactory
import com.vas.feature_main_screen.di.MainDeps
import com.vas.feature_main_screen.navigation.MainNavCommandProvider
import com.vas.feature_main_screen.presentation.MainViewModelFactory
import com.vas.week7_1.MainActivity
import com.vas.week7_1.di.navigation.MainModule
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class,
MainModule::class])
interface AppComponent : MainDeps, DetailsDeps {

    override val mainViewModelFactory: MainViewModelFactory
    override val mainNavCommandProvider: MainNavCommandProvider

    override val detailsViewModelFactory: DetailsViewModelFactory

    fun inject(mainActivity: MainActivity)
}