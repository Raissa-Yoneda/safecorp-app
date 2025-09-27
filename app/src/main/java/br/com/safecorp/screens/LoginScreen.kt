package br.com.safecorp.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.safecorp.R

@Composable
fun LoginScreen(navController: NavController) {
    var password by remember { mutableStateOf("") }

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
            Text(
                text = "Código da Empresa",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF56B7F4)
            )

            Image(
                painter = painterResource(id = R.drawable.padlock),
                contentDescription = "Imagem principal",
                modifier = Modifier.size(250.dp)
            )

            Text(
                text = "Para garantir sua privacidade você não precisa informar seu nome ou informações de contato.",
                fontSize = 14.sp,
                color = Color.White,
                lineHeight = 20.sp
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Digite o código") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedBorderColor = Color(0xFF56B7F4),
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color(0xFF56B7F4),
                    unfocusedLabelColor = Color.White,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )

            )

            Button(
                onClick = {
                    navController.navigate("menu")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Entrar", fontSize = 16.sp)
            }
        }
    }
}
