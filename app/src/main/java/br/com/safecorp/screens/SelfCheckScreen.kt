package br.com.safecorp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.safecorp.data.model.SelfCheck
import br.com.safecorp.viewmodel.SelfCheckViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SelfCheckScreen(
    navController: NavController,
    viewModel: SelfCheckViewModel
) {
    var selectedMood by remember { mutableStateOf<String?>(null) }
    var note by remember { mutableStateOf("") }
    val selfChecks by viewModel.selfChecks.collectAsState()
    val message by viewModel.message.collectAsState()

    val moods = listOf("😊", "😐", "😢", "😡", "😴", "😰", "😎")

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
                .padding(16.dp)
                .padding(top = 100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Como está se sentindo hoje?",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                // Mood selection
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    moods.forEach { mood ->
                        ClickableMood(
                            mood = mood,
                            isSelected = mood == selectedMood,
                            onClick = { selectedMood = mood }
                        )
                    }
                }
            }

            item {
                // Note input
                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Adicione uma descrição (opcional)", color = Color.White) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                        cursorColor = Color.White
                    )
                )
            }

            item {
                // Save button
                Button(
                    onClick = {
                        selectedMood?.let { mood ->
                            viewModel.addSelfCheck(mood, note.takeIf { it.isNotBlank() })
                            selectedMood = null
                            note = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = selectedMood != null
                ) {
                    Text("Salvar Check-in")
                }
            }

            // API Response Message
            message?.let { msg ->
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.1f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = msg,
                                color = Color.White,
                                fontSize = 16.sp
                            )
                            TextButton(
                                onClick = { viewModel.clearMessage() }
                            ) {
                                Text("Fechar", color = Color.White)
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Check-ins Anteriores",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            items(selfChecks) { check ->
                CheckInItem(check)
            }
        }
    }
}

@Composable
fun ClickableMood(
    mood: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = mood,
        fontSize = 32.sp,
        color = if (isSelected) Color.White else Color.White.copy(alpha = 0.5f),
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp)
    )
}

@Composable
fun CheckInItem(check: SelfCheck) {
    val dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale("pt", "BR"))
    
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = check.mood,
                    fontSize = 24.sp
                )
                Text(
                    text = dateFormat.format(check.date),
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
            }
            check.note?.let { note ->
                Text(
                    text = note,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

