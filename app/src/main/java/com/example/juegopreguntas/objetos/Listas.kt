package com.example.juegopreguntas.objetos

import com.example.juegopreguntas.metodos.Anuncio
import com.example.juegopreguntas.metodos.MetodosUtiles.Companion.fillListaAnuncios
import com.example.juegopreguntas.metodos.MetodosUtiles.Companion.fillListaPreguntasPrueba

object Preguntas {
    //var listaPreguntas: MutableList<Pregunta> = fillListaPreguntas("${this.getFilesDir()}/preguntas.csv")
    var listaPreguntas: MutableList<Pregunta> = fillListaPreguntasPrueba()
}

object Respuestas {
    val respuestasUsuario = mutableMapOf<Int, Int>()
}

object Anuncios {
    val anuncios: MutableList<Anuncio> = fillListaAnuncios()
}