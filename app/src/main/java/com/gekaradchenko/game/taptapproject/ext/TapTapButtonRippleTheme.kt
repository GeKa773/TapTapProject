package com.gekaradchenko.game.taptapproject.ext

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

class TapTapButtonRippleTheme(
    private val color: Color,
    private val isActive: Boolean,
) : RippleTheme {

    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        if (isActive) color else MaterialTheme.colorScheme.error,
        !isSystemInDarkTheme()
    )

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleTheme.defaultRippleAlpha(
            if (isActive) color.copy(alpha = 0.75f)
            else MaterialTheme.colorScheme.error.copy(alpha = 0.75f),
            !isSystemInDarkTheme()
        )
}