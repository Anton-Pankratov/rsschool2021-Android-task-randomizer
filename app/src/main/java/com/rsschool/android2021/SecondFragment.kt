package com.rsschool.android2021

import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondFragment : BaseFragment(R.layout.fragment_second) {

    private var backButton: Button? = null
    private var result: TextView? = null

    private var valueInterface: IRandomValuePass? = null

    private val minValue get() = arguments?.getInt(MIN_VALUE_KEY) ?: 0
    private val maxValue get() = arguments?.getInt(MAX_VALUE_KEY) ?: 0
    private var randomValue = "0"

    override fun findViews() {
        result = view?.findViewById(R.id.result)
        backButton = view?.findViewById(R.id.back)
    }

    override fun setRandomValueText() {
        generate(minValue, maxValue).toString().apply {
            randomValue = this
            result?.text = this
        }
    }

    override fun setButtonClick() {
        backButton?.setOnClickListener {
            valueInterface?.passPreviousValue(randomValue)
        }
    }

    fun setRandomValuePassInterface(valueInterface: IRandomValuePass) {
        this.valueInterface = valueInterface
    }

    private fun generate(min: Int, max: Int): Int {
        return (min..max).random()
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            return SecondFragment().apply {
                Bundle().apply {
                    putInt(MIN_VALUE_KEY, min)
                    putInt(MAX_VALUE_KEY, max)
                    arguments = this
                }
            }
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}