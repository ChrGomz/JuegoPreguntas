package com.example.juegopreguntas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.juegopreguntas.Rutas.Rutas
import com.example.juegopreguntas.metodos.MetodosUtiles
import com.example.juegopreguntas.objetos.Pregunta
import com.example.juegopreguntas.objetos.Preguntas
import com.example.juegopreguntas.objetos.Respuestas

@Composable
fun MuestraResultadoExamenDialog(
    navController: NavHostController?
) {
    val preguntas = Preguntas.listaPreguntas
    val respuestas = Respuestas.respuestasUsuario

    Dialog(
        onDismissRequest = {
            navController?.navigate(Rutas.PantallaInicio.ruta)
        }
    ) {
        Box(
            Modifier
                .clip(RectangleShape)
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                respuestas.onEachIndexed { index, entry ->
                    PreguntaItem(pregunta = preguntas!![index], respuesta = entry.value)
                }
                MetodosUtiles.BotonConFuncion(texto = "Inicio", funcion = {
                    navController?.navigate(Rutas.PantallaInicio.ruta)
                })
            }
        }
    }
}

@Composable
fun PreguntaItem(pregunta: Pregunta, respuesta: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = pregunta.textoPregunta)

        val respuestaTexto = if (respuesta == 1) "True" else "False"

        val acierto = respuesta == if (pregunta.solucion) 1 else -1

        val aciertoTexto = if (acierto) "Acertaste" else "Fallaste"

        Text(text = "Respuesta del usuario: $respuestaTexto ($aciertoTexto)")
    }
}

