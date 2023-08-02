package com.gekaradchenko.game.taptapproject.ui.screen.start.state

import com.gekaradchenko.game.taptapproject.base.BaseUiState
import com.gekaradchenko.game.taptapproject.ui.data.models.GameSetting

data class StartUiState(
    val gameSetting: GameSetting = GameSetting.getDefault()
) : BaseUiState() {
    companion object {
        const val MAX_WIDTH = 15
        const val MAX_HEIGHT = 20
        const val MAX_TIMER = 60
        const val MIN_TIMER = 5
    }
}


fun StartUiState.widthPlus(): StartUiState {
    val width = this.gameSetting.widthCount + 1
    return if (width > StartUiState.MAX_WIDTH) this else this.copy(gameSetting = this.gameSetting.copy(widthCount = width))
}

fun StartUiState.widthMinus(): StartUiState {
    val width = this.gameSetting.widthCount - 1
    return if (width < 1) return this else this.copy(gameSetting = this.gameSetting.copy(widthCount = width))
}

fun StartUiState.heightPlus(): StartUiState {
    val height = this.gameSetting.heightCount + 1
    return if (height > StartUiState.MAX_HEIGHT) this else this.copy(gameSetting = this.gameSetting.copy(heightCount = height))
}

fun StartUiState.heightMinus(): StartUiState {
    val height = this.gameSetting.heightCount - 1
    return if (height < 1) this else this.copy(gameSetting = this.gameSetting.copy(heightCount = height))
}

fun StartUiState.timerPlus(): StartUiState {
    val time = this.gameSetting.timer + 5
    return if (time > StartUiState.MAX_TIMER) this else this.copy(gameSetting = this.gameSetting.copy(timer = time))
}

fun StartUiState.timerMinus(): StartUiState {
    val time = this.gameSetting.timer - 5
    return if (time < StartUiState.MIN_TIMER) this else this.copy(gameSetting = this.gameSetting.copy(timer = time))
}