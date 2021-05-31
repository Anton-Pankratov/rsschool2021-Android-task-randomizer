package com.rsschool.android2021

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes val layout: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWindowAdjustResizeMode()
        findViews()
        setRandomValueText()
        setButtonClick()
    }

    abstract fun findViews()

    abstract fun setRandomValueText()

    abstract fun setButtonClick()

    @Suppress("DEPRECATION")
    private fun setWindowAdjustResizeMode() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            activity?.window?.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
            )
        }
    }
}