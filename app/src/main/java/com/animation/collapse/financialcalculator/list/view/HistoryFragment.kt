package com.animation.collapse.financialcalculator.list.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.animation.collapse.financialcalculator.R
import com.animation.collapse.financialcalculator.list.HistoryContract
import com.animation.collapse.financialcalculator.list.presenter.CalculationVO
import com.animation.collapse.financialcalculator.list.presenter.HistoryPresenter
import com.animation.collapse.financialcalculator.manager.view.CalculatorActivity

class HistoryFragment : Fragment(), HistoryContract.View, HistoryViewHolder.OnHistoryClick {

    val mPresenter: HistoryContract.Presenter = HistoryPresenter(this)

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = HistoryAdapter(mutableListOf(), this)

        mPresenter.onInit()
    }

    override fun loadList(list: MutableList<CalculationVO>) {
        (recyclerView.adapter as HistoryAdapter).addList(list)
    }

    override fun showCalculator(vo: CalculationVO?) {
        val intent = Intent(context, CalculatorActivity::class.java)
        intent.putExtra("HISTORY", vo)
        startActivity(intent)
    }

    override fun onClick(vo: CalculationVO) {
        mPresenter.onItemHistoryClicked(vo)
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }
}
