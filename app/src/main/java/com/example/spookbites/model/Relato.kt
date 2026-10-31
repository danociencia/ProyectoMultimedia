package com.danociencia.spookbites.model

enum class TipoRelato {
    RELATO,
    RELATOCORTO
}

data class Relato(
    val id: Int,
    val titulo: String,
    val autor: String,
    val tipo: TipoRelato,
    val views: Int,
    val likes: Int,
    val texto: String
)