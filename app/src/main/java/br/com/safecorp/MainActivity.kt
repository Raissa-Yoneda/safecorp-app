package br.com.safecorp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.safecorp.data.AppDatabase
import br.com.safecorp.data.api.RetrofitClient
import br.com.safecorp.data.repository.AssessmentRepository
import br.com.safecorp.data.repository.SelfCheckRepository
import br.com.safecorp.data.repository.SupportRepository
import br.com.safecorp.models.TokenResponse
import br.com.safecorp.screens.*
import br.com.safecorp.ui.theme.SafeCorpTheme
import br.com.safecorp.viewmodel.SelfCheckViewModel
import br.com.safecorp.viewmodel.SupportViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    // Variável para armazenar o token JWT
    private var jwtToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 🔹 Teste de API: gerar token e chamar endpoints protegidos
        testApiEndpoints()

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
                        composable("login") { LoginScreen(navController = navController) } // versão funcional
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

    private fun testApiEndpoints() {
        val api = RetrofitClient.apiService

        // 1️⃣ Gerar token JWT
        api.loginAnonimo().enqueue(object : Callback<TokenResponse> {
            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                if (response.isSuccessful) {
                    jwtToken = response.body()?.token
                    println("✅ Token gerado: $jwtToken")

                    // 2️⃣ Usar token para chamar endpoints protegidos
                    jwtToken?.let { token ->
                        testAvaliacao(token)
                        testHumor(token)
                        testApoio(token)
                    }
                } else {
                    println("❌ Erro ao gerar token: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                println("❌ Falha ao gerar token: ${t.message}")
            }
        })
    }

    private fun testAvaliacao(token: String) {
        val api = RetrofitClient.assessmentApi
        val body = mapOf(
            "pergunta1" to "Sim",
            "pergunta2" to "Não",
            "pergunta3" to "Talvez"
        )

        api.salvarAvaliacao("Bearer $token", body).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                println("✅ Avaliação POST status: ${response.code()}")
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                println("❌ Erro ao enviar Avaliação: ${t.message}")
            }
        })
    }

    private fun testHumor(token: String) {
        val api = RetrofitClient.selfCheckApi
        val body = mapOf("humor" to "Feliz")

        api.salvarHumor("Bearer $token", body).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                println("✅ Humor POST status: ${response.code()}")
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                println("❌ Erro ao enviar Humor: ${t.message}")
            }
        })
    }

    private fun testApoio(token: String) {
        val api = RetrofitClient.supportApi
        api.listarApoio().enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                println("✅ Apoio GET status: ${response.code()}")
                println("Dados Apoio: ${response.body()}")
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                println("❌ Erro ao listar Apoio: ${t.message}")
            }
        })
    }
}
