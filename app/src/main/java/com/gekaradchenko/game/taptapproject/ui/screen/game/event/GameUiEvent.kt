package com.gekaradchenko.game.taptapproject.ui.screen.game.event

import com.gekaradchenko.game.taptapproject.base.BaseUiEvent
import com.gekaradchenko.game.taptapproject.ui.data.models.GameSetting
import com.gekaradchenko.game.taptapproject.ui.data.models.TapTapData

sealed class GameUiEvent : BaseUiEvent() {

    class EventSetGameSetting(val setting: GameSetting) : GameUiEvent()
    class EventTapTapButton(val data: TapTapData) : GameUiEvent()
    object EventPause : GameUiEvent()
    class EventPlay(val setting: GameSetting) : GameUiEvent()
    object EventReset : GameUiEvent()
    class RestartGame(val setting: GameSetting) : GameUiEvent()
}