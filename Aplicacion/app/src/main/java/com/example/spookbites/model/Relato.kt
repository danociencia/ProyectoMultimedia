package com.example.spookbites.model

import java.util.UUID

enum class TipoRelato {
    RELATO,
    RELATOCORTO
}

data class Relato(
    val id: String = UUID.randomUUID().toString(),
    val titulo: String,
    val autor: String,
    val tipo: TipoRelato,
    val views: Int = 0,
    val likes: Int = 0,
    val texto: String
)