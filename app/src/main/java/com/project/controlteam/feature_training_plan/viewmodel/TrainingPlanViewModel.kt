package com.project.controlteam.feature_training_plan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.controlteam.feature_training_plan.data.model.TrainingPlan
import com.project.controlteam.feature_training_plan.viewmodel.event.TrainingPlanEvent
import com.project.controlteam.feature_training_plan.viewmodel.repository.TrainingPlanRepository
import com.project.controlteam.feature_training_plan.viewmodel.state.TrainingPlanState
import com.project.controlteam.utils.PlayerStateTeamId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingPlanViewModel @Inject constructor(
    private val trainingPlanRepository: TrainingPlanRepository
) : ViewModel() {

    private val _stateTrainingPlan = MutableStateFlow(TrainingPlanState())

    val stateTrainingPlan = _stateTrainingPlan.asStateFlow()

    fun onTrainingPlanEvent(event: TrainingPlanEvent) {

        when (event) {
            TrainingPlanEvent.AddTrainingPlan -> {
                val plan = stateTrainingPlan.value.trainingPlan
                val day = stateTrainingPlan.value.day
                val teamId = PlayerStateTeamId.teamId
                val trainingId = stateTrainingPlan.value.trainingPlanId

                if (plan.isEmpty())
                    return

                val trainingPlan = TrainingPlan(
                    trainingPlan = plan,
                    day = day,
                    teamId = teamId,
                )

                viewModelScope.launch {
                    trainingPlanRepository.upsertTrainingPlan(trainingPlan)
                }

            }

            is TrainingPlanEvent.UpdateTrainingPlan -> {
                viewModelScope.launch {
                    trainingPlanRepository.updateTrainingPlan(event.plan, event.trainingPlanId)
                }
            }

            is TrainingPlanEvent.SetDay -> {
                _stateTrainingPlan.update {
                    it.copy(day = event.day)
                }
            }

            is TrainingPlanEvent.SetTrainingPlan -> {
                _stateTrainingPlan.update {
                    it.copy(trainingPlan = event.trainingPlan)
                }
            }

            is TrainingPlanEvent.GetOneTrainingPlan -> {
                viewModelScope.launch {
                    try {
                        val training = trainingPlanRepository.getTrainingPlanById(event.trainingDay, event.teamId)
                        _stateTrainingPlan.update {
                            it.copy(
                                trainingPlan = training.trainingPlan,
                                day = training.day,
                                trainingPlanId = training.id,
                            )
                        }
                    } catch (e: Exception) {
                        println("Not found training")
                        _stateTrainingPlan.update {
                            it.copy(
                                trainingPlan = "",
                                day = stateTrainingPlan.value.day,
                                trainingPlanId = 0
                            )
                        }
                    }
                }
            }

            is TrainingPlanEvent.SetTrainingPlainId -> {
                _stateTrainingPlan.update {
                    it.copy(trainingPlanId = event.trainingPlainId)
                }
            }

        }
    }

}