package com.gekaradchenko.game.taptapproject.ui.data.models

import androidx.compose.ui.graphics.Color

data class TapTapData(
    val id: Pair<Int, Int>,
    val isActive: Boolean,
    val color: Color?,
    val text: TapTapTextData? = null,
)

data class TapTapTextData(
    val text: String,
    val color: Color
)