package br.com.safecorp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun WelcomeScreen(navController: NavController) {
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
        ) {
            // Título
            Text(
                text = "SafeCorp",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF56B7F4)
            )

            // Imagem
            Image(
                painter = painterResource(id = R.drawable.woman_balance),
                contentDescription = "Imagem principal",
                modifier = Modifier
                    .size(250.dp)
            )

            // Texto descritivo
            Text(
                text = "Uma iniciativa para transformar a cultura do cuidado nas empresas. Aqui, o foco é você — seu equilíbrio, sua saúde e seu tempo.",
                fontSize = 14.sp,
                color = Color.White,
                lineHeight = 20.sp
            )

            // Botão
            Button(
                onClick = {
                    navController.navigate("login")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Iniciar", fontSize = 16.sp)
            }
        }
    }
}