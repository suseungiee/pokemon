package com.seungsu.pokemon.presentation.ext

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

fun NavController.safeNavigate(
    navDestination: NavDirections,
    navOptions: NavOptions? = null
) {
    val action = currentDestination?.getAction(navDestination.actionId) ?: graph.getAction(navDestination.actionId)
    if (action != null && currentDestination?.id != action.destinationId) {
        navigate(navDestination, navOptions)
    }
}
