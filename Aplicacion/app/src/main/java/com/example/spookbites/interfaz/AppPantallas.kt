package com.example.spookbites.interfaz

import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spookbites.viewmodel.RelatosViewModel

@Composable
fun AppPantallas(
    viewModel: RelatosViewModel = viewModel()
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }

    val relatoSeleccionado = uiState.relatos.find { it.id == uiState.idSeleccionado }

    BackHandler(enabled = relatoSeleccionado != null) {
        viewModel.onVolver()
    }

    LaunchedEffect(uiState.mensaje) {
        uiState.mensaje?.let { mensaje ->
            snackbarHostState.showSnackbar(mensaje)
            viewModel.onMensajeMostrado()
        }
    }

    if (relatoSeleccionado == null) {
        PantallaPrincipal(
            relatos = uiState.relatos,
            snackbarHostState = snackbarHostState,
            onRelatoClick = { relato -> viewModel.onRelatoClick(relato) },
            onAnadirClick = { viewModel.onAnadirClick() }
        )
    } else {
        PantallaDetalle(
            relato = relatoSeleccionado,
            snackbarHostState = snackbarHostState,
            onVolver = { viewModel.onVolver() },
            onEditar = { viewModel.onEditarClick(relatoSeleccionado) },
            onBorrar = { viewModel.onBorrarClick(relatoSeleccionado) }
        )
    }

    uiState.relatoABorrar?.let { relato ->
        ConfirmarBorradoDialog(
            relato = relato,
            onCancelar = { viewModel.onCancelarBorrado() },
            onConfirmar = { viewModel.onConfirmarBorrado() }
        )
    }

    if (uiState.dialogoAltaAbierto) {
        RelatoDialog(
            onCancelar = { viewModel.onCerrarDialogoAlta() },
            onGuardar = { titulo, autor, tipo, texto ->
                viewModel.onGuardarNuevo(titulo, autor, tipo, texto)
            }
        )
    }

    uiState.relatoAEditar?.let { relato ->
        RelatoDialog(
            relatoAEditar = relato,
            onCancelar = { viewModel.onCerrarDialogoEdicion() },
            onGuardar = { titulo, autor, tipo, texto ->
                viewModel.onGuardarEdicion(relato.id, titulo, autor, tipo, texto)
            }
        )
    }
}