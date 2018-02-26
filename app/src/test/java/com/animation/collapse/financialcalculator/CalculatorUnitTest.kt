package com.animation.collapse.financialcalculator

import com.animation.collapse.financialcalculator.list.model.CalculatorRepository
import com.animation.collapse.financialcalculator.list.presenter.CalculationVO
import com.animation.collapse.financialcalculator.manager.model.CalculatorBusiness
import org.junit.Assert
import org.junit.Test

class CalculatorUnitTest {

    lateinit var mRepository: CalculatorRepository

    @Test
    fun calculateValue_isCorrect() {

        val calculatoVO = CalculationVO(1000.0, 1.0, 12)
        val result = CalculatorBusiness().calculate(calculatoVO)

        Assert.assertEquals(1126.83, result.result, 2.0)
    }
}
