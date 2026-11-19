package com.example.spookbites.datos

import com.example.spookbites.model.Relato
import com.example.spookbites.model.TipoRelato

object RelatoRepository {
    private val relatos = mutableListOf(
        Relato(
            titulo = "Título del relato",
            autor = "Daniel",
            tipo = TipoRelato.RELATO,
            views = 1000,
            likes = 210,
            texto = "Primer párrafo...\n\nSegundo párrafo..."
        ),
        Relato(
            titulo = "Título del corto",
            autor = "Marta",
            tipo = TipoRelato.RELATOCORTO,
            views = 500,
            likes = 90,
            texto = "Una sola frase de miedo."
        )
    )

    fun getRelatos(): List<Relato> = relatos.toList()

    fun getRelatoById(id: String): Relato? = relatos.find { it.id == id }

    fun addRelato(relato: Relato) {
        relatos.add(relato)
    }

    fun updateRelato(relato: Relato) {
        val index = relatos.indexOfFirst { it.id == relato.id }
        if (index != -1) {
            relatos[index] = relato
        }
    }

    fun deleteRelato(id: String) {
        relatos.removeAll { it.id == id }
    }
}