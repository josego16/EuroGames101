package com.eurogames

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
actual fun getBaseUrl(): String {
    return if (isEmulator()) {
        "http://10.0.2.2:8081"
    } else {
        "https://ace-improved-sunfish.ngrok-free.app"
    }
}

private fun isEmulator(): Boolean {
    return Build.FINGERPRINT.contains("generic")
            || Build.FINGERPRINT.lowercase().contains("emulator")
            || Build.MODEL.contains("Emulator")
            || Build.MODEL.contains("Android SDK built for x86")
            || Build.MANUFACTURER.contains("Genymotion")
            || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
}