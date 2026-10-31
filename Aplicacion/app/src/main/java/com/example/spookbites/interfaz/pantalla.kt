package com.example.spookbites.interfaz

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spookbites.Datos.relatos
import com.example.spookbites.model.Relato

@Composable
fun PantallaLista(lista: List<Relato>, onRelatoClick: (Relato) -> Unit) {
    LazyColumn {
        item {
            Text(
                text = "Spookbites",
                fontSize = 28.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(lista) { relato ->
            RelatoCard(relato = relato, onClick = { onRelatoClick(relato) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaListaPreview() {
    PantallaLista(lista = relatos, onRelatoClick = {})
}