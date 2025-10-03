package br.com.safecorp.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri

// Modelo de recurso de suporte
data class SupportItem(
    val id: String,
    val nome: String,
    val descricao: String,
    val link: String? = null // link opcional
)

@Composable
fun SupportScreen() {
    val context = LocalContext.current

    // Lista de recursos hardcoded
    val supportItems = listOf(
        SupportItem(
            id = "68d7521d84a14cc04c37d711",
            nome = "Chat de Apoio 24h",
            descricao = "Converse com um profissional disponível 24h para apoio emocional.",
            link = "https://cvv.org.br/"
        ),
        SupportItem(
            id = "68d7521d84a14cc04c37d712",
            nome = "Canal de Sugestões",
            descricao = "Envie sugestões ou dúvidas sobre bem-estar e saúde mental."
        ),
        SupportItem(
            id = "68d7521d84a14cc04c37d713",
            nome = "Ações Recomendadas",
            descricao = "Pratique exercícios de respiração e mindfulness diariamente."
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF202126), Color(0xFF3B328B))
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Recursos de Apoio",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            items(supportItems) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.1f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = item.nome,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.descricao,
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 14.sp
                        )

                        // Botão só aparece se houver link válido
                        item.link?.let { link ->
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW, link.toUri())
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                            ) {
                                Text(
                                    text = "Solicitar Apoio Emocional",
                                    color = Color(0xFF3B328B),
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
