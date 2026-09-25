package fr.alnews2.calculatricegpt.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CalculatorTest {
    private val calculator = Calculator()

    @Test fun addition() =
        assertEquals(7.0, calculator.calculate(3.0, Operation.ADD, 4.0).getOrThrow(), 0.0)

    @Test fun subtraction() =
        assertEquals(2.0, calculator.calculate(5.0, Operation.SUBTRACT, 3.0).getOrThrow(), 0.0)

    @Test fun multiplication() =
        assertEquals(12.0, calculator.calculate(3.0, Operation.MULTIPLY, 4.0).getOrThrow(), 0.0)

    @Test fun division() =
        assertEquals(2.5, calculator.calculate(5.0, Operation.DIVIDE, 2.0).getOrThrow(), 0.0)

    @Test fun divisionByZeroFails() =
        assertTrue(calculator.calculate(5.0, Operation.DIVIDE, 0.0).isFailure)
}
