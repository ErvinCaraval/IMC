
package com.example.imc
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imc.ui.theme.IMCTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color // Importa Color para usar colores



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMCTheme {
                IMCCalculatorScreen()
            }
        }
    }
}

@Composable
fun IMCCalculatorScreen() {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var imcResult by remember { mutableStateOf<Double?>(null) }
    var category by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(100.dp)
            .fillMaxSize()
    ) {


        Text(
            text = "Calculadora IMC",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Peso (kg)", color = Color.White) },

        modifier = Modifier.width(200.dp) // Ancho fijo de 300 dp


        )
        Spacer(modifier = Modifier.height(100.dp))
        TextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Altura (m)" ,color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(100.dp))
        Button(onClick = {
            val weightValue = weight.toDoubleOrNull()
            val heightValue = height.toDoubleOrNull()
            if (weightValue != null && heightValue != null && heightValue > 0) {
                val imc = weightValue / (heightValue * heightValue)
                imcResult = imc
                category = when {
                    imc < 18.5 -> "Bajo peso"
                    imc in 18.5..24.9 -> "Peso normal"
                    imc in 25.0..29.9 -> "Sobrepeso"
                    else -> "Obesidad"
                }
            } else {
                imcResult = null
                category = "Ingrese valores válidos"
            }
        }) {
            Text("Calcular IMC")
        }
        Spacer(modifier = Modifier.height(30.dp))
        imcResult?.let {
            Text(text = "IMC: %.2f".format(it))
            Text(text = "Categoría: $category")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IMCCalculatorPreview() {
    IMCTheme {
        IMCCalculatorScreen()
    }
}
