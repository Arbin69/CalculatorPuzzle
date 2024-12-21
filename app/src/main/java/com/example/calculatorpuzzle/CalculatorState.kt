package com.example.calculatorpuzzle

data class CalculatorState(
    val equation: String = "",
    val userAnswer: String = "",
    val isCorrect: Boolean? = null,
    val selectedOperators: List<CalculatorOperation> = listOf(),
    val correctAnswer: String = ""
)
