package com.project.controlteam.screens.addition_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.project.controlteam.screens.addition_screens.shape_item.PlayerShapeItem
import com.project.controlteam.viewmodel.events.PlayerEvent
import com.project.controlteam.viewmodel.events.states.PlayerState

@Composable
fun AddPlayerScreen(
    navController: NavHostController,
    state: PlayerState,
    onEvent: (PlayerEvent) -> Unit
) {
    var showMenuShape by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            value = state.playerName,
            onValueChange = { onEvent(PlayerEvent.SetPlayerName(it)) },
            label = { Text(text = "Player name") },
            placeholder = { Text(text = "Player name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.playerPosition,
            onValueChange = { onEvent(PlayerEvent.SetPlayerPosition(it)) },
            label = { Text(text = "Player position") },
            placeholder = { Text(text = "Player position") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.playerSalary,
            onValueChange = { onEvent(PlayerEvent.SetPlayerSalary(it)) },
            label = { Text(text = "Salary") },
            placeholder = { Text(text = state.playerSalary) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Select shape ${state.playerShape} ",
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp)
                )
                IconButton(
                    onClick = {
                        showMenuShape = !showMenuShape
                    },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                onEvent(PlayerEvent.AddPlayer)
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Add player", fontSize = 18.sp)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.BottomEnd)
    ) {
        DropdownMenu(
            expanded = showMenuShape,
            onDismissRequest = { showMenuShape = false },
        ) {
            PlayerShapeItem.playerShapeList.forEach { shapeItem ->
                DropdownMenuItem(
                    text = { Text(text = shapeItem) },
                    onClick = {
                        onEvent(PlayerEvent.SetPlayerShape(shapeItem))
                    }
                )
            }
        }
    }
}