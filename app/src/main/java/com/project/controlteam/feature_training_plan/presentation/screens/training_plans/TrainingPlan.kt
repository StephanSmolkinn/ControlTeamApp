package com.project.controlteam.feature_training_plan.presentation.screens.training_plans

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.project.controlteam.feature_training_plan.viewmodel.event.TrainingPlanEvent
import com.project.controlteam.feature_training_plan.viewmodel.state.TrainingPlanState

@Composable
fun TrainingPlan(
    navController: NavHostController,
    stateTrainingPlan: TrainingPlanState,
    onEvent: (TrainingPlanEvent) -> Unit
) {
    var check = 0
    if (stateTrainingPlan.trainingPlanId != 0)
        check = 1

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = "Training",
            fontSize = 24.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stateTrainingPlan.day.title,
            fontSize = 24.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = stateTrainingPlan.trainingPlan,
            onValueChange = { onEvent(TrainingPlanEvent.SetTrainingPlan(it)) },
            placeholder = { Text(text = "Description of training") },
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(64.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            IconButton(
                onClick = {
                    if (check == 1)
                        onEvent(TrainingPlanEvent.UpdateTrainingPlan(stateTrainingPlan.trainingPlan, stateTrainingPlan.trainingPlanId))
                    else
                        onEvent(TrainingPlanEvent.AddTrainingPlan)

                    navController.popBackStack()
                },
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    }

}

