package com.example.spookbites.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.spookbites.datos.RelatoRepository
import com.example.spookbites.model.Relato
import com.example.spookbites.model.TipoRelato

class RelatosViewModel : ViewModel() {

    var uiState by mutableStateOf(RelatosUiState())
        private set

    init {
        uiState = uiState.copy(relatos = RelatoRepository.getRelatos())
    }

    fun onRelatoClick(relato: Relato) {
        uiState = uiState.copy(idSeleccionado = relato.id)
    }

    fun onVolver() {
        uiState = uiState.copy(idSeleccionado = null)
    }

    fun onAnadirClick() {
        uiState = uiState.copy(dialogoAltaAbierto = true)
    }

    fun onCerrarDialogoAlta() {
        uiState = uiState.copy(dialogoAltaAbierto = false)
    }

    fun onGuardarNuevo(titulo: String, autor: String, tipo: TipoRelato, texto: String) {
        val nuevo = Relato(titulo = titulo, autor = autor, tipo = tipo, texto = texto)
        RelatoRepository.addRelato(nuevo)
        uiState = uiState.copy(
            relatos = RelatoRepository.getRelatos(),
            dialogoAltaAbierto = false,
            mensaje = "Relato '${nuevo.titulo}' publicado"
        )
    }

    fun onEditarClick(relato: Relato) {
        uiState = uiState.copy(relatoAEditar = relato)
    }

    fun onCerrarDialogoEdicion() {
        uiState = uiState.copy(relatoAEditar = null)
    }

    fun onGuardarEdicion(id: String, titulo: String, autor: String, tipo: TipoRelato, texto: String) {
        val existente = RelatoRepository.getRelatoById(id) ?: return
        val editado = existente.copy(titulo = titulo, autor = autor, tipo = tipo, texto = texto)
        RelatoRepository.updateRelato(editado)
        uiState = uiState.copy(
            relatos = RelatoRepository.getRelatos(),
            relatoAEditar = null,
            mensaje = "Relato actualizado"
        )
    }

    fun onBorrarClick(relato: Relato) {
        uiState = uiState.copy(relatoABorrar = relato)
    }

    fun onCancelarBorrado() {
        uiState = uiState.copy(relatoABorrar = null)
    }

    fun onConfirmarBorrado() {
        val relato = uiState.relatoABorrar ?: return
        RelatoRepository.deleteRelato(relato.id)
        uiState = uiState.copy(
            relatos = RelatoRepository.getRelatos(),
            relatoABorrar = null,
            idSeleccionado = null,
            mensaje = "Relato '${relato.titulo}' eliminado"
        )
    }

    fun onMensajeMostrado() {
        uiState = uiState.copy(mensaje = null)
    }
}