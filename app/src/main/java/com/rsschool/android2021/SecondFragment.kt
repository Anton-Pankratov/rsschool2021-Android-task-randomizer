package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class SecondFragment : BaseFragment(R.layout.fragment_second) {

    private var backButton: Button? = null
    private var result: TextView? = null

    private var valueInterface: IPreviousValuePass? = null

    private val minValue = arguments?.getInt(MIN_VALUE_KEY) ?: 0
    private val maxValue = arguments?.getInt(MAX_VALUE_KEY) ?: 0
    private var previousValue = "0"

    override fun findViews() {
        result = view?.findViewById(R.id.result)
        backButton = view?.findViewById(R.id.back)
    }

    override fun setFragmentFunctions() {
        result?.text = generate(minValue, maxValue).toString()
    }

    override fun setButtonClick() {
        backButton?.setOnClickListener {
            valueInterface?.passPreviousValue(previousValue)
        }
    }

    fun setPreviousValuePassInterface(valueInterface: IPreviousValuePass) {
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