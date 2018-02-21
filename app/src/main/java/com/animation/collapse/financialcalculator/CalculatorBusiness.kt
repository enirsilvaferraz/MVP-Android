package com.animation.collapse.financialcalculator

class CalculatorBusiness : CalculatorContract.Business {

    /**
     * Retorna a configuração inicial da tela
     */
    override fun getInitialState(): InitialState =
            InitialState(1000.0, 1.0, 12)

    /**
     *  Formula: M = C x (1 + i)^n
     *
     *  M = Montante
     *  C = Capital Inicial
     *  i = Taxa de Juros
     *  n = Quantidade de meses
     */
    override fun calculate(value: Double, interest: Double, monthsCount: Int): Double =
            value * Math.pow((1 + interest / 100), monthsCount.toDouble())

}