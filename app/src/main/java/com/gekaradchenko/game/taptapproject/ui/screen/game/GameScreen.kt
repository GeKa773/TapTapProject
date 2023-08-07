package com.gekaradchenko.game.taptapproject.ui.screen.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gekaradchenko.game.taptapproject.R
import com.gekaradchenko.game.taptapproject.ui.base.Text28_OnBackground
import com.gekaradchenko.game.taptapproject.ui.base.Text24_OnBackground
import com.gekaradchenko.game.taptapproject.ui.base.TapTapButton
import com.gekaradchenko.game.taptapproject.ui.base.Text28_OnPrimary
import com.gekaradchenko.game.taptapproject.ui.data.models.GameSetting
import com.gekaradchenko.game.taptapproject.ui.screen.game.event.GameUiEvent
import com.gekaradchenko.game.taptapproject.ui.screen.game.state.GameUiState
import com.gekaradchenko.game.taptapproject.ui.screen.game.viewmodel.GameViewModel
import com.gekaradchenko.game.taptapproject.ui.theme.TapTapProjectTheme
import com.gekaradchenko.game.taptapproject.ui.theme.defaultToolBarHeight

@Composable
fun GameScreen(
    navController: NavController,
    setting: GameSetting = GameSetting.getDefault(),
    viewModel: GameViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        GameToolBar(uiState = uiState, viewModel = viewModel, clickBack = { navController.popBackStack() })
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            FieldOfPlay(uiState = uiState, viewModel = viewModel)
            if (uiState.isPause || !uiState.isPlay) {
                PausePart(setting = setting, uiState = uiState, viewModel = viewModel)
            }
            if (uiState.gameEnd) {
                RestartGameButton(setting = setting, viewModel = viewModel)
            }
        }
    }
}

@Composable
private fun GameToolBar(uiState: GameUiState, viewModel: GameViewModel, clickBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(defaultToolBarHeight)
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(modifier = Modifier.width(40.dp)) {

            if (uiState.gameEnd || uiState.isPause) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(0.dp)
                        .clickable { clickBack.invoke() }
                )
            } else {
                Text24_OnBackground(text = uiState.score.toString())
            }
        }
        Text28_OnBackground(text = uiState.timer)

        Icon(
            painter = painterResource(id = R.drawable.ic_pause),
            contentDescription = "Pause",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(6.dp)
                .clickable { viewModel.sendUiEvent(GameUiEvent.EventPause) }
        )
    }
}

@Composable
private fun FieldOfPlay(uiState: GameUiState, viewModel: GameViewModel) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 1.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        repeat(uiState.widthCount) { first ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                repeat(uiState.heightCount) { second ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f, true)
                    ) {
                        TapTapButton(
                            data = uiState.getTapTapData(first, second),
                            click = { viewModel.sendUiEvent(GameUiEvent.EventTapTapButton(it)) })
                    }
                }
            }
        }
    }
}

@Composable
private fun PausePart(setting: GameSetting, uiState: GameUiState, viewModel: GameViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f))
            .clickable { viewModel.sendUiEvent(GameUiEvent.EventPlay(setting)) },
        contentAlignment = Alignment.Center
    ) {
        if (uiState.pauseTimer.isEmpty()) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                modifier = Modifier
                    .size(200.dp)
            )
        } else {
            Text(
                text = uiState.pauseTimer,
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                fontSize = 160.sp
            )
        }
    }
}

@Composable
fun RestartGameButton(setting: GameSetting, viewModel: GameViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 0.dp)
                .padding(bottom = 30.dp)
                .height(50.dp)
                .align(Alignment.BottomCenter),
            onClick = {
                viewModel.sendUiEvent(GameUiEvent.RestartGame(setting = setting))
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
                Text28_OnPrimary(text = "Restart")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GameScreenPreview() {
    TapTapProjectTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GameScreen(rememberNavController())
        }
    }
}