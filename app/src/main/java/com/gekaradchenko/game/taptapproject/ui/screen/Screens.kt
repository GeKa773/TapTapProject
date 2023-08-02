package com.gekaradchenko.game.taptapproject.ui.screen

import com.gekaradchenko.game.taptapproject.ui.data.models.GameSetting

sealed class Screens(val route: String) {
    object Start : Screens(route = "start_screen")
    object Game :
        Screens(route = "game_screen?width={$GAME_SETTING_WIDTH_ARGUMENT}&height={$GAME_SETTING_HEIGHT_ARGUMENT}&timer={$GAME_SETTING_TIMER_ARGUMENT}") {
        fun gameSettings(setting: GameSetting): String {
            return "game_screen?width=${setting.widthCount}&height=${setting.heightCount}&timer=${setting.timer}"
        }
    }
}

const val GAME_SETTING_ARGUMENT = "game_setting_argument"
const val GAME_SETTING_WIDTH_ARGUMENT = "game_setting_width_argument"
const val GAME_SETTING_HEIGHT_ARGUMENT = "game_setting_height_argument"
const val GAME_SETTING_TIMER_ARGUMENT = "game_setting_timer_argument"
