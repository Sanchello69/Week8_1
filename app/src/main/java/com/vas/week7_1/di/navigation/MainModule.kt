package com.vas.week7_1.di.navigation

import com.vas.feature_main_screen.navigation.MainNavCommandProvider
import com.vas.week7_1.navigation.MainNavCommandProviderImpl
import dagger.Binds
import dagger.Module

@Module
interface MainModule{
    @Binds
    fun bindMainNavigator(impl: MainNavCommandProviderImpl): MainNavCommandProvider
}