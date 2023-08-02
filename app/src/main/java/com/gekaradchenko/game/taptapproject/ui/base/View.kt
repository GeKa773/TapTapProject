package com.gekaradchenko.game.taptapproject.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gekaradchenko.game.taptapproject.ui.data.models.TapTapData
import com.gekaradchenko.game.taptapproject.ui.screen.start.event.StartUiEvent

@Composable
fun TapTapButton(data: TapTapData, click: (data: TapTapData) -> Unit) {

    val buttonColor = if (data.isActive) data.color else Color.Transparent

    Button(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor ?: MaterialTheme.colorScheme.primary
        ),
        onClick = {
            click.invoke(data)
        }) {

        data.text?.let { textData ->
            Text(text = textData.text, color = textData.color)
        }
    }
}


@Composable
fun TapTapButtonPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.8.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.primary),
    )
}

@Composable
fun CircleSettingButton(text: String, click: () -> Unit) {
    Button(
        modifier = Modifier
            .size(70.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        onClick = { click.invoke() }) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = text, fontSize = 30.sp)
        }
    }
}

@Composable
fun SettingText(text: String) {
    Text(text = text, fontSize = 38.sp)
}
@Composable
fun SettingDescriptionText(text: String) {
    Text(text = text, fontSize = 24.sp)
}