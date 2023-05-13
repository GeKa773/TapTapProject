package com.gekaradchenko.game.taptapproject.ui.screen.game.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gekaradchenko.game.taptapproject.base.BaseViewModel
import com.gekaradchenko.game.taptapproject.ext.toTime
import com.gekaradchenko.game.taptapproject.timer.GameTimer
import com.gekaradchenko.game.taptapproject.timer.GameTimerImpl
import com.gekaradchenko.game.taptapproject.ui.data.models.TapTapData
import com.gekaradchenko.game.taptapproject.ui.screen.game.event.GameUiEvent
import com.gekaradchenko.game.taptapproject.ui.screen.game.state.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel : BaseViewModel<GameUiState, GameUiEvent>() {

    override val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private val timer: GameTimer = GameTimerImpl()


    override fun sendUiEvent(event: GameUiEvent) {
        when (event) {
            is GameUiEvent.EventTapTapButton -> {
                Log.e("TTTTTTT", "EventTapTapButton = ${event.data.id}")
            }

            is GameUiEvent.EventPause -> {}
            is GameUiEvent.EventPlay -> {
                if (!uiState.value.isPlay) {
                    startTimer()
                    startGame()
                } else {

                }
            }

            is GameUiEvent.EventReset -> {}
        }
    }


    private fun initTimer() {
        timer.event = TimerEvent()
    }

    private fun startTimer() {
        initTimer()
        viewModelScope.launch {
            timer.start(10)
        }
    }

    private fun startGame(width: Int = 5, height: Int = 8) {
        buttonGeneration(width, height)
        _uiState.value = uiState.value.copy(widthCount = width, heightCount = height, isPlay = true)
    }

    private fun buttonGeneration(width: Int? = null, height: Int? = null) {
        val thisWidth = width ?: uiState.value.widthCount
        val thisHeight = height ?: uiState.value.heightCount
        val activeButtonId = Pair((0 until thisWidth).random(), (0 until thisHeight).random())
        Log.e("TTTTTTT", "activeButtonId = $activeButtonId")
        val list = mutableListOf<TapTapData>().apply {
            repeat(thisWidth) { first ->
                repeat(thisHeight) { second ->
                    val buttonId = first to second
                    add(
                        TapTapData(
                            id = buttonId,
                            isActive = buttonId == activeButtonId,
                            color = null,
                            text = null
                        )
                    )
                }
            }
        }

        _uiState.value = uiState.value.copy(tapTapButtons = list)
    }


    inner class TimerEvent : GameTimer.TimerEvent {
        override fun timeChange(time: Int) {
            _uiState.value = uiState.value.copy(timer = time.toTime())
        }

        override fun end() {
            _uiState.value = uiState.value.copy(timer = "End")
        }
    }
}