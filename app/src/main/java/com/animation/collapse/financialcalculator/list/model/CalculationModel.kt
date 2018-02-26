package com.animation.collapse.financialcalculator.list.model

import com.animation.collapse.financialcalculator.list.presenter.CalculationVO

data class CalculationModel(val value: Double, val interest: Double,
                            val monthsCount: Int, var result: Double) {

    fun toVO(): CalculationVO = CalculationVO(value.toString(), interest.toString(),
            monthsCount.toString())
}