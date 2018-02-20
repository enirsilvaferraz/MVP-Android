package com.animation.collapse.financialcalculator

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

/**
 * A placeholder fragment containing a simple view.
 */
@Suppress("UNREACHABLE_CODE")
class CalculatorFragment : Fragment(), CalculatorContract.View {

    lateinit var txValue:TextView
    lateinit var txInterest:TextView
    lateinit var txMonthsCount:TextView
    lateinit var txResult:TextView
    lateinit var btClear:Button
    lateinit var btcalculate:Button

    lateinit var mPresenter:CalculatorContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)

        btClear.setOnClickListener({
            mPresenter.onClearClicked()
        })

        btcalculate.setOnClickListener({
            mPresenter.onCalculateClicked(txValue.text.toString(), txInterest.text.toString(), txMonthsCount.text.toString())
        })
    }

    override fun setValue(value: String?) {
        txValue.setText(value)
    }

    override fun setInterest(value: String?) {
        txInterest.setText(value)
    }

    override fun setMonthsCount(value: String?) {
        txMonthsCount.setText(value)
    }

    override fun setResult(value: String?) {
        txResult.setText(value)
    }
}
