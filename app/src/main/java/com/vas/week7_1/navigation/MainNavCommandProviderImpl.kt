package com.vas.week7_1.navigation

import com.vas.feature_about_app_screen.AboutAppFragment
import com.vas.feature_details_screen.presentation.DetailsFragment
import com.vas.feature_main_screen.navigation.MainNavCommandProvider
import com.vas.navigation.NavCommand
import com.vas.week7_1.R
import javax.inject.Inject

class MainNavCommandProviderImpl @Inject constructor() : MainNavCommandProvider {
    override val toDetails: NavCommand =
        NavCommand(
            container = R.id.fragmentContainer,
            fragment = DetailsFragment()
        )

    override val toAboutApp: NavCommand =
        NavCommand(
            container = R.id.fragmentContainer,
            fragment = AboutAppFragment()
        )
}