package com.example.marvelcompose.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.marvelcompose.ui.screens.characters.CharactersScreen
import com.example.marvelcompose.ui.screens.charactersdetail.CharacterDetailScreen

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Feature.CHARACTERS.route
    ) {
        charactersNav(navController)
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
private fun NavGraphBuilder.charactersNav(navController: NavController) {
    navigation(
        startDestination = NavItem.ContentType(Feature.CHARACTERS).route,
        route = Feature.CHARACTERS.route
    ){
        composable(NavItem.ContentType(Feature.CHARACTERS)) {
            CharactersScreen(
                onClick = { character ->
                    navController.navigate(
                        NavItem.ContentDetail(Feature.CHARACTERS).createRoute(character.id)
                    )
                }
            )
        }
        composable(NavItem.ContentDetail(Feature.CHARACTERS)) {
            CharacterDetailScreen(
                id = it.finArg(NavArg.ItemId),
                onUpClick = { navController.popBackStack() })
        }
    }
}

private fun NavGraphBuilder.composable(
    navCommand: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.finArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}