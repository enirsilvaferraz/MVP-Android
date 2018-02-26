package com.animation.collapse.financialcalculator.manager.presenter

import android.os.AsyncTask
import com.animation.collapse.financialcalculator.list.presenter.CalculationVO
import com.animation.collapse.financialcalculator.manager.CalculatorContract
import com.animation.collapse.financialcalculator.manager.model.CalculatorBusiness

class CalculatorPresenter(view: CalculatorContract.View) : CalculatorContract.Presenter {

    val mView: CalculatorContract.View = view
    val mBusiness: CalculatorContract.Business

    init {
        mBusiness = CalculatorBusiness()
    }

    override fun onInit(calculationVO: CalculationVO) {
        mView.setValue(calculationVO.getValueFormatted())
        mView.setInterest(calculationVO.getInterestFormatted())
        mView.setMonthsCount(calculationVO.getMonthsCountFormatted())
        mView.setResult(calculationVO.getResultFormatted())
    }

    override fun onClearClicked() {
        mView.setValue(null)
        mView.setInterest(null)
        mView.setMonthsCount(null)
        mView.setResult(null)
    }

    override fun onCalculateClicked(value: String, interest: String, monthsCount: String) {

        if (value.toDoubleOrNull() == null)
            mView.showError("O campo valor é inválido")

        else if (interest.toDoubleOrNull() == null)
            mView.showError("O campo juros é inválido")

        else if (monthsCount.toIntOrNull() == null)
            mView.showError("O campo quantidade de meses é inválido")

        val calculationVO = CalculationVO(value, interest, monthsCount)
        TaskGetResult(this).execute(calculationVO)
    }

    fun callService(calculationVO: CalculationVO): CalculationVO {
        return mBusiness.calculate(calculationVO)
    }

    fun showResult(calculationVO: CalculationVO?) {
        if (calculationVO != null) {
            mView.setResult(calculationVO.getResultFormatted())
        } else {
            mView.showError("Problemas no cálculo")
        }
    }

    private class TaskGetResult(val presenter: CalculatorPresenter) :
            AsyncTask<CalculationVO, Void, CalculationVO>() {

        override fun doInBackground(vararg params: CalculationVO?): CalculationVO {
            return presenter.callService(params[0]!!)
        }

        override fun onPostExecute(result: CalculationVO?) {
            presenter.showResult(result)
        }
    }
}
