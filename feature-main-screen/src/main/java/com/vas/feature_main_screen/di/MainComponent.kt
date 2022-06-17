package com.vas.feature_main_screen.di

import androidx.lifecycle.ViewModel
import com.vas.feature_main_screen.navigation.MainNavCommandProvider
import com.vas.feature_main_screen.presentation.MainFragment
import com.vas.feature_main_screen.presentation.MainViewModelFactory
import dagger.Component
import kotlin.properties.Delegates.notNull

@Component(dependencies = [MainDeps::class])
internal interface MainComponent {
    fun inject(fragment: MainFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: MainDeps): Builder

        fun build(): MainComponent
    }
}

interface MainDeps {
    val mainViewModelFactory: MainViewModelFactory
    val mainNavCommandProvider: MainNavCommandProvider
}

internal class MainComponentViewModel : ViewModel(){

    val newMainComponent = DaggerMainComponent.builder().deps(MainDepsProvider.deps).build()
}

interface MainDepsProvider {
    val deps: MainDeps

    companion object : MainDepsProvider by MainDepsStore
}

object MainDepsStore : MainDepsProvider {
    override var deps: MainDeps by notNull()
}