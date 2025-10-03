package br.com.safecorp.screens

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.safecorp.data.model.SupportResource
import br.com.safecorp.network.RetrofitClient
import br.com.safecorp.viewmodel.SupportViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.core.net.toUri

@Composable
fun SupportScreen() {
    val context = LocalContext.current

    // ViewModel usando RetrofitClient direto
    val viewModel: SupportViewModel = viewModel(
        factory = SupportViewModel.Factory(RetrofitClient.supportApi)
    )

    val supportResources by viewModel.supportResources.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // Log para debug: verifica se os dados chegaram do backend
    LaunchedEffect(supportResources) {
        Log.d("SupportScreen", "Recursos recebidos: $supportResources")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = listOf(Color(0xFF202126), Color(0xFF3B328B)))
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
                    text = "Recursos de apoio",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (isLoading) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color.White)
                    }
                }
            } else {
                if (supportResources.isEmpty()) {
                    // Mostra mensagem somente se não houver recurso algum
                    item {
                        Text(
                            text = "Nenhum recurso de suporte disponível no momento.",
                            color = Color(0xFF8A2BE2), // roxo
                            fontSize = 16.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    // Botão do primeiro recurso
                    val firstResource = supportResources.first()
                    item {
                        Button(
                            onClick = {
                                firstResource.contactInfo?.let { link ->
                                    val intent = Intent(Intent.ACTION_VIEW, link.toUri())
                                    context.startActivity(intent)
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                        ) {
                            Text(
                                text = "Contato do suporte",
                                color = Color(0xFF3B328B),
                                fontSize = 16.sp
                            )
                        }
                    }

                    // Lista dos demais recursos (excluindo o primeiro)
                    val remainingResources = supportResources.drop(1)
                    items(remainingResources) { resource ->
                        SupportResourceCard(resource)
                    }
                }
            }
        }
    }
}

@Composable
fun SupportResourceCard(resource: SupportResource) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = resource.title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            resource.contactInfo?.let { contact ->
                Text(
                    text = contact,
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
            }
        }
    }
}
