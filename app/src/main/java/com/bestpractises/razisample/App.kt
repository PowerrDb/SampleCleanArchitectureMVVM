package com.bestpractises.razisample

import android.app.Application
import android.content.Context
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatDelegate
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp
import es.dmoral.toasty.Toasty
import timber.log.Timber
import timber.log.Timber.DebugTree


@HiltAndroidApp
class App : Application() {

    companion object {
        private lateinit var context: Context

        fun getAppContext(): Context {
            return context
        }


    }

    var isLoading: Boolean = true

    override fun onCreate() {
        super.onCreate()
        context = this


        Hawk.init(this).build()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        val font = Typeface.createFromAsset(
            assets,
            "fonts/iran_sans_mobile.ttf"
        )

        Toasty.Config.getInstance()
            .setToastTypeface(font) // optional
            .setTextSize(11)
            .apply()

        AppSession.saveUuid()

        initTheme()

    }

    fun initTheme() {
        if (AppSession.getDarkTheme().isNotEmpty() && AppSession.getDarkTheme() == "DARK") {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

}