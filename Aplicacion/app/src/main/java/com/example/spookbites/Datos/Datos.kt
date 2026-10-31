package com.example.spookbites.Datos

import com.example.spookbites.model.Relato
import com.example.spookbites.model.TipoRelato

val relatos = listOf(
    Relato(
        id = 1,
        titulo = "El hombre de anoche",
        autor = "Daniel",
        tipo = TipoRelato.RELATO,
        views = 1000,
        likes = 210,
        texto = "Esta es la historia de un hombre que vivía en una aldea alejada de la ciudad"
    ),

    Relato(
        id = 2,
        titulo = "Enero",
        autor = "Daniel",
        tipo = TipoRelato.RELATO,
        views = 210,
        likes = 25,
        texto = "Todo comenzó el 1 de enero de aquel año"
    ),

    Relato(
        id = 3,
        titulo = "Los últimos días",
        autor = "Daniel",
        tipo = TipoRelato.RELATOCORTO,
        views = 100219,
        likes = 3000,
        texto = "Sonó el teléfono. Ahí fue cuando se apagarón las luces"
    )
)