package com.example.spookbites.interfaz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spookbites.Datos.relatos
import com.example.spookbites.model.Relato

@Composable
fun RelatoCard(relato: Relato, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = relato.titulo, fontWeight = FontWeight.Bold)
                Text(text = "Autor: ${relato.autor}")
                Text(text = "${relato.views} views - ${relato.likes} likes")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RelatoCardPreview() {
    RelatoCard(relato = relatos[0], onClick = {})
}