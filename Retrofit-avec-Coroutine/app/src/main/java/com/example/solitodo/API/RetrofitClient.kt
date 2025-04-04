package com.example.solitodo.API

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoScreen()
        }
    }
}

@Composable
fun TodoScreen() {
    var title by remember { mutableStateOf("Chargement...") }

    LaunchedEffect(Unit) {
        try {
            // Appel API dans un thread IO (optionnel ici, mais propre)
            val todo = withContext(Dispatchers.IO) {
                RetrofitClient.api.getTodo()
            }
            title = todo.title
        } catch (e: Exception) {
            title = "Erreur : ${e.message}"
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, style = MaterialTheme.typography.h5)
        }
    }
}