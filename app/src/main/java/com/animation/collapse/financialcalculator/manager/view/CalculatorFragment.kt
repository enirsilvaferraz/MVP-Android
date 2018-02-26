package com.animation.collapse.financialcalculator.manager.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.animation.collapse.financialcalculator.R
import com.animation.collapse.financialcalculator.list.presenter.CalculationVO
import com.animation.collapse.financialcalculator.manager.CalculatorContract
import com.animation.collapse.financialcalculator.manager.presenter.CalculatorPresenter

/**
 * A placeholder fragment containing a simple view.
 */
class CalculatorFragment : Fragment(), CalculatorContract.View {

    private lateinit var txValue: TextView
    private lateinit var txInterest: TextView
    private lateinit var txMonthsCount: TextView
    private lateinit var txResult: TextView
    private lateinit var btClear: Button
    private lateinit var btCalculate: Button

    val mPresenter: CalculatorContract.Presenter = CalculatorPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txValue = view.findViewById(R.id.calc_value)
        txInterest = view.findViewById(R.id.calc_interest)
        txMonthsCount = view.findViewById(R.id.calc_months_count)
        txResult = view.findViewById(R.id.calc_result)
        btClear = view.findViewById(R.id.calc_clear)
        btCalculate = view.findViewById(R.id.calc_calculate)

        btClear.setOnClickListener({
            mPresenter.onClearClicked()
        })

        btCalculate.setOnClickListener({

            if (txValue.text.isNullOrBlank()) {
                showError("Esse campo é obrigatório")
            } else if (txValue.text.toString().toDoubleOrNull() == null) {
                showError("Esse campo deve ser um número")
            } else if (txValue.text.toString().toDouble() < 0.0) {
                showError("Esse campo deve ser maior que zero")
            }

            mPresenter.onCalculateClicked(txValue.text.toString(),
                    txInterest.text.toString(), txMonthsCount.text.toString())
        })

        if (arguments != null) {
            mPresenter.onInit(arguments!!.getParcelable<CalculationVO>("HISTORY"))
        }
    }

    override fun setValue(value: String?) {
        txValue.text = value
    }

    override fun setInterest(value: String?) {
        txInterest.text = value
    }

    override fun setMonthsCount(value: String?) {
        txMonthsCount.text = value
    }

    override fun setResult(value: String?) {
        txResult.text = value
    }

    override fun showError(error: String) {
        Toast.makeText(context, error,
                Toast.LENGTH_SHORT).show()
    }

    class OnClickListener(val mPresenter: CalculatorContract.Presenter) : View.OnClickListener {

        override fun onClick(view: View?) {
            if (view != null) {
                when (view.id) {
                    R.id.calc_calculate ->
                        mPresenter.onCalculateClicked("", "", "")
                    R.id.calc_clear ->
                        mPresenter.onClearClicked()
                }
            }
        }
    }
}
