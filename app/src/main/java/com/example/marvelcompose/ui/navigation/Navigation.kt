package com.example.marvelcompose.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelcompose.ui.screens.characters.CharactersScreen
import com.example.marvelcompose.ui.screens.charactersdetail.CharacterDetailScreen

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavItem.Characters.route) {
        composable(NavItem.Characters) {
            CharactersScreen(onClick = { character ->
                navController.navigate(NavItem.CharacterDetail.createRoute(character.id))
            })
        }
        composable(NavItem.CharacterDetail) {
            CharacterDetailScreen(id = it.finArg(NavArg.ItemId))
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