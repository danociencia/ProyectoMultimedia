package com.example.spookbites.interfaz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.spookbites.model.Relato
import com.example.spookbites.model.TipoRelato

@Composable
fun ConfirmarBorradoDialog(
    relato: Relato,
    onCancelar: () -> Unit,
    onConfirmar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text("Eliminar relato") },
        text = { Text("¿Seguro que quieres eliminar «${relato.titulo}»? No se podrá recuperar.") },
        confirmButton = {
            TextButton(
                onClick = onConfirmar,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    error: String? = null,
    singleLine: Boolean = true,
    minLines: Int = 1
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = singleLine,
        minLines = minLines,
        isError = error != null,
        supportingText = if (error != null) {
            { Text(error) }
        } else {
            null
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun RelatoDialog(
    relatoAEditar: Relato? = null,
    onCancelar: () -> Unit,
    onGuardar: (titulo: String, autor: String, tipo: TipoRelato, texto: String) -> Unit
) {
    var titulo by remember { mutableStateOf(relatoAEditar?.titulo ?: "") }
    var autor by remember { mutableStateOf(relatoAEditar?.autor ?: "") }
    var tipo by remember { mutableStateOf(relatoAEditar?.tipo ?: TipoRelato.RELATO) }
    var texto by remember { mutableStateOf(relatoAEditar?.texto ?: "") }

    var mostrarErrores by remember { mutableStateOf(false) }

    val tituloError = if (titulo.isBlank()) "El título es obligatorio" else null
    val autorError = if (autor.isBlank()) "El autor es obligatorio" else null
    val textoError = when {
        texto.isBlank() -> "El relato no puede estar vacío"
        tipo == TipoRelato.RELATOCORTO && texto.length > 140 ->
            "Un relato de una línea no puede pasar de 140 caracteres (${texto.length})"
        else -> null
    }

    val formularioValido = tituloError == null && autorError == null && textoError == null

    AlertDialog(
        onDismissRequest = onCancelar,
        title = {
            Text(if (relatoAEditar == null) "Nuevo relato" else "Editar relato")
        },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = tipo == TipoRelato.RELATO,
                        onClick = { tipo = TipoRelato.RELATO }
                    )
                    Text("Relato")
                    Spacer(modifier = Modifier.width(16.dp))
                    RadioButton(
                        selected = tipo == TipoRelato.RELATOCORTO,
                        onClick = { tipo = TipoRelato.RELATOCORTO }
                    )
                    Text("Una línea")
                }
                FormField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = "Título *",
                    error = if (mostrarErrores) tituloError else null
                )
                FormField(
                    value = autor,
                    onValueChange = { autor = it },
                    label = "Autor *",
                    error = if (mostrarErrores) autorError else null
                )
                FormField(
                    value = texto,
                    onValueChange = { texto = it },
                    label = if (tipo == TipoRelato.RELATOCORTO) "Texto * (máx. 140)" else "Texto *",
                    error = if (mostrarErrores) textoError else null,
                    singleLine = false,
                    minLines = 4
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (formularioValido) {
                        onGuardar(titulo.trim(), autor.trim(), tipo, texto.trim())
                    } else {
                        mostrarErrores = true
                    }
                }
            ) {
                Text(if (relatoAEditar == null) "Publicar" else "Actualizar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}