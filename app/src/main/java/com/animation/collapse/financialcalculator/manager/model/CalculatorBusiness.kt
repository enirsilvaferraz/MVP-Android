package com.animation.collapse.financialcalculator.manager.model

import com.animation.collapse.financialcalculator.list.model.CalculatorRepository
import com.animation.collapse.financialcalculator.list.presenter.CalculationVO
import com.animation.collapse.financialcalculator.manager.CalculatorContract

class CalculatorBusiness : CalculatorContract.Business {

    val mRepository = CalculatorRepository()

    /**
     *  Formula: M = C x (1 + i)^n
     *
     *  M = Montante
     *  C = Capital Inicial
     *  i = Taxa de Juros
     *  n = Quantidade de meses
     */
    override fun calculate(calculationVO: CalculationVO): CalculationVO {

        // Convert to model
        val model = calculationVO.toModel()

        // Calculate
        model.result = model.value * Math.pow((1 + model.interest / 100), model.monthsCount.toDouble())

        // Convert to VO
        return model.toVO()
    }

    override fun save(calculationVO: CalculationVO){

        // Save model
        mRepository.save(calculationVO.toModel())
    }
}