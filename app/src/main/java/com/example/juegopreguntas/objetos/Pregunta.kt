package com.example.juegopreguntas.objetos

data class Pregunta(var textoPregunta: String, var solucion: Boolean) {
    constructor() : this("", false)
}
