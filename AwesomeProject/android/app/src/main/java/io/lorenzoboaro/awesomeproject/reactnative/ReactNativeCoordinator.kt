package it.lorenzoboaro.awesomeproject.reactnative

import android.annotation.TargetApi
import android.app.Application
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.shell.MainReactPackage
import io.lorenzoboaro.awesomeproject.BuildConfig
import io.lorenzoboaro.awesomeproject.Configuration
import java.util.Arrays
import net.mischneider.MSREventBridgePackage



class ReactNativeCoordinator internal constructor(application: Application) {

    internal var reactInstanceManager: ReactInstanceManager

    internal val reactNativeHost = object : ReactNativeHost(application) {
        override fun getUseDeveloperSupport(): Boolean = true

        override fun getPackages(): List<ReactPackage> = Arrays.asList(
            MainReactPackage(),
            MSREventBridgePackage()
        )

        override fun getJSMainModuleName(): String = "index"
    }

    init { reactInstanceManager = reactNativeHost.reactInstanceManager }

    @TargetApi(Build.VERSION_CODES.M)
    fun start(application: Application) {
        if (Configuration.isDebug && Configuration.isAtLeastMarshmallow && !Settings.canDrawOverlays(application)) {
            handleOverlayPermissionsMissing(application)
            return
        }

        if (!isInitialized() && !hasStartedCreatingContext()) {
            reactInstanceManager.createReactContextInBackground()
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun handleOverlayPermissionsMissing(application: Application) {
        // RN needs "OVERLAY_PERMISSION" in dev mode in order to render the menu and redbox and stuff.
        // In dev we check if we have that permission (if we've made it here, we don't) and send the user
        // to the settings page with a toast indicating why.
        Handler(Looper.getMainLooper()).postDelayed({
            // Delaying an arbitrary 3 seconds so that the app can bootstrap, or else this intent doesn't
            // seem to really work.
            Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                .apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
                .run(application::startActivity)

            Toast.makeText(
                application,
                "This app must have permissions to draw over other apps in order to run React Native in dev mode",
                Toast.LENGTH_LONG
            ).show()
        }, 3000L)
    }

    private fun isInitialized(): Boolean =
        reactNativeHost.hasInstance() && reactInstanceManager.currentReactContext != null

    private fun hasStartedCreatingContext(): Boolean =
        reactInstanceManager.hasStartedCreatingInitialContext()

    fun addReactInstanceListener(listener: ReactInstanceManager.ReactInstanceEventListener) {
        this.reactInstanceManager.addReactInstanceEventListener(listener)
    }

    fun removeReactInstanceListener(listener: ReactInstanceManager.ReactInstanceEventListener) {
        this.reactInstanceManager.removeReactInstanceEventListener(listener)
    }
}
