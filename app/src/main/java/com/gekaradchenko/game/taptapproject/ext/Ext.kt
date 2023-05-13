package com.gekaradchenko.game.taptapproject.ext

import java.lang.IllegalArgumentException

fun Int.toTime(): String {
    val minutes = this / 60
    val seconds = this % 60

    val minutesStr = when (minutes) {
        in 10..59 -> "$minutes"
        in 0..9 -> "0$minutes"
        else -> throw IllegalArgumentException("Минут не можеть быть $minutes")
    }

    val secondsStr = when (seconds) {
        in 10..59 -> "$seconds"
        in 0..9 -> "0$seconds"
        else -> throw IllegalArgumentException("Ошибка в расчётах секунд = $seconds")
    }

    return "$minutesStr:$secondsStr"
}