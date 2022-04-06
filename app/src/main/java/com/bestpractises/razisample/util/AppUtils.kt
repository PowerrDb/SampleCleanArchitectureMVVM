package com.bestpractises.razisample.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.TaskStackBuilder
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.bestpractises.razisample.BuildConfig
import com.bestpractises.razisample.MainActivity


object AppUtils {

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun EditText.showKeyboard() {
        post {
            requestFocus()
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun changeDrawableStrokeColor(v: View, width: Int = 1, color: Int) {
        val view = v.background as GradientDrawable
        view.setStroke(width, color)  // set stroke width and stroke color
    }



    fun restart(activity: Activity) {
        TaskStackBuilder.create(activity)
            .addNextIntent(Intent(activity, MainActivity::class.java))
            .addNextIntent(activity.intent)
            .startActivities()
    }

    fun changeDrawableColor(view: View, color: Int) {
        val wrappedStateDrawable = DrawableCompat.wrap(view.background).mutate()
        DrawableCompat.setTint(
            wrappedStateDrawable,
            ColorUtils.setAlphaComponent(color, 97)
        )
    }

    fun getVersionCode() = BuildConfig.VERSION_CODE

    fun setCustomFontToTabLayout(
        tabLayoutFavList: TabLayout,
        font: Typeface?
    ) {
        val vg = tabLayoutFavList.getChildAt(0) as ViewGroup
        val tabsCount = vg.childCount
        for (j in 0 until tabsCount) {
            val vgTab = vg.getChildAt(j) as ViewGroup
            val tabChildesCount = vgTab.childCount
            for (i in 0 until tabChildesCount) {
                val tabViewChild = vgTab.getChildAt(i)
                if (tabViewChild is TextView) {
                    tabViewChild.typeface = font
                    tabViewChild.includeFontPadding = false
                }
            }
        }
    }

}


