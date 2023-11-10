package com.example.juegopreguntas.Rutas

sealed class Rutas (val ruta: String){
    object PantallaInicio: Rutas("inicio")
    object PantallaCuestionario: Rutas("cuertionario")
    object PantallaExamen: Rutas("examen")
    object PantallaResultadoExamen: Rutas("resultadoexamen")
}