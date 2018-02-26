package com.animation.collapse.financialcalculator.list

import com.animation.collapse.financialcalculator.list.presenter.CalculationVO

interface HistoryContract {

    interface View {
        fun loadList(list: MutableList<CalculationVO>)
        fun showCalculator(vo: CalculationVO?)
        fun showError(error: String)
    }

    interface Presenter {
        fun onInit()
        fun onItemHistoryClicked(vo: CalculationVO)
        fun onCalculatorCliecked()
    }

    interface Model {
        fun getHistoryList(): MutableList<CalculationVO>
    }
}