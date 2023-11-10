package com.example.juegopreguntas.metodos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.juegopreguntas.PantallaCuestionario
import com.example.juegopreguntas.PantallaExamen
import com.example.juegopreguntas.PantallaInicio
import com.example.juegopreguntas.R
import com.example.juegopreguntas.Rutas.Rutas
import com.example.juegopreguntas.objetos.Anuncios.anuncios
import com.example.juegopreguntas.objetos.Pregunta
import java.io.File

class MetodosUtiles {
    companion object {

        @Composable
        fun GraphNavegacion() {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Rutas.PantallaInicio.ruta) {

                composable(Rutas.PantallaInicio.ruta) {
                    PantallaInicio(navController = navController)
                }

                composable(Rutas.PantallaCuestionario.ruta) {
                    PantallaCuestionario(navController = navController)
                }

                composable(Rutas.PantallaExamen.ruta) {
                    PantallaExamen(navController = navController)
                }
            }
        }

        fun obtenerTextoSolucion(Solucion: Boolean, opcionSeleccionada: Boolean): String {
            return if (Solucion == opcionSeleccionada) {
                "Acertaste! la respuesta era $Solucion"
            } else {
                "Cagaste la respuesta era $Solucion"
            }
        }

        fun obtenerBooleanSolucion(Solucion: Boolean, opcionSeleccionada: Boolean): Boolean {
            return Solucion == opcionSeleccionada
        }


        fun fillListaPreguntas(ruta: String): ArrayList<Pregunta> {
            val listaPreguntas =
                parseArchivoAPreguntas(ruta)

            return listaPreguntas as ArrayList<Pregunta>
        }

        fun fillListaPreguntasPrueba(): ArrayList<Pregunta> {
            val listaPreguntas = ArrayList<Pregunta>()

            val pregunta1 = Pregunta().apply {
                textoPregunta = "Es la tierra plana?"
                solucion = false
            }
            val pregunta2 = Pregunta().apply {
                textoPregunta = "El awa tiene grafeno para controlarte la mente?"
                solucion = true
            }
            val pregunta3 = Pregunta().apply {
                textoPregunta = "Los aviones controllan el clima?"
                solucion = true
            }
            val pregunta4 = Pregunta().apply {
                textoPregunta = "Es la tierra un donut"
                solucion = true
            }
            val pregunta5 = Pregunta().apply {
                textoPregunta = "las vacunas tienen microchips?"
                solucion = true
            }
            val pregunta6 = Pregunta().apply {
                textoPregunta = "El meado de vaca cura el cancer"
                solucion = true
            }
            val pregunta7 = Pregunta().apply {
                textoPregunta = "Venezuela es el equivalente real de wakanda?"
                solucion = true
            }

            listaPreguntas.add(pregunta1)
            listaPreguntas.add(pregunta2)
            listaPreguntas.add(pregunta3)
            listaPreguntas.add(pregunta4)
            listaPreguntas.add(pregunta5)
            listaPreguntas.add(pregunta6)
            listaPreguntas.add(pregunta7)

            return listaPreguntas
        }

        fun fillListaAnuncios(): MutableList<Anuncio> {
            val anuncios = mutableListOf<Anuncio>()

            anuncios.add(Anuncio(R.drawable.cr7npi, 10))
            anuncios.add(Anuncio(R.drawable.cr7bancosanto, 10))
            anuncios.add(Anuncio(R.drawable.messibimbo, 20))
            anuncios.add(Anuncio(R.drawable.messiherbalife, 20))
            anuncios.add(Anuncio(R.drawable.messilays, 20))
            anuncios.add(Anuncio(R.drawable.messipepsi, 20))

            return anuncios
        }

        fun obtenerAnuncioALeatorio(): Int {
            val numAleatorio = Math.random() * 100

            var rangoActual = 0
            for (anuncio in anuncios) {
                if (numAleatorio < rangoActual + anuncio.peso) {
                    return anuncio.idPainter
                }
                rangoActual += anuncio.peso
            }

            return anuncios.first().idPainter
        }

        @Composable
        fun BotonConFuncion(
            texto: String,
            funcion: () -> Unit,
            colorFondo: Color = Color.Gray,
            colorTexto: Color = Color.Black
        ) {
            OutlinedButton(
                onClick = funcion,
                border = BorderStroke(1.dp, colorFondo),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = colorTexto),
            ) {
                Text(text = texto, color = colorTexto)
            }
        }

        @Composable
        fun BotonesNavegacion(
            funcionAnterior: () -> Unit,
            funcionAleatorio: () -> Unit,
            funcionSiguiente: () -> Unit
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BotonConFuncion("Anterior", funcionAnterior)
                BotonConFuncion("Aleatorio", funcionAleatorio)
                BotonConFuncion("Siguiente", funcionSiguiente)
            }
        }

        fun leerArchivo(ruta: String): String? {
            try {
                val file = File(ruta)
                if (file.exists()) {
                    return file.readText()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        fun escribirArchivo(ruta: String, contenido: String): Boolean {
            try {
                val file = File(ruta)
                if (!file.exists()) {
                    file.createNewFile()
                }
                file.writeText(contenido)
                return true
            } catch (e: Exception) {
                System.out.print(e.toString())
            }
            return false
            //TODO alert dialog
        }

        fun parseArchivoAPreguntas(filePath: String): List<Pregunta> {
            val preguntaList = ArrayList<Pregunta>()

            val fileContent = leerArchivo(filePath)

            if (fileContent != null) {
                val lines = fileContent.split("\n")

                for (line in lines) {
                    val values = line.split(",")

                    if (values.size == 2) {
                        val textoPregunta = values[0].trim('"')
                        val solucion = values[1] == "1"
                        val pregunta = Pregunta(textoPregunta, solucion)
                        preguntaList.add(pregunta)
                    }
                }
            }

            return preguntaList
        }
    }
}

data class Anuncio(val idPainter: Int, val peso: Int)
