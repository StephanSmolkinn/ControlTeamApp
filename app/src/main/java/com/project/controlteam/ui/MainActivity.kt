package com.project.controlteam.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.project.controlteam.feature_team.viewmodel.SplashViewModel
import com.project.controlteam.navigation.Graph
import com.project.controlteam.ui.theme.ControlTeamTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        installSplashScreen().setKeepOnScreenCondition { splashViewModel.showSplashScreen.value }

        enableEdgeToEdge()

        setContent {
            ControlTeamTheme {
                Scaffold {
                    Surface(modifier = Modifier.padding(it)) {
                        Graph()
                    }
                }
            }
        }
    }
}