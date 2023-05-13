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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val timer: GameTimer
) : BaseViewModel<GameUiState, GameUiEvent>() {

    override val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var count = 0
        set(value) {
            val result = if (value < 0) 0 else value
            field = result
            _uiState.value = uiState.value.copy(score = result)
        }
    private var activeButtonId: Pair<Int, Int>? = null
    private var lastActiveButtonId: Pair<Int, Int>? = null


    private var buttonsList: MutableList<TapTapData>? = null


    init {
        initTimer()
    }

    override fun sendUiEvent(event: GameUiEvent) {
        when (event) {
            is GameUiEvent.EventTapTapButton -> {
                Log.e("TTTTTTT", "EventTapTapButton = ${event.data.id}")
                if (event.data.id == activeButtonId) count++ else count -= 2
                buttonGeneration()
            }

            is GameUiEvent.EventPause -> {
                setPause(true)
            }

            is GameUiEvent.EventPlay -> {
                if (!uiState.value.isPlay) {
                    startGame()
                    startTimer()
                } else {
                    setPause(false)
                }
            }

            is GameUiEvent.EventReset -> {}
        }
    }

    private fun setPause(pause: Boolean) {
        viewModelScope.launch {
            if (uiState.value.isPlay) {
                if (pause) {
                    timer.pause()
                } else {
                    timer.continueTimer()
                }
                _uiState.value = uiState.value.copy(isPause = pause)
            }
        }
    }

    private fun initTimer() {
        timer.event = TimerEvent()
    }

    private fun startTimer() {
        viewModelScope.launch {
            timer.start(15)
        }
    }

    private fun startGame(width: Int = 5, height: Int = 8) {
        count = 0
        buttonGeneration(width, height)
        _uiState.value = uiState.value.copy(widthCount = width, heightCount = height, isPlay = true, pauseTimer = "")
    }

    private fun buttonGeneration(width: Int? = null, height: Int? = null) {
        val thisWidth = width ?: uiState.value.widthCount
        val thisHeight = height ?: uiState.value.heightCount
        activeButtonId = Pair((0 until thisWidth).random(), (0 until thisHeight).random())

        if (buttonsList.isNullOrEmpty()) {
            buttonsList = mutableListOf<TapTapData>().apply {
                repeat(thisWidth) { first ->
                    repeat(thisHeight) { second ->
                        val buttonId = first to second
                        add(
                            TapTapData(
                                id = buttonId,
                                color = null,
                                text = null
                            ).apply {
                                isActive = buttonId == activeButtonId
                            }
                        )
                    }
                }
            }
        } else {
            buttonsList!!.filter { it.id == activeButtonId || it.id == lastActiveButtonId }.forEach {
                if (it.id == lastActiveButtonId) it.isActive = false
                if (it.id == activeButtonId) it.isActive = true
            }
        }

        _uiState.value = uiState.value.copy(tapTapButtons = buttonsList!!)
        lastActiveButtonId = activeButtonId
    }


    inner class TimerEvent : GameTimer.TimerEvent {
        override fun timeChange(time: Int) {
            _uiState.value = uiState.value.copy(timer = time.toTime())
        }

        override fun end() {
            _uiState.value = uiState.value.copy(pauseTimer = "$count", isPlay = false)
        }
    }
}