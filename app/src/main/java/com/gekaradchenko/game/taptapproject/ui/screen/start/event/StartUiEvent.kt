package com.gekaradchenko.game.taptapproject.ui.screen.start.event

import com.gekaradchenko.game.taptapproject.base.BaseUiEvent

sealed class StartUiEvent : BaseUiEvent() {
    object EventStart : StartUiEvent()
    sealed class EventSettingButtons : StartUiEvent() {
        object WidthPlus : EventSettingButtons()
        object WidthMinus : EventSettingButtons()
        object HeightPlus : EventSettingButtons()
        object HeightMinus : EventSettingButtons()
        object TimerPlus : EventSettingButtons()
        object TimerMinus : EventSettingButtons()
    }
}

