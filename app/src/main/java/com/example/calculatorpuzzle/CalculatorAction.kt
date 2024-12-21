package com.example.calculatorpuzzle

sealed class CalculatorAction {
    data class NumberInput(val newAnswer: String): CalculatorAction()
    object Clear: CalculatorAction()
    object Delete: CalculatorAction()
    object Decimal: CalculatorAction()
    object Calculate: CalculatorAction()
    data class Operation(val operation: CalculatorOperation): CalculatorAction()
    data class CheckAnswer(val userAnswer: String): CalculatorAction()
    object GeneratePuzzle: CalculatorAction()
}
