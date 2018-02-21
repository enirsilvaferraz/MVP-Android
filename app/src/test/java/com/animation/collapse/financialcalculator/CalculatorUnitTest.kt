package com.animation.collapse.financialcalculator

import org.junit.Assert
import org.junit.Test

class CalculatorUnitTest {

    @Test
    fun calculateValue_isCorrect() {
        val result = CalculatorBusiness().calculate(1000.0, 1.0, 12)
        Assert.assertEquals(1126.83, result, 2.0)
    }
}
