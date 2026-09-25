package fr.alnews2.calculatricegpt

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import fr.alnews2.calculatricegpt.domain.Calculator
import fr.alnews2.calculatricegpt.domain.Operation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CalculatorScreen() }
    }
}

@Composable
private fun CalculatorScreen() {
    var display by remember { mutableStateOf("0") }
    var storedValue by remember { mutableStateOf<Double?>(null) }
    var pendingOperation by remember { mutableStateOf<Operation?>(null) }
    var enteringNumber by remember { mutableStateOf(false) }
    val activity = LocalContext.current as? Activity

    fun input(value: String) {
        display = if (!enteringNumber || display == "0") value else display + value
        enteringNumber = true
    }
    fun clear() {
        display = "0"; storedValue = null; pendingOperation = null; enteringNumber = false
    }
    fun operation(op: Operation) {
        storedValue = display.toDoubleOrNull()
        pendingOperation = op
        enteringNumber = false
    }
    fun equals() {
        val left = storedValue ?: return
        val op = pendingOperation ?: return
        val right = display.toDoubleOrNull() ?: return
        display = Calculator().calculate(left, op, right).fold(
            onSuccess = ::formatResult,
            onFailure = { "Erreur" }
        )
        storedValue = null; pendingOperation = null; enteringNumber = false
    }

    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(19.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    tonalElevation = 2.dp,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "CalculatriceGPT",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    color = Color.Black,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = display,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = Color.White,
                            style = MaterialTheme.typography.displayLarge
                        )
                    }
                }

                listOf(
                    listOf("7", "8", "9", "÷"),
                    listOf("4", "5", "6", "×"),
                    listOf("1", "2", "3", "−"),
                    listOf("0", "C", "=", "+")
                ).forEach { row ->
                    Row(
                        Modifier.fillMaxWidth().weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        row.forEach { label ->
                            Button(
                                onClick = {
                                    when (label) {
                                        "C" -> clear()
                                        "=" -> equals()
                                        "+" -> operation(Operation.ADD)
                                        "−" -> operation(Operation.SUBTRACT)
                                        "×" -> operation(Operation.MULTIPLY)
                                        "÷" -> operation(Operation.DIVIDE)
                                        else -> input(label)
                                    }
                                },
                                modifier = Modifier.weight(1f)
                            ) { Text(label) }
                        }
                    }
                }

                OutlinedButton(
                    onClick = { activity?.finish() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Quitter")
                }

                Text(
                    text = "application générée par une intelligence artificielle ChatGPT",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    }
}

private fun formatResult(value: Double): String =
    if (value % 1.0 == 0.0) value.toLong().toString() else value.toString()
