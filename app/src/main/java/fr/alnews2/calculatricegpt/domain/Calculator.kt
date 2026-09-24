package fr.alnews2.calculatricegpt.domain

enum class Operation(val symbol: String) {
    ADD("+"), SUBTRACT("−"), MULTIPLY("×"), DIVIDE("÷")
}

class Calculator {
    fun calculate(left: Double, operation: Operation, right: Double): Result<Double> =
        when (operation) {
            Operation.ADD -> Result.success(left + right)
            Operation.SUBTRACT -> Result.success(left - right)
            Operation.MULTIPLY -> Result.success(left * right)
            Operation.DIVIDE ->
                if (right == 0.0) Result.failure(ArithmeticException("Division by zero"))
                else Result.success(left / right)
        }
}
