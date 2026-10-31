package com.example.spookbites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.spookbites.Datos.relatos
import com.example.spookbites.interfaz.PantallaDetalle
import com.example.spookbites.interfaz.PantallaLista
import com.example.spookbites.model.Relato
import com.example.spookbites.ui.theme.SpookbitesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpookbitesTheme {
                var relatoSeleccionado by remember { mutableStateOf<Relato?>(null) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        val relato = relatoSeleccionado
                        if (relato == null) {
                            PantallaLista(
                                lista = relatos,
                                onRelatoClick = { relatoSeleccionado = it }
                            )
                        } else {
                            PantallaDetalle(
                                relato = relato,
                                onVolver = { relatoSeleccionado = null }
                            )
                        }
                    }
                }
            }
        }
    }
}