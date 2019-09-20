package io.lorenzoboaro.awesomeproject

import android.os.Build

object Configuration {
    val isDebug: Boolean
        get() = BuildConfig.BUILD_TYPE == BuildConfig.DEVELOPMENT

    val isProduction: Boolean
        get() = BuildConfig.BUILD_TYPE == BuildConfig.RELEASE

    val isAtLeastMarshmallow: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    val isAtLeastOreo: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
}