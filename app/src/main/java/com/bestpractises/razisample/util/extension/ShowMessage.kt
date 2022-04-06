package com.bestpractises.razisample.util.extension


import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.errorMessage(text: String, isNeedVibrator: Boolean = false) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT ).show()
    if (isNeedVibrator) {
//        AppUtils.vibration(requireContext())
    }
}

fun Fragment.successMessage(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT ).show()
}