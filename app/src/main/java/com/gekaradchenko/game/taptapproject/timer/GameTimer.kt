package com.gekaradchenko.game.taptapproject.timer

interface GameTimer {

    var event: TimerEvent?

    suspend fun start(second: Int)
    suspend fun pause()
    suspend fun continueTimer()
    suspend fun stopTimer()

    interface TimerEvent {
        fun timeChange(time: Int)
        fun end()
    }
}