package com.example.juegopreguntas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.juegopreguntas.Rutas.Rutas
import com.example.juegopreguntas.metodos.MetodosUtiles

@Composable
fun PantallaInicio(navController: NavHostController?) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Quiz Cerebro Galaxia", fontFamily = FontFamily.Cursive, fontSize = 42.sp)
        Image(
            painter = painterResource(id = R.drawable.cerebrogalaxia),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(8.dp)
                .weight(1F)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
        ) {
            MetodosUtiles.BotonConFuncion("Modo libre", {
                navController?.navigate(Rutas.PantallaCuestionario.ruta)
            })

            MetodosUtiles.BotonConFuncion("Modo Examen", {
                navController?.navigate(Rutas.PantallaExamen.ruta)
            })

            MetodosUtiles.BotonConFuncion("Estadisticas", { })

            MetodosUtiles.BotonConFuncion("Contribuir Pregunta", { })
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewHome() {
    PantallaInicio(navController = null)
}