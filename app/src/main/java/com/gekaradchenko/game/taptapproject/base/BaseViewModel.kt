package com.gekaradchenko.game.taptapproject.base

import androidx.lifecycle.ViewModel
import com.gekaradchenko.game.taptapproject.ui.screen.game.event.GameUiEvent
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel<State : BaseUiState, Event : BaseUiEvent> : ViewModel() {

    protected abstract val _uiState: MutableStateFlow<State>


    abstract fun sendUiEvent(event: Event)
}