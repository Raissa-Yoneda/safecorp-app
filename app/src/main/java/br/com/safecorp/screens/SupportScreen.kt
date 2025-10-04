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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri

data class SupportItem(
    val id: String,
    val nome: String,
    val descricao: androidx.compose.ui.text.AnnotatedString,
    val link: String? = null
)

@Composable
fun SupportScreen() {
    val context = LocalContext.current

    val supportItems = listOf(
        SupportItem(
            id = "68d7521d84a14cc04c37d711",
            nome = "Chat de Apoio 24h",
            descricao = buildAnnotatedString {
                append("Converse com um profissional disponível 24h para apoio emocional.")
            },
            link = "https://cvv.org.br/"
        ),
        SupportItem(
            id = "68d7521d84a14cc04c37d713",
            nome = "Ações Recomendadas",
            descricao = buildAnnotatedString {
                appendBold("Respiração guiada\n")
                append("Exercício de respiração 4-4-4 (inspirar 4s, segurar 4s, expirar 4s).\n" +
                        "Ou respiração profunda com áudio/vibração para marcar o ritmo.\n\n")

                appendBold("Técnica 5-4-3-2-1 (Grounding)\n")
                append("Identificar 5 coisas que vê, 4 que pode tocar, 3 que pode ouvir, 2 que pode cheirar, 1 que pode saborear.\n" +
                        "Ajuda a trazer foco para o presente.\n\n")

                appendBold("Alongamento leve\n")
                append("Movimentar braços, pescoço e ombros para liberar tensão física.\n" +
                        "Um mini-guia visual pode ajudar.\n\n")

                appendBold("Escuta consciente\n")
                append("Colocar um som calmante (chuva, natureza, música relaxante).\n" +
                        "Convidar a pessoa a fechar os olhos e focar só no som.\n")
            }
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
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = item.descricao,
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 14.sp,
                            lineHeight = 20.sp
                        )

                        item.link?.let { link ->
                            Spacer(modifier = Modifier.height(12.dp))
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

// Função de extensão para negrito
fun androidx.compose.ui.text.AnnotatedString.Builder.appendBold(text: String) {
    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
    append(text)
    pop()
}
