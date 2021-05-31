package com.rsschool.android2021

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class FirstFragment : BaseFragment(R.layout.fragment_first) {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minValueField: EditText? = null
    private var maxValueField: EditText? = null

    private var valuesInterface: IMinMaxValuesPass? = null

    private val randomValue get() = arguments?.getInt(PREVIOUS_RESULT_KEY)

    override fun findViews() {
        generateButton = view?.findViewById(R.id.generate)
        previousResult = view?.findViewById(R.id.previous_result)
        minValueField = view?.findViewById(R.id.min_value)
        maxValueField = view?.findViewById(R.id.max_value)
    }

    override fun setRandomValueText() {
        "Previous result: $randomValue".apply {
            previousResult?.text = this
        }
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

    /**
     * Jvm static annotation need for Java because "companion object" not exist
     * in this language and it understand that as "static" value.
     */
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