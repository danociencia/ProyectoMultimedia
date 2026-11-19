package com.example.spookbites.interfaz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spookbites.datos.RelatoRepository
import com.example.spookbites.model.Relato
import com.example.spookbites.model.TipoRelato

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal(
    relatos: List<Relato>,
    snackbarHostState: SnackbarHostState,
    onRelatoClick: (Relato) -> Unit,
    onAnadirClick: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Mis relatos") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAnadirClick) {
                Icon(Icons.Default.Add, contentDescription = "Escribir relato")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        if (relatos.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Todavía no hay relatos. Pulsa + para escribir el primero.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(relatos, key = { it.id }) { relato ->
                    RelatoCard(relato = relato, onClick = { onRelatoClick(relato) })
                }
            }
        }
    }
}

@Composable
fun RelatoCard(relato: Relato, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            if (relato.tipo == TipoRelato.RELATO) {
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .background(Color.LightGray)
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
            Column {
                Text(text = relato.titulo, fontWeight = FontWeight.Bold)
                if (relato.tipo == TipoRelato.RELATOCORTO) {
                    Text(text = relato.texto)
                }
                Text(text = "Autor: ${relato.autor}")
                Text(text = "${relato.views} views - ${relato.likes} likes")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPrincipalPreview() {
    PantallaPrincipal(
        relatos = RelatoRepository.getRelatos(),
        snackbarHostState = remember { SnackbarHostState() },
        onRelatoClick = {},
        onAnadirClick = {}
    )
}