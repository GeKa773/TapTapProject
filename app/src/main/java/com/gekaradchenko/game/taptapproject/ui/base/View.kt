package com.gekaradchenko.game.taptapproject.ui.base

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gekaradchenko.game.taptapproject.ui.data.models.TapTapData

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