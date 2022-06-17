package com.vas.navigation

import androidx.fragment.app.Fragment

data class NavCommand(
    val container: Int,
    val fragment: Fragment
)