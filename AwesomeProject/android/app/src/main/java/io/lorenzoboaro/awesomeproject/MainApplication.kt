package io.lorenzoboaro.awesomeproject

import android.app.Application
import com.facebook.react.ReactNativeHost
import com.facebook.soloader.SoLoader
import it.lorenzoboaro.awesomeproject.reactnative.ReactAwareApplication
import it.lorenzoboaro.awesomeproject.reactnative.ReactNativeCoordinator

class MainApplication: Application(), ReactAwareApplication {

    private val reactCoordinator by lazy { ReactNativeCoordinator(this) }

    override fun getReactNativeHost(): ReactNativeHost = reactCoordinator.reactNativeHost

    override fun getReactNativeCoordinator(): ReactNativeCoordinator = reactCoordinator

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)

        reactCoordinator.start(this)
    }
}