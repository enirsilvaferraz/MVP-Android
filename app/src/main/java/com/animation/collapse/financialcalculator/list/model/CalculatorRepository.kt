package com.animation.collapse.financialcalculator.list.model

import android.content.Context

open class CalculatorRepository {

    var context: Context? = null

    fun getHistory(): MutableList<CalculationModel> {

        // Recupera o shared preferences
        val sharedPref = context!!.getSharedPreferences("KEY", Context.MODE_PRIVATE)

        // Recupera a lista ja salva
        val json = sharedPref.getString("JSON_LIST", "")

        // Transaforma o json em lista
        return contertToList(json)
    }

    fun save(model: CalculationModel) {

        // Recupera o shared preferences
        val sharedPref = context!!.getSharedPreferences("KEY", Context.MODE_PRIVATE)

        // Recupera a lista ja salva
        val json = sharedPref.getString("JSON_LIST", "")

        // Transaforma o json em lista
        val list = contertToList(json)

        // Adiciona na lista
        list.add(model)

        // Salva lista
        sharedPref.edit().putString("JSON_LIST", convertToJSON(list)).apply()
    }

    private fun convertToJSON(list: MutableList<CalculationModel>): String? {
        return ""
    }

    private fun contertToList(json: String?): MutableList<CalculationModel> {
        return mutableListOf()
    }
}