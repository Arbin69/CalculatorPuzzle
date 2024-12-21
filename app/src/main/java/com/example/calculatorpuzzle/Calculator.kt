package com.example.calculatorpuzzle

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp

@Composable
fun Calculator(viewModel: CalculatorViewModel) {
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Solve: ${state.equation}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.userAnswer,
            onValueChange = { viewModel.onAction(CalculatorAction.NumberInput(it)) },
            label = { Text("Your Answer") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        state.isCorrect?.let { isCorrect ->
            val resultText = if (isCorrect) "Correct!" else "Wrong! Correct answer: ${state.correctAnswer}"
            Text(
                text = resultText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = if (isCorrect) Color.Green else Color.Red,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            CalculatorButton("1", Modifier, onClick = { viewModel.onAction(CalculatorAction.NumberInput("1")) })
            CalculatorButton("2", Modifier, onClick = { viewModel.onAction(CalculatorAction.NumberInput("2")) })
            CalculatorButton("3", Modifier, onClick = { viewModel.onAction(CalculatorAction.NumberInput("3")) })
        }

        Row {
            CalculatorButton("4", Modifier, onClick = { viewModel.onAction(CalculatorAction.NumberInput("4")) })
            CalculatorButton("5", Modifier, onClick = { viewModel.onAction(CalculatorAction.NumberInput("5")) })
            CalculatorButton("6", Modifier, onClick = { viewModel.onAction(CalculatorAction.NumberInput("6")) })
        }

        Row {
            CalculatorButton("7", Modifier, onClick = { viewModel.onAction(CalculatorAction.NumberInput("7")) })
            CalculatorButton("8", Modifier, onClick = { viewModel.onAction(CalculatorAction.NumberInput("8")) })
            CalculatorButton("9", Modifier, onClick = { viewModel.onAction(CalculatorAction.NumberInput("9")) })
        }

        Row {
            CalculatorButton("0", Modifier, onClick = { viewModel.onAction(CalculatorAction.NumberInput("0")) })
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            CalculatorButton("+", Modifier, onClick = { viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add)) })
            CalculatorButton("-", Modifier, onClick = { viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Subtract)) })
            CalculatorButton("*", Modifier, onClick = { viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply)) })
            CalculatorButton("/", Modifier, onClick = { viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide)) })
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.onAction(CalculatorAction.GeneratePuzzle) }) {
            Text("Generate Puzzle")
        }
    }
}