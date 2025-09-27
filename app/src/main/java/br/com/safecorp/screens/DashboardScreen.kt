package br.com.safecorp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.safecorp.data.model.SelfCheck
import br.com.safecorp.viewmodel.SelfCheckViewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue

@Composable
fun DashboardWelcomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF202126),
                        Color(0xFF3B328B)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){}
    }
}

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: SelfCheckViewModel
) {
    val selfChecks by viewModel.selfChecks.collectAsStateWithLifecycle(initialValue = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF202126),
                        Color(0xFF3B328B)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Análise de Humor",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            // Average Mood Card
            StatCard(
                title = "Emoção comum",
                value = calculateAverageMood(selfChecks),
                description = "Baseado nas emoções mais sentidas"
            )

            // Last Mood Card
            StatCard(
                title = "Última emoção",
                value = selfChecks.firstOrNull()?.mood ?: "No check-ins yet",
                description = "Seu check-in mais recente"
            )

            // Total Check-ins Card
            StatCard(
                title = "Total Check-ins",
                value = selfChecks.size.toString(),
                description = "Número de check-ins feitos"
            )
        }
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 16.sp
            )
            Text(
                text = value,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = description,
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 14.sp
            )
        }
    }
}

private fun calculateAverageMood(selfChecks: List<SelfCheck>): String {
    if (selfChecks.isEmpty()) return "Sem dados"

    val moodValues = mapOf(
        "😊" to 7,
        "😎" to 6,
        "😐" to 5,
        "😴" to 4,
        "😰" to 3,
        "😢" to 2,
        "😡" to 1
    )

    val average = selfChecks.mapNotNull { check ->
        moodValues[check.mood]
    }.average()

    return when {
        average >= 6.5 -> "😊"
        average >= 5.5 -> "😎"
        average >= 4.5 -> "😐"
        average >= 3.5 -> "😴"
        average >= 2.5 -> "😰"
        average >= 1.5 -> "😢"
        else -> "😡"
    }
}