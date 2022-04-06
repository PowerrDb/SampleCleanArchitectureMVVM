package com.bestpractises.razisample.util

import android.content.Context
import androidx.biometric.BiometricManager
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity

object BiometricUtil {

    private fun hasBiometricCapability(context: Context): Int {
        val biometricManager = BiometricManager.from(context)
        return biometricManager.canAuthenticate()
    }

    fun isBiometricReady(context: Context) =
        hasBiometricCapability(context) == BiometricManager.BIOMETRIC_SUCCESS

    private fun setBiometricPromptInfo(
        title: String,
//        subtitle: String,
        description: String,
        allowDeviceCredential: Boolean
    ): BiometricPrompt.PromptInfo {
        val builder = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
//            .setSubtitle(subtitle)
            .setDescription(description)

        // Use Device Credentials if allowed, otherwise show Cancel Button
        builder.apply {
//            if (allowDeviceCredential) setDeviceCredentialAllowed(true)
//            else setNegativeButtonText("مایلم با رمز عبور وارد شوم")
            setNegativeButtonText("مایلم با رمز عبور وارد شوم")
        }

        return builder.build()
    }

    fun initBiometricPrompt(
        activity: FragmentActivity,
        listener: (String) -> Unit
    ): BiometricPrompt {
        // 1
        val executor = ContextCompat.getMainExecutor(activity)

        // 2
        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                listener("ERROR")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Log.w(this.javaClass.simpleName, "Authentication failed for an unknown reason")
                listener("ERROR")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                listener("SUCCESS")
            }
        }

        // 3
        return BiometricPrompt(activity, executor, callback)
    }

    fun showBiometricPrompt(
        title: String = "احراز هویت توسط اثر انگشت",
        description: String = "انگشت خود را روی حسگر اثر انگشت قرار دهید",
        activity: FragmentActivity,
        cryptoObject: BiometricPrompt.CryptoObject? = null,
        allowDeviceCredential: Boolean = false,
        listener: (String) -> Unit
    ) {
        // 1
        val promptInfo = setBiometricPromptInfo(
            title,
            description,
            allowDeviceCredential
        )

        // 2
        val biometricPrompt = initBiometricPrompt(activity, listener)

        // 3
        biometricPrompt.apply {
            if (cryptoObject == null) authenticate(promptInfo)
            else authenticate(promptInfo, cryptoObject)
        }
    }
}