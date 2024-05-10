package com.project.controlteam.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.project.controlteam.data.room.AppDatabase
import com.project.controlteam.navigation.Graph
import com.project.controlteam.repository.TeamRepository
import com.project.controlteam.ui.theme.ControlTeamTheme
import com.project.controlteam.viewmodel.SplashViewModel
import com.project.controlteam.viewmodel.TeamListViewModel
import com.project.controlteam.viewmodel.factory.TeamViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<TeamListViewModel>(
        factoryProducer = {
            TeamViewModelFactory(
                teamRepository = TeamRepository(AppDatabase.getDatabase(this).teamDao)
            )
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        installSplashScreen().setKeepOnScreenCondition { splashViewModel.showSplashScreen.value }

        enableEdgeToEdge()

        setContent {
            ControlTeamTheme {
                Scaffold {
                    val state by viewModel.state.collectAsState()
                    Surface(modifier = Modifier.padding(it)) {
                        Graph(state = state, onEvent = viewModel::onTeamListEvent)
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen() {

}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MainScreen()
}