package com.animation.collapse.financialcalculator.list.presenter

import android.os.Parcel
import android.os.Parcelable
import com.animation.collapse.financialcalculator.list.model.CalculationModel

data class CalculationVO(val value: Double, val interest: Double,
                         val monthsCount: Int) : Parcelable

{

    val result: Double = 0.0

    constructor(value: String, interest: String, monthsCount: String) : this(
            value.removeCurrency(), interest.removePrecentual(), monthsCount.removeFormat())

    fun toModel(): CalculationModel {
        return CalculationModel(value, interest, monthsCount, 0.0)
    }

    fun getValueFormatted() = "R$${value}"
    fun getInterestFormatted() = "${interest}%"
    fun getMonthsCountFormatted() = "${result} meses"
    fun getResultFormatted() = "R$${result}"

    constructor(parcel: Parcel) : this(parcel.readDouble(),
            parcel.readDouble(), parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(value)
        parcel.writeDouble(interest)
        parcel.writeInt(monthsCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CalculationVO> {
        override fun createFromParcel(parcel: Parcel): CalculationVO {
            return CalculationVO(parcel)
        }

        override fun newArray(size: Int): Array<CalculationVO?> {
            return arrayOfNulls(size)
        }
    }
}

private fun String.removeCurrency() = this.replace("R$ ", "").toDouble()
private fun String.removePrecentual() = this.replace("%", "").toDouble()
private fun String.removeFormat() = this.replace(" meses", "").toInt()

