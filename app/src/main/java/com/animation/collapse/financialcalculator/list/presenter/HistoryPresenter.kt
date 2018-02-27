package com.animation.collapse.financialcalculator.list.presenter

import android.os.AsyncTask
import com.animation.collapse.financialcalculator.list.HistoryContract
import com.animation.collapse.financialcalculator.list.model.HistoryBusiness

class HistoryPresenter(val mView: HistoryContract.View) : HistoryContract.Presenter {

    val mBusiness: HistoryContract.Model = HistoryBusiness()

    override fun onInit() {
        GetHistoryTask(this).execute()
    }

    override fun onItemHistoryClicked(vo: CalculationVO) {
        mView.showCalculator(vo)
    }

    override fun onCalculatorCliecked() {
        mView.showCalculator(null)
    }

    private fun callService(): MutableList<CalculationVO> = mBusiness.getHistoryList()

    private fun loadList(result: MutableList<CalculationVO>?) {
        if (result != null) mView.loadList(result)
        else mView.showError("Erro ao carregar o hitórico")
    }

    class GetHistoryTask(val mPresenter: HistoryPresenter) : AsyncTask<Void, Void, MutableList<CalculationVO>>() {

        override fun doInBackground(vararg p0: Void?): MutableList<CalculationVO> = mPresenter.callService()

        override fun onPostExecute(result: MutableList<CalculationVO>?) {
            mPresenter.loadList(result)
        }
    }
}