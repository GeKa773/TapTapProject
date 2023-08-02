package com.gekaradchenko.game.taptapproject.ui.data.models

data class GameSetting(
    val widthCount: Int,
    val heightCount: Int,
    val timer: Int
) {

    companion object {

        const val DEFAULT_WIDTH = 3
        const val DEFAULT_HEIGHT = 5
        const val DEFAULT_TIMER = 30


        fun getDefault() = GameSetting(
            widthCount = DEFAULT_WIDTH,
            heightCount = DEFAULT_HEIGHT,
            timer = DEFAULT_TIMER
        )
    }
}
