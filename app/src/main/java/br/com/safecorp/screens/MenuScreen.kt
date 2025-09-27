package br.com.safecorp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.safecorp.R
import kotlinx.coroutines.delay

@Composable
fun MenuScreen(navController: NavController) {
    val motivationalMessages = remember {
        listOf(
            "Respire fundo e lembre-se: cada dia é uma nova oportunidade para ser a melhor versão de você.",
            "Seu bem-estar é uma prioridade. Reserve um momento para cuidar de si mesmo hoje.",
            "Pequenos passos diários levam a grandes mudanças. Celebre cada conquista!",
            "A saúde mental é tão importante quanto a física. Cuide de ambas com carinho.",
            "Você é mais forte do que imagina. Cada desafio superado te torna mais resiliente.",
            "Momentos de pausa são essenciais. Permita-se descansar quando necessário.",
            "Sua jornada é única. Não compare seu progresso com o dos outros.",
            "A gratidão transforma o que temos em suficiente. Pratique-a diariamente.",
            "Equilíbrio é a chave. Encontre seu ritmo entre trabalho e bem-estar.",
            "Você merece ser feliz. Permita-se buscar o que te faz bem."
        )
    }

    var currentMessageIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000) // 5 seconds
            currentMessageIndex = (currentMessageIndex + 1) % motivationalMessages.size
        }
    }

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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = " ${motivationalMessages[currentMessageIndex]}",
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 20.sp
                )
            }

            item {
                Image(
                    painter = painterResource(id = R.drawable.chat_bot),
                    contentDescription = "Imagem principal",
                    modifier = Modifier
                        .size(250.dp)
                )
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, color = Color(0xFF56B7F4), shape = RoundedCornerShape(24.dp))
                ) {
                    Button(
                        onClick = { navController.navigate("self_check") },
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3D3D3D)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text("Autoavaliação de Humor", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            Text("Responda um breve questionário sobre como está se sentindo,", fontSize = 14.sp, color = Color.LightGray)
                        }
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, color = Color(0xFF56B7F4), shape = RoundedCornerShape(24.dp))
                ) {
                    Button(
                        onClick = { navController.navigate("rating") },
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3D3D3D)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text("Questionário", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            Text("Responda perguntas para descobrir sua pontuação.", fontSize = 14.sp, color = Color.LightGray)
                        }
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, color = Color(0xFF56B7F4), shape = RoundedCornerShape(24.dp))
                ) {
                    Button(
                        onClick = { navController.navigate("meditation") },
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3D3D3D)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text("Relaxe um pouco", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            Text("Reserve um momento para relaxar com uma breve meditação", fontSize = 14.sp, color = Color.LightGray)
                        }
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, color = Color(0xFF56B7F4), shape = RoundedCornerShape(24.dp))
                ) {
                    Button(
                        onClick = { navController.navigate("dashboard") },
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3D3D3D)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text("Dados de Humor", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            Text("Veja suas estatísticas e tendências de humor", fontSize = 14.sp, color = Color.LightGray)
                        }
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, color = Color(0xFF56B7F4), shape = RoundedCornerShape(24.dp))
                ) {
                    Button(
                        onClick = { navController.navigate("support") },
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3D3D3D)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text("Suporte", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            Text("Encontre recursos e canais de apoio disponíveis", fontSize = 14.sp, color = Color.LightGray)
                        }
                    }
                }
            }
        }
    }
}