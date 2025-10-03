package br.com.safecorp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.safecorp.models.Avaliacao
import br.com.safecorp.data.repository.AssessmentRepository
import kotlinx.coroutines.launch

@Composable
fun RatingScreen(
    navController: NavController,
    repository: AssessmentRepository,
    token: String = "" // Recebe o token
) {
    val scope = rememberCoroutineScope()
    var answers by remember { mutableStateOf(List(5) { 0 }) }
    var assessment by remember { mutableStateOf<Avaliacao?>(null) }
    var showResult by remember { mutableStateOf(false) }

    val questions = listOf(
        "Com que frequência você sente que precisa esconder suas emoções no ambiente de trabalho?",
        "Com que frequência você se sente valorizado pelo seu trabalho?",
        "Com que frequência você sente que pode fazer pausas sem culpa durante o expediente?",
        "Com que frequência você leva preocupações do trabalho para casa?",
        "Com que frequência você sente que seu bem-estar mental é uma prioridade para a empresa?"
    )

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
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Avaliação Psicossocial",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            questions.forEachIndexed { index, question ->
                QuestionItem(
                    question = question,
                    selectedValue = answers[index],
                    onValueChange = { newValue ->
                        answers = answers.toMutableList().apply {
                            set(index, newValue)
                        }
                    }
                )
            }

            Button(
                onClick = {
                    scope.launch {
                        val stringAnswers = answers.map { it.toString() }
                        assessment = repository.submitAssessment(stringAnswers, token)
                        showResult = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enviar avaliação")
            }

            if (showResult && assessment != null) {
                ResultCard(assessment = assessment!!)
            }
        }
    }
}

@Composable
fun QuestionItem(
    question: String,
    selectedValue: Int,
    onValueChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = question,
                color = Color.White,
                fontSize = 16.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 1..5) {
                    RadioButton(
                        selected = selectedValue == i,
                        onClick = { onValueChange(i) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White,
                            unselectedColor = Color.White.copy(alpha = 0.5f)
                        )
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Nunca", color = Color.White.copy(alpha = 0.7f))
                Text("Sempre", color = Color.White.copy(alpha = 0.7f))
            }
        }
    }
}

@Composable
fun ResultCard(assessment: Avaliacao) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Resultado da Avaliação",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Pergunta 1: ${assessment.pergunta1}",
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                text = "Pergunta 2: ${assessment.pergunta2}",
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                text = "Pergunta 3: ${assessment.pergunta3}",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}
