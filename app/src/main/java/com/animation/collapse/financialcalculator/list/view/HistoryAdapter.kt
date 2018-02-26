package com.animation.collapse.financialcalculator.list.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.animation.collapse.financialcalculator.R
import com.animation.collapse.financialcalculator.list.presenter.CalculationVO

class HistoryAdapter(val mList: MutableList<CalculationVO>,
                     val onClickListener: HistoryViewHolder.OnHistoryClick) :
        RecyclerView.Adapter<HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):
            HistoryViewHolder {

        val view = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view, onClickListener)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder?, position: Int) {
        holder?.bind(mList[position])
    }

    fun addList(list: MutableList<CalculationVO>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }
}