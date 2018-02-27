package com.animation.collapse.financialcalculator.manager

import com.animation.collapse.financialcalculator.list.presenter.CalculationVO

interface CalculatorContract {

    interface View {
        fun setValue(value: String?)
        fun setInterest(value: String?)
        fun setMonthsCount(value: String?)
        fun setResult(value: String?)
        fun showError(error: String)
    }

    interface Presenter {
        fun onInit(calculationVO: CalculationVO)
        fun onClearClicked()
        fun onCalculateClicked(value: String,
               interest: String, monthsCount: String)
    }

    interface Business {
        fun calculate(calculationVO: CalculationVO): CalculationVO
        fun save(calculationVO: CalculationVO)
    }
}