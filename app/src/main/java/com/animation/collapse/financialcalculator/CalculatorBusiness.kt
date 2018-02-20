package com.animation.collapse.financialcalculator

class CalculatorBusiness : CalculatorContract.Business {

    override fun getInitialState(): InitialState =
            InitialState(1000.0, 1.0, 12)

    override fun calculate(value: Double, interest: Double, monthsCount: Int): Double {

        // Calculo muito complexo aqui...

        return 5000.0
    }
}