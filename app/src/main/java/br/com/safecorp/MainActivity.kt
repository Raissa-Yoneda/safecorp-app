package br.com.safecorp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.safecorp.data.AppDatabase
import br.com.safecorp.network.RetrofitClient
import br.com.safecorp.data.repository.AssessmentRepository
import br.com.safecorp.data.repository.SelfCheckRepository
import br.com.safecorp.data.repository.SupportRepository
import br.com.safecorp.screens.*
import br.com.safecorp.ui.theme.SafeCorpTheme
import br.com.safecorp.viewmodel.SelfCheckViewModel
import br.com.safecorp.viewmodel.SupportViewModel
import br.com.safecorp.data.api.SupportApi

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SafeCorpTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val context = LocalContext.current

                    // Inicializar database e repositories
                    val database = AppDatabase.getDatabase(context)
                    val assessmentRepository =
                        AssessmentRepository(database.assessmentDao(), RetrofitClient.assessmentApi)
                    val selfCheckRepository =
                        SelfCheckRepository(database.selfCheckDao(), RetrofitClient.selfCheckApi)
                    val supportRepository = SupportRepository(RetrofitClient.supportApi)

                    NavHost(
                        navController = navController,
                        startDestination = "welcome"
                    ) {
                        composable("welcome") { WelcomeScreen(navController) }
                        composable("login") { LoginScreen(navController = navController) }
                        composable("menu") { MenuScreen(navController) }
                        composable("meditation") { MeditationScreen(navController) }
                        composable("rating") { RatingScreen(navController, assessmentRepository) }
                        composable("self_check") {
                            val viewModel: SelfCheckViewModel = viewModel(
                                factory = SelfCheckViewModel.Factory(selfCheckRepository)
                            )
                            SelfCheckScreen(navController, viewModel)
                        }
                        composable("support") {
                            val viewModel: SupportViewModel = viewModel(
                                factory = SupportViewModel.Factory(RetrofitClient.supportApi)
                            )
                            SupportScreen(navController, viewModel)
                        }
                        composable("dashboard") {
                            val viewModel: SelfCheckViewModel = viewModel(
                                factory = SelfCheckViewModel.Factory(selfCheckRepository)
                            )
                            DashboardScreen(navController, viewModel)
                        }
                    }
                }
            }
        }
    }
}
