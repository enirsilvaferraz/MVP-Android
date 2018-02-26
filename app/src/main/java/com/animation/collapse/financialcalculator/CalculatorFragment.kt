package com.animation.collapse.financialcalculator

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

/**
 * A placeholder fragment containing a simple view.
 */
//@Suppress("UNREACHABLE_CODE")
class CalculatorFragment : Fragment(), CalculatorContract.View {

    lateinit var txValue: TextView
    lateinit var txInterest: TextView
    lateinit var txMonthsCount: TextView
    lateinit var txResult: TextView
    lateinit var btClear: Button
    lateinit var btCalculate: Button

    lateinit var mPresenter: CalculatorContract.Presenter

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
            } else if (txValue.text.toString().toDouble() < 0.0){
                showError("Esse campo deve ser maior que zero")
            }

            mPresenter.onCalculateClicked(txValue.text.toString(),
                    txInterest.text.toString(), txMonthsCount.text.toString())
        })
    }

    override fun setPresenter(presenter: CalculatorContract.Presenter) {
        mPresenter = presenter
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

    fun setResultVisibility(visible: Boolean) {
        txResult.visibility = if (visible) View.VISIBLE else View.GONE
    }

    fun showResult() {
        txResult.visibility = View.VISIBLE
    }

    fun hideResult() {
        txResult.visibility = View.GONE
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
