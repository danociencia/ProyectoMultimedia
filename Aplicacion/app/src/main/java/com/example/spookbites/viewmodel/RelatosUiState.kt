package com.example.spookbites.viewmodel

import com.example.spookbites.model.Relato

data class RelatosUiState(
    val relatos: List<Relato> = emptyList(),
    val idSeleccionado: String? = null,
    val relatoAEditar: Relato? = null,
    val relatoABorrar: Relato? = null,
    val dialogoAltaAbierto: Boolean = false,
    val mensaje: String? = null
)