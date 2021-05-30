package com.rsschool.android2021

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class FirstFragment : BaseFragment(R.layout.fragment_first) {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minValueField: EditText? = null
    private var maxValueField: EditText? = null

    private var valuesInterface: IMinMaxValuesPass? = null

    private val previousValue = arguments?.getInt(PREVIOUS_RESULT_KEY)

    override fun findViews() {
        generateButton = view?.findViewById(R.id.generate)
        previousResult = view?.findViewById(R.id.previous_result)
        minValueField = view?.findViewById(R.id.min_value)
        maxValueField = view?.findViewById(R.id.max_value)
    }

    override fun setFragmentFunctions() {
        //previousResult?.text = "Previous result: ${result.toString()}"
    }

    override fun setButtonClick() {
        generateButton?.setOnClickListener {
            valuesInterface?.passMinMaxValues(
                minValueField?.text.toString(),
                maxValueField?.text.toString()
            )
        }
    }

    fun setMinMaxValuesPassInterface(valuesInterface: IMinMaxValuesPass) {
        this.valuesInterface = valuesInterface
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            return FirstFragment().apply {
                Bundle().apply {
                    putInt(PREVIOUS_RESULT_KEY, previousResult)
                    arguments = this
                }
            }
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}