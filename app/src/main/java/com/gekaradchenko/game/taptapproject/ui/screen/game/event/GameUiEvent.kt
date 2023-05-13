package com.gekaradchenko.game.taptapproject.ui.screen.game.event

import com.gekaradchenko.game.taptapproject.base.BaseUiEvent
import com.gekaradchenko.game.taptapproject.ui.data.models.TapTapData

sealed class GameUiEvent : BaseUiEvent() {
    class EventTapTapButton(val data: TapTapData) : GameUiEvent()
    object EventPause : GameUiEvent()
    object EventPlay : GameUiEvent()
    object EventReset : GameUiEvent()
}