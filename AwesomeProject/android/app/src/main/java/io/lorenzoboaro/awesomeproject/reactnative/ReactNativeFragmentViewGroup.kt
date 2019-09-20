package it.lorenzoboaro.awesomeproject.reactnative

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.widget.FrameLayout

import com.facebook.react.ReactRootView

/**
 * Root ViewGroup for [ReactNativeFragment] that allows it to get KeyEvents.
 */
class ReactNativeFragmentViewGroup : FrameLayout {

    private val reactRootView: ReactRootView? = null
    private var keyListener: KeyListener? = null

    interface KeyListener {
        fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean

        fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
        : super(context, attrs, defStyleAttr)

    internal fun setKeyListener(keyListener: KeyListener?) {
        this.keyListener = keyListener
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        var handled = super.onKeyDown(keyCode, event)
        if (!handled && keyListener != null) {
            handled = keyListener!!.onKeyDown(keyCode, event)
        }
        return handled
    }
}
