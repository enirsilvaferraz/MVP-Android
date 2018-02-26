package com.animation.collapse.financialcalculator.list.model

import com.animation.collapse.financialcalculator.list.HistoryContract
import com.animation.collapse.financialcalculator.list.presenter.CalculationVO

class HistoryBusiness : HistoryContract.Model {

    val mRepository = CalculatorRepository()

    override fun getHistoryList(): MutableList<CalculationVO> {
        return convertToVO(mRepository.getHistory())
    }

    private fun convertToVO(origin: MutableList<CalculationModel>): MutableList<CalculationVO> {
        val destination = mutableListOf<CalculationVO>()
        origin.mapTo(destination, { it.toVO() })
        return destination
    }
}