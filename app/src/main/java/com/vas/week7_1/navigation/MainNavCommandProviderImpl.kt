package com.vas.week7_1.navigation

import com.vas.feature_main_screen.navigation.MainNavCommandProvider
import com.vas.navigation.NavCommand
import com.vas.week7_1.R
import javax.inject.Inject

class MainNavCommandProviderImpl @Inject constructor() : MainNavCommandProvider {
    override val toDetails: NavCommand =
        NavCommand(R.id.action_mainFragment_to_detailsFragment)
}