package com.animation.collapse.financialcalculator.list.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.animation.collapse.financialcalculator.list.presenter.CalculationVO


class HistoryViewHolder(view: View, val onClickListener: OnHistoryClick) : RecyclerView.ViewHolder(view) {

    lateinit var mValue: TextView
    lateinit var mInterest: TextView
    lateinit var mMonthsCount: TextView
    lateinit var mResult: TextView

    fun bind(vo: CalculationVO) {
        mValue.text = vo.getValueFormatted();
        mInterest.text = vo.getInterestFormatted();
        mMonthsCount.text = vo.getMonthsCountFormatted();
        mResult.text = vo.getResultFormatted();
        itemView.setOnClickListener({ onClickListener.onClick(vo) })
    }

    interface OnHistoryClick {
        fun onClick(vo: CalculationVO)
    }
}
