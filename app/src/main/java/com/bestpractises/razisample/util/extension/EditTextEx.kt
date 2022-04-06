package com.bestpractises.razisample.util.extension


import android.view.inputmethod.EditorInfo
import android.widget.EditText



fun EditText.actionSearch(callback: () -> Unit) {
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            callback()
            true
        } else false
    }
}


