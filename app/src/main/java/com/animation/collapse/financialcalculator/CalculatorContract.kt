package com.animation.collapse.financialcalculator

interface CalculatorContract {

    interface View {
        fun setValue(value: String?)
        fun setInterest(value: String?)
        fun setMonthsCount(value: String?)
        fun setResult(value: String?)
        fun setPresenter(presenter: CalculatorContract.Presenter)
        fun showError(error: String)
    }

    interface Presenter {
        fun onInit()
        fun onClearClicked()
        fun onCalculateClicked(value: String, interest: String, monthsCount: String)
    }

    interface Business {
        fun getInitialState(): InitialState
        fun calculate(value: Double, interest: Double, monthsCount: Int): Double
    }
}