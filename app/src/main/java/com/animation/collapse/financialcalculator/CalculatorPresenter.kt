package com.animation.collapse.financialcalculator

import android.os.AsyncTask

class CalculatorPresenter(view: CalculatorContract.View) : CalculatorContract.Presenter {

    val mView: CalculatorContract.View = view
    val mBusiness: CalculatorContract.Business

    init {
        mBusiness = CalculatorBusiness()
    }

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

        if (value.toDoubleOrNull() != null)
            mView.showError("O campo valor é inválido")

        else if (interest.toDoubleOrNull() != null)
            mView.showError("O campo juros é inválido")

        else if (monthsCount.toIntOrNull() != null)
            mView.showError("O campo quantidade de meses é inválido")

        else
            TaskGetResult(this).execute(value.removeCurrency(), interest.removePercentual(), monthsCount.toDouble())
    }

    fun callService(value: Double, interest: Double, monthsCount: Int): Double {
        return mBusiness.calculate(value, interest, monthsCount)
    }

    fun updateResult(result: Double) {
        mView.setResult(result.toCurrency())
    }
}

private class TaskGetResult(val presenter: CalculatorPresenter) : AsyncTask<Double, Void, Double>() {

    override fun doInBackground(vararg params: Double?): Double {
        return presenter.callService(params[0]!!, params[1]!!, params[2]!!.toInt())
    }

    override fun onPostExecute(result: Double?) {
        presenter.updateResult(result!!)
    }
}

/**
 * Extensions
 */

private fun Double.toPercentual(): String? = "${this}%"

private fun Double.toCurrency(): String? = "R$${this}"

private fun String.removeCurrency(): Double = this.replace("R$", "").toDouble()
private fun String.removePercentual(): Double = this.replace("%", "").toDouble()
