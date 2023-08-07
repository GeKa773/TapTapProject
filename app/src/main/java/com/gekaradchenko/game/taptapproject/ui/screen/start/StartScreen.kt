package com.gekaradchenko.game.taptapproject.ui.screen.start

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gekaradchenko.game.taptapproject.ui.base.*
import com.gekaradchenko.game.taptapproject.ui.screen.Screens
import com.gekaradchenko.game.taptapproject.ui.screen.start.event.StartUiEvent
import com.gekaradchenko.game.taptapproject.ui.screen.start.state.StartUiState
import com.gekaradchenko.game.taptapproject.ui.screen.start.viewmodel.StartViewModel
import com.gekaradchenko.game.taptapproject.ui.theme.TapTapProjectTheme
import com.gekaradchenko.game.taptapproject.util.getDisplayRatio

@Composable
fun StartScreen(
    navController: NavController,
    viewModel: StartViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        StartTable(uiState = uiState, context = context)
        StartSettingModule(uiState = uiState, viewModel)
        StartButton(viewModel = viewModel) {
            navController.navigate(route = Screens.Game.gameSettings(uiState.gameSetting))
        }
    }

}

@Composable
fun StartTable(uiState: StartUiState, context: Context) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .aspectRatio(context.getDisplayRatio())
                .padding(bottom = 1.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(uiState.gameSetting.widthCount) { first ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    repeat(uiState.gameSetting.heightCount) { second ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f, true)
                        ) {
                            TapTapButtonPreview()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StartSettingModule(uiState: StartUiState, viewModel: StartViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        StartSettingButtonsModule(
            value = uiState.gameSetting.widthCount.toString(),
            description = "Width",
            plus = { viewModel.sendUiEvent(StartUiEvent.EventSettingButtons.WidthPlus) },
            minus = { viewModel.sendUiEvent(StartUiEvent.EventSettingButtons.WidthMinus) }
        )

        StartSettingButtonsModule(
            value = uiState.gameSetting.heightCount.toString(),
            description = "Height",
            plus = { viewModel.sendUiEvent(StartUiEvent.EventSettingButtons.HeightPlus) },
            minus = { viewModel.sendUiEvent(StartUiEvent.EventSettingButtons.HeightMinus) }
        )

        StartSettingButtonsModule(
            value = uiState.gameSetting.timer.toString(),
            description = "Timer",
            plus = { viewModel.sendUiEvent(StartUiEvent.EventSettingButtons.TimerPlus) },
            minus = { viewModel.sendUiEvent(StartUiEvent.EventSettingButtons.TimerMinus) }
        )
    }
}

@Composable
fun StartSettingButtonsModule(value: String, description: String, plus: () -> Unit, minus: () -> Unit) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CircleSettingButton(
            text = "+",
            click = { plus.invoke() }
        )

        Text36_OnBackground(text = value)

        CircleSettingButton(
            text = "-",
            click = { minus.invoke() }
        )

        Text24_OnBackground(text = description)
    }
}


@Composable
fun StartButton(viewModel: StartViewModel, click: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 0.dp)
            .height(50.dp),
        onClick = {
            click.invoke()
            viewModel.sendUiEvent(StartUiEvent.EventStart)
        },
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text28_OnPrimary(text = "Start")
        }
    }
}


@Composable
@Preview(showBackground = true)
fun StartScreenPreview() {
    TapTapProjectTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            StartScreen(navController = rememberNavController())
        }
    }
}