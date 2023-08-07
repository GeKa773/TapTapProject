package com.gekaradchenko.game.taptapproject.ui.screen.game.state

import com.gekaradchenko.game.taptapproject.base.BaseUiState
import com.gekaradchenko.game.taptapproject.ui.data.models.TapTapData

data class GameUiState(
    val widthCount: Int = 0,
    val heightCount: Int = 0,
    val isPlay: Boolean = false,
    val timer: String = "00:00",
    val tapTapButtons: List<TapTapData> = emptyList(),
    val isPause: Boolean = false,
    val pauseTimer: String = "",
    val score: Int = 0,
    val gameEnd: Boolean = false
) : BaseUiState() {

    fun getTapTapData(width: Int, height: Int): TapTapData {
        return tapTapButtons.find { it.id == width to height }
            ?: throw NullPointerException("Кнопка не найдена")
    }
}
