package com.example.spookbites.datos

import com.example.spookbites.model.Relato
import com.example.spookbites.model.TipoRelato

object RelatoRepository {
    private val relatos = mutableListOf(
        Relato(
            titulo = "La puerta del sótano",
            autor = "Reddit",
            tipo = TipoRelato.RELATO,
            views = 10102,
            likes = 210,
            texto = "Mi abuela cerraba la puerta del sótano con llave todas las noches. " +
                    "Decía que era por la humedad, que no subiera el frío. Yo tenía ocho años y me lo creía todo. \n" +
                    "Cuando murió, heredamos la casa. La primera noche dejé la puerta abierta por pura costumbre de no hacer caso a nadie \n" +
                    "A las tres de la mañana oí pasos subiendo la escalera. Despacio. Y después, una voz que no era la de mi abuela, diciendo mi nombre como si llevara años esperando poder hacerlo."
        ),
        Relato(
            titulo = "El último hombre",
            autor = "Clásico",
            tipo = TipoRelato.RELATOCORTO,
            views = 32100,
            likes = 652,
            texto = "El último hombre sobra la Tierra estaba sentado solo en una habitación. \nEntonces llamaron a la puerta."
        ),
        Relato(
            titulo = "El espejo",
            autor = "Clásico",
            tipo = TipoRelato.RELATOCORTO,
            views = 25012,
            likes = 341,
            texto = "Siempre me sorprendió que mi perro me mirase fijamente. \nHasta que me di cuenta que miraba justo detrás de mi."
        ),
        Relato(
            titulo = "Nota de voz",
            autor = "Reddit",
            tipo = TipoRelato.RELATO,
            views = 1000,
            likes = 210,
            texto = "A las 3:14 me llegó una nota de voz. El remitente era mi propio número.\n" +
                    "Pensé que era un fallo de la compañía, pero la escuché igual. Era mi voz, susurrando muy bajito: «No enciendas la luz. Está en la habitación contigo».\n" +
                    "No la encendí. Me quedé quieta hasta que amaneció. Cuando por fin miré el móvil, había una segunda nota de voz, enviada a las 3:15. Solo se oía una respiración que no era la mía, y luego mi voz otra vez: «Bien hecho»."
        ),
        Relato(
            titulo = "El vecino del cuarto",
            autor = "Reddit",
            tipo = TipoRelato.RELATOCORTO,
            views = 500,
            likes = 90,
            texto = "Cada noche, a la misma hora, alguien da tres golpes en el techo de mi piso. Siempre tres. Siempre a las doce y cuarto.\n" +
                    "Al final subí a quejarme. Llamé al timbre del cuarto varias veces, pero nadie abrió. Bajé a hablar con el portero y le conté lo de los golpes.\n" +
                    "Me miró raro y me dijo que eso era imposible. El edificio solo tiene tres plantas. Esa noche los golpes no vinieron del techo. Vinieron desde dentro del armario."
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