package it.lorenzoboaro.awesomeproject.reactnative

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.view.animation.Animation
import com.facebook.react.ReactRootView
import com.facebook.react.devsupport.DoubleTapReloadRecognizer
import io.lorenzoboaro.awesomeproject.Configuration
import io.lorenzoboaro.awesomeproject.R
import io.lorenzoboaro.awesomeproject.reactnative.ReactConfiguration
import io.lorenzoboaro.awesomeproject.reactnative.ReactInterface
import java.util.Locale

class ReactNativeFragment : Fragment(), ReactInterface, ReactNativeFragmentViewGroup.KeyListener {
    private val mDoubleTapReloadRecognizer = DoubleTapReloadRecognizer()

    private val reactNativeCoordinator by lazy {
        (activity?.application as ReactAwareApplication?)?.getReactNativeCoordinator()
    }
    private val reactInstanceManager by lazy { reactNativeCoordinator?.reactInstanceManager }

    private var instanceId: String? = null
    private var reactRootView: ReactRootView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (instanceId == null) {
            instanceId = if (savedInstanceState == null) {
                val moduleName = arguments?.getString(ReactNativeIntents.EXTRA_MODULE_NAME)
                String.format(Locale.ENGLISH, "%1s_fragment_%2\$d", moduleName, UUID++)
            } else {
                savedInstanceState.getString(INSTANCE_ID_PROP)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initReactNative()
    }

    private fun initReactNative() {
        if (reactRootView != null || view == null) return
        onAttachWithReactContext()
    }

    private fun onAttachWithReactContext() {
        if (view == null) return

        val (moduleName, props) = arguments?.let {
            Pair(it.getString(ReactNativeIntents.EXTRA_MODULE_NAME), it.getBundle(ReactNativeIntents.EXTRA_PROPS))
        } ?: Pair("", Bundle())

        props.putString(INSTANCE_ID_PROP, instanceId)

        if (reactRootView == null) {
            val reactViewStub = view?.findViewById<ViewStub>(R.id.react_root_view_stub)
            reactRootView = reactViewStub?.inflate() as ReactRootView?
        }

        reactRootView?.startReactApplication(reactInstanceManager, moduleName, props)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_react_native, container, false)
            .apply {
                findViewById<ReactNativeFragmentViewGroup>(R.id.content_container)
                    .apply { setKeyListener(this@ReactNativeFragment) }
            }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(INSTANCE_ID_PROP, instanceId)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? = null

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        reactRootView?.unmountReactApplication()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean = false

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if (Configuration.isDebug) {
            if (keyCode == KeyEvent.KEYCODE_MENU) {
                reactInstanceManager?.devSupportManager?.showDevOptionsDialog()
                return true
            }
            if (keyCode == 0) { // this is the "backtick"
                reactInstanceManager?.devSupportManager?.showDevOptionsDialog()
                return true
            }
            if (mDoubleTapReloadRecognizer.didDoubleTapR(keyCode, activity?.currentFocus)) {
                reactInstanceManager?.devSupportManager?.handleReloadJS()
                return true
            }
        }
        return false
    }

    override fun getInstanceId(): String? = instanceId

    override fun getReactRootView(): ReactRootView? = reactRootView

    override fun emitEvent(eventName: String, `object`: Any) {
        val key = String.format(Locale.ENGLISH, "NativeScreen.%s.%s", eventName, instanceId)
        ReactNativeUtils.maybeEmitEvent(reactInstanceManager?.currentReactContext, key, `object`)
    }

    companion object {
        private val TAG = ReactNativeFragment::class.java.simpleName

        private const val INSTANCE_ID_PROP = "nativeNavigationInstanceId"

        // An incrementing ID to identify each ReactNativeActivity instance (used in `instanceId`)
        private var UUID = 1

        fun newInstance(moduleName: String, props: Bundle?): ReactNativeFragment {
            return ReactNativeFragment().apply {
                arguments = Bundle().apply {
                    putString(ReactNativeIntents.EXTRA_MODULE_NAME, moduleName)
                    putBundle(ReactNativeIntents.EXTRA_PROPS, props)
                }
            }
        }

        fun newInstance(config: ReactConfiguration): ReactNativeFragment {
            return ReactNativeFragment().apply {
                arguments = Bundle().apply {
                    putString(ReactNativeIntents.EXTRA_MODULE_NAME, config.moduleName)
                    putBundle(ReactNativeIntents.EXTRA_PROPS, config.mergedProps)
                }
            }
        }
    }
}
