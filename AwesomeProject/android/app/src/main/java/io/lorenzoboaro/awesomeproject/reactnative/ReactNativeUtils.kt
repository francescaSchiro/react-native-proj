package it.lorenzoboaro.awesomeproject.reactnative

import com.facebook.react.bridge.ReactContext
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter

internal object ReactNativeUtils {
    fun maybeEmitEvent(context: ReactContext?, name: String, data: Any?) {
        if (context == null) {
            throw IllegalArgumentException(
                String.format("reactContext is null (calling event: %s)", name))
        }
        if (context.hasActiveCatalystInstance()) {
            try {
                context.getJSModule(RCTDeviceEventEmitter::class.java).emit(name, data)
            } catch (e: RuntimeException) {
                // the JS bundle hasn't finished executing, so this call is going to be lost.
                // In the future, we could maybe set something up to queue the call, and then pass them through once
                // the bundle has finished getting parsed, but for now I am going to just swallow the error.
            }
        }
    }
}
