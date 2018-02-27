package com.animation.collapse.financialcalculator

import android.content.Context
import com.animation.collapse.financialcalculator.list.model.CalculatorRepository
import com.animation.collapse.financialcalculator.list.presenter.CalculationVO
import com.animation.collapse.financialcalculator.manager.model.CalculatorBusiness
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class CalculatorUnitTest {

    lateinit var mRepository: CalculatorRepository

    @Test
    fun calculateValue_isCorrect() {

        val calculatoVO = CalculationVO(1000.0, 1.0, 12)
        val result = CalculatorBusiness().calculate(calculatoVO)

        Assert.assertEquals(1126.83, result.result, 2.0)
    }

    @Test
    fun save_success() {

        val mock = Mockito.mock(CalculatorRepository::class.java)

        val calculatoVO = CalculationVO(1000.0, 1.0, 12)
        CalculatorBusiness().save(calculatoVO)

        Mockito.verify(mock.save(calculatoVO.toModel()))
    }
}
