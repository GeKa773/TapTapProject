package com.gekaradchenko.game.taptapproject.ui.base

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun CircleSettingButton(text: String, click: () -> Unit) {

    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(targetValue = if (pressed) 0.8f else 1f)

    val borderRadius by animateIntAsState(
        targetValue = if (pressed) 30 else 100,
        animationSpec = tween(
            durationMillis = 500
        )
    )
    Button(
        modifier = Modifier
            .size(70.dp)
            .clip(RoundedCornerShape(borderRadius))
            .background(MaterialTheme.colorScheme.secondary)
            .animateContentSize(),
        contentPadding = PaddingValues(0.dp),
        interactionSource = interactionSource,
        onClick = {
            click.invoke()
        }) {
        Row(
            modifier = Modifier.fillMaxSize()
                .graphicsLayer {
                    scaleX = scale // Анимация масштаба по оси X
                    scaleY = scale // Анимация масштаба по оси Y
                    alpha = if (pressed) 0.7f else 1f // Анимация прозрачности
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text36_OnSecondary(text = text)
        }
    }
}