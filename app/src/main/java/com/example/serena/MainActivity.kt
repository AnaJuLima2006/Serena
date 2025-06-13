package com.example.serena

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.example.serena.ui.theme.AppTypography
import com.example.serena.ui.theme.screens.HomeScreen
import com.example.serena.ui.theme.screens.SoundPlayerScreen
import com.example.serena.ui.theme.screens.SplashScreen
import com.example.serena.ui.theme.screens.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                typography = AppTypography
            ) {
                SerenaApp()
            }
        }
    }
}

@Composable
fun SerenaApp() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        AppNavHost(navController, modifier = Modifier.padding(paddingValues))
    }
}



@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "splash",
        modifier = modifier
    ) {
        composable("splash") { SplashScreen(navController) }
        composable("welcome") { WelcomeScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("sound/{environmentName}") { backStack ->
            val environmentName = backStack.arguments?.getString("environmentName") ?: "FLORESTA"
            SoundPlayerScreen(environmentName, navController)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun SoundPlayerScreenPreview() {
    val navController = rememberNavController()
    SoundPlayerScreen(envName = "FLORESTA", navController = navController)
}






