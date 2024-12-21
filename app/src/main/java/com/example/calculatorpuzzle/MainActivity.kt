package com.example.calculatorpuzzle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PuzzleScreen()
            }
        }
    }
}

@Composable
fun PuzzleScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel()
) {
    val state = viewModel.state

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Math Puzzle",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 32.sp, color = Color.Black),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = "Equation: ${state.equation}",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 28.sp, color = Color.Black),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = state.userAnswer,
            onValueChange = { newAnswer ->
                viewModel.onAction(CalculatorAction.NumberInput(newAnswer))
            },
            label = { Text("Your Answer") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .background(Color(0xFF4A6E83), shape = MaterialTheme.shapes.small),
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp, color = Color.Black)
        )

        state.isCorrect?.let {
            Text(
                text = if (it) "Correct!" else "Wrong! The correct answer is ${state.correctAnswer}",
                color = if (it) Color.Green else Color.Red,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 22.sp),
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { viewModel.onAction(CalculatorAction.GeneratePuzzle) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier
                    .height(60.dp)
                    .width(200.dp)
            ) {
                Text(text = "Generate Puzzle", fontSize = 20.sp)
            }

            Button(
                onClick = { viewModel.onAction(CalculatorAction.CheckAnswer(state.userAnswer)) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)),
                modifier = Modifier
                    .height(60.dp)
                    .width(200.dp)
            ) {
                Text(text = "Check Answer", fontSize = 20.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        PuzzleScreen()
    }
}
