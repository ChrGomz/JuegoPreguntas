package com.example.juegopreguntas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.juegopreguntas.Rutas.Rutas
import com.example.juegopreguntas.metodos.MetodosUtiles
import com.example.juegopreguntas.metodos.MetodosUtiles.Companion.BotonConFuncion
import com.example.juegopreguntas.metodos.MetodosUtiles.Companion.BotonesNavegacion
import com.example.juegopreguntas.metodos.MetodosUtiles.Companion.obtenerAnuncioALeatorio
import com.example.juegopreguntas.objetos.Preguntas
import com.example.juegopreguntas.objetos.Respuestas

@Composable
fun PantallaCuestionario(navController: NavHostController?) {
    MuestraPregunta(navController)
}

@Composable
fun MuestraPregunta(navController: NavHostController?) {
    var indicePregunta by remember {
        mutableIntStateOf(0)
    }
    var textoSolucion by remember {
        mutableStateOf("")
    }

    var textoPregunta by remember {
        mutableStateOf("")
    }

    LaunchedEffect(indicePregunta) {
        val preguntaActual = Preguntas.listaPreguntas[indicePregunta]
        textoSolucion = ""
        preguntaActual?.let {
            textoPregunta = it.textoPregunta
        }
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    )
    {
        Box(modifier = Modifier.weight(1F)) {
            BotonConFuncion(texto = "Inicio", funcion = {
                navController?.navigate(Rutas.PantallaInicio.ruta)
            })
        }

        Text(
            text = textoPregunta, modifier = Modifier
                .weight(1F)
        )

        Image(
            painter = painterResource(id = obtenerAnuncioALeatorio()),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .weight(2F)
        )

        Text(
            text = textoSolucion, modifier = Modifier
                .weight(1F)
        )
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BotonConFuncion(texto = "Falso", funcion = {
                    textoSolucion = MetodosUtiles
                        .obtenerTextoSolucion(
                            Preguntas.listaPreguntas[indicePregunta].solucion,
                            false
                        )
                    Respuestas.respuestasUsuario[indicePregunta] = -1
                }, Color.Red, Color.Red)

                BotonConFuncion(texto = "True", funcion = {
                    textoSolucion = MetodosUtiles
                        .obtenerTextoSolucion(
                            Preguntas.listaPreguntas[indicePregunta].solucion,
                            true
                        )
                    Respuestas.respuestasUsuario[indicePregunta] = 1
                }, Color.Green, Color.Green)

            }
            BotonesNavegacion(
                funcionAnterior = {
                    if (indicePregunta > 0) {
                        indicePregunta--
                    } else {
                        indicePregunta = Preguntas.listaPreguntas.size - 1
                    }
                },
                funcionAleatorio = {
                    var randomIndex: Int
                    do {
                        randomIndex = (0 until Preguntas.listaPreguntas.size).random()
                    } while (randomIndex == indicePregunta)
                    indicePregunta = randomIndex
                },
                funcionSiguiente = {
                    if (indicePregunta < Preguntas.listaPreguntas.size - 1) {
                        indicePregunta++
                    } else {
                        indicePregunta = 0
                    }
                }
            )
        }
    }
}