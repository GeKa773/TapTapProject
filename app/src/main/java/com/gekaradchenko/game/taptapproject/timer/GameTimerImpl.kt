package com.gekaradchenko.game.taptapproject.timer

import kotlinx.coroutines.delay

class GameTimerImpl : GameTimer {

    override var event: GameTimer.TimerEvent? = null

    private var pause: Boolean = false
    private var stop: Boolean = false
    private var oneSecond: Int = 10
    private var time: Int = 0
        set(value) {
            field = value
            event?.timeChange(value)
        }


    override suspend fun start(second: Int) {

        pause = false
        stop = false
        time = second

        while (true) {
            delay(100)
            if (!pause) {
                if (calculateOneSecond()) {
                    time--
                }
            }

            if (time <= 0 || stop) {
                event?.end()
                break
            }
        }
    }

    override suspend fun pause() {
        pause = true
    }

    override suspend fun continueTimer() {
        pause = false
    }

    override suspend fun stopTimer() {
        stop = true
    }

    private fun calculateOneSecond(): Boolean {
        oneSecond--
        return if (oneSecond <= 0) {
            oneSecond = 10
            true
        } else {
            false
        }
    }
}