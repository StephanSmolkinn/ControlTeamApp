package com.project.controlteam.feature_training_plan.presentation.screens.training_plans

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.project.controlteam.feature_training_plan.data.model.day_constants.Day
import com.project.controlteam.navigation.constants_graph_root.Graph
import com.project.controlteam.utils.PlayerStateTeamId
import com.project.controlteam.feature_training_plan.viewmodel.event.TrainingPlanEvent
import com.project.controlteam.feature_training_plan.viewmodel.state.TrainingPlanState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrainingPlansScreen(
    navController: NavHostController,
    stateTrainingPlan: TrainingPlanState,
    onEvent: (TrainingPlanEvent) -> Unit
) {
    val listWeekend = listOf<Day>(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY)

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        stickyHeader {
            Surface(
                modifier = Modifier.fillParentMaxWidth()
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "Select day",
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
        items(items = listWeekend) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
                    .clickable {
                        onEvent(TrainingPlanEvent.SetDay(it))
                        onEvent(TrainingPlanEvent.GetOneTrainingPlan(it, PlayerStateTeamId.teamId))
                        navController.navigate(Graph.ADD_TRAINING_PLAN)
                    }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = it.title,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Justify,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}