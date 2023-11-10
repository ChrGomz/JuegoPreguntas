package com.example.juegopreguntas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.juegopreguntas.metodos.MetodosUtiles
import com.example.juegopreguntas.metodos.MetodosUtiles.Companion.BotonConFuncion
import com.example.juegopreguntas.objetos.Preguntas
import com.example.juegopreguntas.objetos.Respuestas


@Preview
@Composable
fun PantallaExamenPreview() {
    PantallaExamen(null)
}

@Composable
fun PantallaExamen(navController: NavHostController?) {
    MuestraExamen(navController)
}

@Composable
fun MuestraExamen(navController: NavHostController?) {
    var indicePregunta by remember {
        mutableIntStateOf(0)
    }

    var textoPregunta by remember {
        mutableStateOf("")
    }

    var visibilidadDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(indicePregunta) {
        val preguntaActual = Preguntas.listaPreguntas[indicePregunta]
        preguntaActual.let {
            textoPregunta = it.textoPregunta
        }
    }

    if (visibilidadDialog) {
        MuestraResultadoExamenDialog(navController = navController)
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = textoPregunta, modifier = Modifier.weight(1F)
        )

        Image(
            painter = painterResource(id = MetodosUtiles.obtenerAnuncioALeatorio()),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BotonConFuncion(texto = "Falso", funcion = {
                Respuestas.respuestasUsuario[indicePregunta] = -1
                if (indicePregunta < Preguntas.listaPreguntas.size - 1) {
                    indicePregunta++
                } else {
                    visibilidadDialog = true
                }
            }, Color.Red, Color.Red)

            BotonConFuncion(texto = "True", funcion = {
                Respuestas.respuestasUsuario[indicePregunta] = 1
                if (indicePregunta < Preguntas.listaPreguntas.size - 1) {
                    indicePregunta++
                } else {
                    visibilidadDialog = true
                }
            }, Color.Green, Color.Green)

        }
    }
}
