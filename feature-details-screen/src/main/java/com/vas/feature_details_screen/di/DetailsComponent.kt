package com.vas.feature_details_screen.di

import androidx.lifecycle.ViewModel
import com.vas.feature_details_screen.presentation.DetailsFragment
import com.vas.feature_details_screen.presentation.DetailsViewModelFactory
import dagger.Component
import kotlin.properties.Delegates

@Component(dependencies = [DetailsDeps::class])
internal interface DetailsComponent {
    fun inject(fragment: DetailsFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: DetailsDeps): Builder

        fun build(): DetailsComponent
    }
}

interface DetailsDeps {
    val detailsViewModelFactory: DetailsViewModelFactory
}

internal class DetailsComponentViewModel : ViewModel(){

    val newDetailsComponent = DaggerDetailsComponent.builder().deps(DetailsDepsProvider.deps).build()
}

interface DetailsDepsProvider {
    val deps: DetailsDeps

    companion object : DetailsDepsProvider by DetailsDepsStore
}

object DetailsDepsStore : DetailsDepsProvider {
    override var deps: DetailsDeps by Delegates.notNull()
}