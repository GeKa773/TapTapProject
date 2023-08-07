package com.gekaradchenko.game.taptapproject.ui.base

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
private fun BaseText(text: String, color: Color, style: TextStyle) {
    Text(text = text, color = color, style = style)
}

// OnBackground
@Composable
fun OnBackgroundColorText(text: String, style: TextStyle) {
    BaseText(text = text, color = MaterialTheme.colorScheme.onBackground, style = style)
}

@Composable
fun Text24_OnBackground(text: String) {
    OnBackgroundColorText(text = text, style = MaterialTheme.typography.headlineSmall)
}

@Composable
fun Text28_OnBackground(text: String) {
    OnBackgroundColorText(text = text, style = MaterialTheme.typography.headlineMedium)
}

@Composable
fun Text36_OnBackground(text: String) {
    OnBackgroundColorText(text = text, style = MaterialTheme.typography.displaySmall)
}

// OnPrimary
@Composable
fun OnPrimaryColorText(text: String, style: TextStyle) {
    BaseText(text = text, color = MaterialTheme.colorScheme.onPrimary , style = style )

}

@Composable
fun Text36_OnPrimary(text: String) {
    OnPrimaryColorText(text = text, style = MaterialTheme.typography.displaySmall)
}

@Composable
fun Text28_OnPrimary(text: String) {
    OnPrimaryColorText(text = text, style = MaterialTheme.typography.headlineMedium)
}

// OnPrimary
@Composable
fun OnSecondaryColorText(text: String, style: TextStyle) {
    BaseText(text = text, color = MaterialTheme.colorScheme.onSecondary , style = style )

}

@Composable
fun Text36_OnSecondary(text: String) {
    OnSecondaryColorText(text = text, style = MaterialTheme.typography.displaySmall)
}

@Composable
fun Text28_OnSecondary(text: String) {
    OnSecondaryColorText(text = text, style = MaterialTheme.typography.headlineMedium)
}

