package com.animation.collapse.financialcalculator

class CalculatorPresenter : CalculatorContract.Presenter {

    lateinit var mView: CalculatorContract.View
    lateinit var mBusiness: CalculatorContract.Business

    override fun onInit() {
        val initialState = mBusiness.getInitialState()
        mView.setValue(initialState.value.toCurrency())
        mView.setInterest(initialState.interest.toPercentual())
        mView.setMonthsCount(initialState.monthsCount.toString())
        mView.setResult(null)
    }

    override fun onClearClicked() {
        mView.setValue(null)
        mView.setInterest(null)
        mView.setMonthsCount(null)
        mView.setResult(null)
    }

    override fun onCalculateClicked(value: String, interest: String, monthsCount: String) {
        val result = mBusiness.calculate(value.removeCurrency(), interest.removePercentual(), monthsCount.toInt())
        mView.setResult(result.toCurrency())
    }
}

/**
 * Extensions
 */

private fun Double.toPercentual(): String? = "${this}%"
private fun Double.toCurrency(): String? = "R$${this}"

private fun String.removeCurrency(): Double = this.replace("R$","").toDouble()
private fun String.removePercentual(): Double = this.replace("%","").toDouble()
