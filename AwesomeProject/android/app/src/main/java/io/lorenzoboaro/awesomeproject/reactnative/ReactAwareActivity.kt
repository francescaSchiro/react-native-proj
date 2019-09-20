package io.lorenzoboaro.awesomeproject.reactnative

import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import com.facebook.react.ReactInstanceManager
import com.facebook.react.bridge.ReactContext
import com.facebook.react.devsupport.DoubleTapReloadRecognizer
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import it.lorenzoboaro.awesomeproject.reactnative.ReactAwareApplication
import it.lorenzoboaro.awesomeproject.reactnative.ReactNativeUtils
import io.lorenzoboaro.awesomeproject.Configuration

abstract class ReactAwareActivity : AppCompatActivity(), DefaultHardwareBackBtnHandler,
                                         ReactInstanceManager.ReactInstanceEventListener {

    private val reactApplication by lazy { application as ReactAwareApplication }
    internal val reactNativeCoordinator by lazy { reactApplication.getReactNativeCoordinator() }
    private val reactInstanceManager by lazy { reactNativeCoordinator.reactInstanceManager }
    private val mDoubleTapReloadRecognizer = DoubleTapReloadRecognizer()

    override fun onResume() {
        super.onResume()
        reactInstanceManager.onHostResume(this, this)
        reactNativeCoordinator.addReactInstanceListener(this)
    }

    override fun onPause() {
        super.onPause()
        reactInstanceManager.onHostPause(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        reactInstanceManager.onHostDestroy(this)
        reactNativeCoordinator.removeReactInstanceListener(this)
    }

    override fun onReactContextInitialized(context: ReactContext) { }

    override fun invokeDefaultOnBackPressed() = onBackPressed()

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if (Configuration.isDebug) {
            if (keyCode == KeyEvent.KEYCODE_MENU) {
                reactInstanceManager.devSupportManager?.showDevOptionsDialog()
                return true
            }
            if (keyCode == 0) { // this is the "backtick"
                reactInstanceManager.devSupportManager?.showDevOptionsDialog()
                return true
            }
            if (mDoubleTapReloadRecognizer.didDoubleTapR(keyCode, currentFocus)) {
                reactInstanceManager.devSupportManager?.handleReloadJS()
            }
        }

        return super.onKeyUp(keyCode, event)
    }

    protected fun emitEvent(eventName: String, `object`: Any? = null) =
        ReactNativeUtils.maybeEmitEvent(reactInstanceManager.currentReactContext,
            eventName,
            `object`)

    protected fun onReactBackPress(): Unit? = reactInstanceManager.onBackPressed()
}