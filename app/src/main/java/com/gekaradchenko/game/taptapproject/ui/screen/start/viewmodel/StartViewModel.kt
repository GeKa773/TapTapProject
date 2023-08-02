package com.gekaradchenko.game.taptapproject.ui.screen.start.viewmodel

import com.gekaradchenko.game.taptapproject.base.BaseViewModel
import com.gekaradchenko.game.taptapproject.ui.screen.start.event.StartUiEvent
import com.gekaradchenko.game.taptapproject.ui.screen.start.state.StartUiState
import com.gekaradchenko.game.taptapproject.ui.screen.start.state.heightMinus
import com.gekaradchenko.game.taptapproject.ui.screen.start.state.heightPlus
import com.gekaradchenko.game.taptapproject.ui.screen.start.state.timerMinus
import com.gekaradchenko.game.taptapproject.ui.screen.start.state.timerPlus
import com.gekaradchenko.game.taptapproject.ui.screen.start.state.widthMinus
import com.gekaradchenko.game.taptapproject.ui.screen.start.state.widthPlus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor() : BaseViewModel<StartUiState, StartUiEvent>() {

    override val _uiState: MutableStateFlow<StartUiState> = MutableStateFlow(StartUiState())
    val uiState: StateFlow<StartUiState> = _uiState.asStateFlow()


    override fun sendUiEvent(event: StartUiEvent) {
        when (event) {
            is StartUiEvent.EventStart -> {}
            is StartUiEvent.EventSettingButtons -> {
                when (event) {
                    StartUiEvent.EventSettingButtons.WidthPlus -> {
                        _uiState.value = uiState.value.widthPlus()
                    }
                    StartUiEvent.EventSettingButtons.WidthMinus -> {
                        _uiState.value = uiState.value.widthMinus()
                    }
                    StartUiEvent.EventSettingButtons.HeightPlus -> {
                        _uiState.value = uiState.value.heightPlus()
                    }
                    StartUiEvent.EventSettingButtons.HeightMinus -> {
                        _uiState.value = uiState.value.heightMinus()
                    }
                    StartUiEvent.EventSettingButtons.TimerPlus -> {
                        _uiState.value = uiState.value.timerPlus()
                    }
                    StartUiEvent.EventSettingButtons.TimerMinus -> {
                        _uiState.value = uiState.value.timerMinus()
                    }
                }
            }
        }
    }
}