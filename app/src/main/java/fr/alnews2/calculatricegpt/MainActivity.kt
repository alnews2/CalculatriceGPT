package fr.alnews2.calculatricegpt

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var editMenuExpanded by remember { mutableStateOf(false) }
    val clipboardManager = LocalClipboardManager.current
    val activity = LocalContext.current as? Activity
    val isLandscape =
        LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    fun input(value: String) {
        display = if (!enteringNumber || display == "0") value else display + value
        enteringNumber = true
    }

    fun clear() {
        display = "0"
        storedValue = null
        pendingOperation = null
        enteringNumber = false
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
        storedValue = null
        pendingOperation = null
        enteringNumber = false
    }

    fun copyResult() {
        clipboardManager.setText(AnnotatedString(display))
    }

    fun pasteResult() {
        val pastedText = clipboardManager.getText()?.text.orEmpty()
        display = pastedText.ifEmpty { "0" }
        storedValue = null
        pendingOperation = null
        enteringNumber = pastedText.isNotEmpty()
    }

    fun onKeyPressed(label: String) {
        when (label) {
            "C" -> clear()
            "=" -> equals()
            "+" -> operation(Operation.ADD)
            "−" -> operation(Operation.SUBTRACT)
            "×" -> operation(Operation.MULTIPLY)
            "÷" -> operation(Operation.DIVIDE)
            else -> input(label)
        }
    }

    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(if (isLandscape) 8.dp else 19.dp),
                verticalArrangement = Arrangement.spacedBy(if (isLandscape) 6.dp else 12.dp)
            ) {
                CalculatorHeader(
                    editMenuExpanded = editMenuExpanded,
                    onMenuOpen = { editMenuExpanded = true },
                    onMenuDismiss = { editMenuExpanded = false },
                    onCopy = {
                        copyResult()
                        editMenuExpanded = false
                    },
                    onPaste = {
                        pasteResult()
                        editMenuExpanded = false
                    },
                    onQuit = {
                        editMenuExpanded = false
                        activity?.finish()
                    }
                )

                if (isLandscape) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ResultDisplay(
                            display = display,
                            modifier = Modifier
                                .weight(0.8f)
                                .fillMaxHeight(),
                            landscape = true
                        )
                        CalculatorKeypad(
                            modifier = Modifier
                                .weight(1.2f)
                                .fillMaxHeight(),
                            onKeyPressed = ::onKeyPressed,
                            landscape = true
                        )
                    }
                } else {
                    ResultDisplay(
                        display = display,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        landscape = false
                    )
                    CalculatorKeypad(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(4f),
                        onKeyPressed = ::onKeyPressed,
                        landscape = false
                    )
                }

                Text(
                    text = "application générée par une intelligence artificielle ChatGPT",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun CalculatorHeader(
    editMenuExpanded: Boolean,
    onMenuOpen: () -> Unit,
    onMenuDismiss: () -> Unit,
    onCopy: () -> Unit,
    onPaste: () -> Unit,
    onQuit: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            tonalElevation = 2.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "CalculatriceGPT",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .wrapContentSize(Alignment.TopEnd)
                .offset(y = (-3).dp)
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small,
                tonalElevation = 2.dp
            ) {
                IconButton(onClick = onMenuOpen) {
                    Text(
                        text = "⋮",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }

            DropdownMenu(
                expanded = editMenuExpanded,
                onDismissRequest = onMenuDismiss
            ) {
                DropdownMenuItem(
                    text = { Text("Copier") },
                    onClick = onCopy
                )
                DropdownMenuItem(
                    text = { Text("Coller") },
                    onClick = onPaste
                )
                DropdownMenuItem(
                    text = { Text("Quitter") },
                    onClick = onQuit
                )
            }
        }
    }
}

@Composable
private fun ResultDisplay(
    display: String,
    modifier: Modifier,
    landscape: Boolean
) {
    val resultTextStyle = if (landscape) {
        when {
            display.length > 18 -> MaterialTheme.typography.titleLarge
            display.length > 12 -> MaterialTheme.typography.headlineMedium
            else -> MaterialTheme.typography.displayMedium
        }
    } else {
        MaterialTheme.typography.displayLarge
    }

    Surface(
        modifier = modifier,
        color = Color.Black,
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = if (landscape) 12.dp else 0.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = display,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                style = resultTextStyle,
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Clip,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
private fun CalculatorKeypad(
    modifier: Modifier,
    onKeyPressed: (String) -> Unit,
    landscape: Boolean
) {
    val rowSpacing = if (landscape) 5.dp else 8.dp
    val rows = listOf(
        listOf("7", "8", "9", "÷"),
        listOf("4", "5", "6", "×"),
        listOf("1", "2", "3", "−"),
        listOf("0", "C", "=", "+")
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(rowSpacing)
    ) {
        rows.forEach { row ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(rowSpacing),
                verticalAlignment = Alignment.CenterVertically
            ) {
                row.forEach { label ->
                    val isDigit = label.length == 1 && label[0].isDigit()
                    val buttonColors = if (isDigit) {
                        ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    } else {
                        ButtonDefaults.buttonColors()
                    }

                    Button(
                        onClick = { onKeyPressed(label) },
                        modifier = Modifier.weight(1f),
                        shape = if (isDigit) {
                            MaterialTheme.shapes.medium
                        } else {
                            MaterialTheme.shapes.small
                        },
                        colors = buttonColors
                    ) {
                        Text(label)
                    }
                }
            }
        }
    }
}

private fun formatResult(value: Double): String =
    if (value % 1.0 == 0.0) value.toLong().toString() else value.toString()
