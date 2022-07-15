package com.example.marvelcompose.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.marvelcompose.ui.navigation.NavItem
import com.example.marvelcompose.ui.navigation.navigatePopInUpToStartDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberMarveAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): MarvelAppState = remember(scaffoldState, navController, coroutineScope) {
    MarvelAppState(scaffoldState, navController, coroutineScope)
}

class MarvelAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
) {
    fun onUpClick() {
        navController.popBackStack()
    }

    fun onMenuClick() {
        coroutineScope.launch { scaffoldState.drawerState.open() }
    }

    fun onNavItemClick(item: NavItem) {
        navController.navigatePopInUpToStartDestination(item.navCommand.route)
    }

    fun onDrawerOptionClick(navItem: NavItem) {
        coroutineScope.launch { scaffoldState.drawerState.close() }
        onNavItemClick(navItem)
    }

    companion object {
        val DRAWER_OPTIONS = listOf(NavItem.HOME, NavItem.SETTINGS)
        val BOTTOM_NAV_OPTIONS = listOf(NavItem.CHARACTERS, NavItem.COMICS, NavItem.EVENTS)
    }

    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""
    val showUpNavigation: Boolean
        @Composable get() = currentRoute !in NavItem.values().map { it.navCommand.route }

    val showBottomNavigation: Boolean
        @Composable get() = BOTTOM_NAV_OPTIONS.any { currentRoute.contains(it.navCommand.feature.route) }

    val drawerSelectedIndex: Int
        @Composable get() =
            if (showBottomNavigation) DRAWER_OPTIONS.indexOf(NavItem.HOME)
            else DRAWER_OPTIONS.indexOfFirst { it.navCommand.route == currentRoute }
}