package it.lorenzoboaro.awesomeproject.reactnative

import com.facebook.react.ReactApplication

interface ReactAwareApplication : ReactApplication {

    fun getReactNativeCoordinator(): ReactNativeCoordinator
}