package com.gekaradchenko.game.taptapproject.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gekaradchenko.game.taptapproject.ui.data.models.GameSetting
import com.gekaradchenko.game.taptapproject.ui.screen.game.GameScreen
import com.gekaradchenko.game.taptapproject.ui.screen.start.StartScreen

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Screens.Start.route) {

        composable(Screens.Start.route) {
            StartScreen(navController = navHostController)
        }

        composable(
            route = Screens.Game.route,
            arguments = listOf(
                navArgument(GAME_SETTING_WIDTH_ARGUMENT) {
                    type = NavType.IntType
                    defaultValue = GameSetting.DEFAULT_WIDTH
                },
                navArgument(GAME_SETTING_HEIGHT_ARGUMENT) {
                    type = NavType.IntType
                    defaultValue = GameSetting.DEFAULT_HEIGHT
                },
                navArgument(GAME_SETTING_TIMER_ARGUMENT) {
                    type = NavType.IntType
                    defaultValue = GameSetting.DEFAULT_TIMER
                }
            )
        ) {

            val width = it.arguments?.getInt(GAME_SETTING_WIDTH_ARGUMENT)
            val height = it.arguments?.getInt(GAME_SETTING_HEIGHT_ARGUMENT)
            val timer = it.arguments?.getInt(GAME_SETTING_TIMER_ARGUMENT)


            GameScreen(
                navController = navHostController,
                setting = GameSetting(
                    widthCount = width ?: GameSetting.DEFAULT_WIDTH,
                    heightCount = height ?: GameSetting.DEFAULT_HEIGHT,
                    timer = timer ?: GameSetting.DEFAULT_TIMER,
                )
            )
        }
    }

}