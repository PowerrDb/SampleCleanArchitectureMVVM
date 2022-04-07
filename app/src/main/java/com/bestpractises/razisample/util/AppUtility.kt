package com.bestpractises.razisample.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.CountDownTimer
import android.provider.MediaStore.Images
import android.text.method.PasswordTransformationMethod
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.bestpractises.razisample.AppSession
import com.bestpractises.razisample.BuildConfig
import com.bestpractises.razisample.network.NetworkConst
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*


object AppUtility {

    fun getImageUri(context: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            Images.Media.insertImage(context.contentResolver, inImage, "avatar", null)
        return Uri.parse(path)
    }

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

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun EditText.showKeyboard() {
        requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)

    }

    fun EditText.hideKeyboard(clearFocus: Boolean = true) {
        if (clearFocus)
            clearFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)

    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Dialog.hideKeyboard() {
        this.window?.let {
            it.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun updatePasswordVisibility(editText: EditText) {
        if (editText.transformationMethod is PasswordTransformationMethod) {
            editText.transformationMethod = null
        } else {
            editText.transformationMethod = PasswordTransformationMethod()
        }
        editText.setSelection(editText.length())
    }

    fun reverseTimer(
        seconds: Int = if (BuildConfig.DEBUG) 10 else (3 * 60),
        tv: TextView,
        callback: (String) -> Unit
    ): CountDownTimer {
        return object : CountDownTimer((seconds * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var seconds = (millisUntilFinished / 1000).toInt()
                val minutes = seconds / 60
                seconds %= 60
                tv.text = String.format("%02d", minutes) + ":" + String.format("%02d", seconds)
            }

            override fun onFinish() {
                tv.text = "00:00"
                callback("FINISH")
            }
        }
    }

    /**
     * check code validations.
     *
     * @param target
     * @return
     */
    fun isValidCode(target: CharSequence): Boolean {
        return target.length == 6
    }

    fun changeDrawableColor(view: View, mColor: String) {

        try {
            val color = Color.parseColor(mColor)
            val backgroundGradient = view.background as GradientDrawable
            backgroundGradient.setColor(color)
        } catch (e: Exception) {
            e.stackTrace
        }

    }

    fun sharePost(context: Context) {
        val url = NetworkConst.BASE_URL_API
        val sb = StringBuilder()
        sb.append(url)
        val shareBody = sb.toString()
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "اشتراک تالار")
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
        context.startActivity(Intent.createChooser(sharingIntent, "اشتراک تالار"))
    }

    fun convertDpToPixel(context: Context, dp: Float): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun convertPixelsToDp(context: Context, px: Float): Float {
        return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun getDecimalFormattedString(value: String): String? {
        if (value.isEmpty()) return ""
        val lst = StringTokenizer(value, ".")
        var str1 = value
        var str2 = ""
        if (lst.countTokens() > 1) {
            str1 = lst.nextToken()
            str2 = lst.nextToken()
        }
        var str3 = ""
        var i = 0
        var j = -1 + str1.length
        if (str1[-1 + str1.length] == '.') {
            j--
            str3 = "."
        }
        var k = j
        while (true) {
            if (k < 0) {
                if (str2.length > 0) str3 = "$str3.$str2"
                return str3
            }
            if (i == 3) {
                str3 = ",$str3"
                i = 0
            }
            str3 = str1[k].toString() + str3
            i++
            k--
        }
    }


    fun isValidIranianNationalCode(input: String): Boolean {
        if (!"^\\d{10}$".toRegex().matches(input))
            return false
        input.toCharArray().map(Character::getNumericValue).let {
            val check = it[9]
            val sum = (0..8).map { i -> it[i] * (10 - i) }.sum() % 11
            return sum < 2 && check == sum || sum >= 2 && check + sum == 11
        }
    }

    fun getVersionName(): String? {
        return BuildConfig.VERSION_NAME
    }

    fun getVersionCode(): Int {
        return BuildConfig.VERSION_CODE
    }

    fun getErrorMessageStr(context: Context?, e: Throwable?): String {
        var error_message: String
        error_message =
            if (e is IOException && !NetworkAvailable.isNetworkAvailable(context)) {
                "Network Connection Error or no network available"
            } else {
                "${e?.message}"
            }
        return error_message
    }

    fun isInteger(str: String?) = str?.toIntOrNull()?.let { true } ?: false



    fun getChangedColorDrawable(
        context: Context,
        drawable: Drawable,
        color: Int,
        alpha: Int = 255
    ): Drawable {
        val wrappedDrawable: Drawable = DrawableCompat.wrap(drawable)
        DrawableCompat.setTint(wrappedDrawable, ColorUtils.setAlphaComponent(color, alpha))
        return wrappedDrawable
    }

}


