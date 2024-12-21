package com.example.calculatorpuzzle

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.NumberInput -> enterUserAnswer(action.newAnswer)
            is CalculatorAction.CheckAnswer -> checkAnswer(action.userAnswer)
            is CalculatorAction.GeneratePuzzle -> generatePuzzle()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Delete -> performDeletion()
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Calculate -> performCalculation()
        }
    }

    private fun enterUserAnswer(newAnswer: String) {
        state = state.copy(userAnswer = newAnswer)
    }

    private fun checkAnswer(userAnswer: String) {
        val isCorrect = state.correctAnswer == userAnswer
        state = state.copy(
            userAnswer = userAnswer,
            isCorrect = isCorrect
        )
    }

    private fun generatePuzzle() {
        val operators = state.selectedOperators.takeIf { it.isNotEmpty() } ?: CalculatorOperation.values().toList()

        val operator = operators[Random.nextInt(operators.size)]

        val number1 = Random.nextInt(1, 10)
        val number2 = Random.nextInt(1, 10)

        val equation = when (operator) {
            CalculatorOperation.Add -> "$number1 + $number2"
            CalculatorOperation.Subtract -> "$number1 - $number2"
            CalculatorOperation.Multiply -> "$number1 * $number2"
            CalculatorOperation.Divide -> "$number1 / $number2"
        }

        val correctAnswer = when (operator) {
            CalculatorOperation.Add -> (number1 + number2).toString()
            CalculatorOperation.Subtract -> (number1 - number2).toString()
            CalculatorOperation.Multiply -> (number1 * number2).toString()
            CalculatorOperation.Divide -> {
                val result = number1.toFloat() / number2.toFloat()
                String.format("%.3f", result)
            }
        }

        state = state.copy(
            equation = equation,
            correctAnswer = correctAnswer,
            isCorrect = null,
            userAnswer = "",
        )
    }


    private fun performDeletion() {
        if (state.equation.isNotEmpty()) {
            state = state.copy(
                equation = state.equation.dropLast(1)
            )
        }
    }

    private fun performCalculation() {}

    private fun enterOperation(operation: CalculatorOperation) {
        state = state.copy(
            equation = state.equation + operation.symbol
        )
    }

    private fun enterDecimal() {}

    private fun enterNumber(number: Int) {
        state = state.copy(
            equation = state.equation + number.toString()
        )
    }
}