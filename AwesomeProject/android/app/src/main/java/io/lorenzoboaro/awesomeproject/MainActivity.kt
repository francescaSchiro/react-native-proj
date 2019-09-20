package io.lorenzoboaro.awesomeproject

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReadableMap
import io.lorenzoboaro.awesomeproject.reactnative.ReactAwareActivity
import io.lorenzoboaro.awesomeproject.reactnative.ReactConfiguration
import it.lorenzoboaro.awesomeproject.reactnative.ReactNativeFragment
import kotlinx.android.synthetic.main.activity_main.*
import net.mischneider.MSREventBridgeEventReceiver
import net.mischneider.MSREventBridgeReceiverCallback

class MainActivity : ReactAwareActivity(), MSREventBridgeEventReceiver {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = resources.getString(R.string.beer_of_the_month)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onReactContextInitialized(context: ReactContext) {
        super.onReactContextInitialized(context)

        makeSupportActionBarVisible(false, "Beer of the month")

        val fragment = ReactNativeFragment.newInstance(ReactConfiguration("Home"))
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout, fragment)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                makeSupportActionBarVisible(false, resources.getString(R.string.beers_list))
                supportFragmentManager.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                makeSupportActionBarVisible(false, resources.getString(R.string.beer_of_the_month))

                val fragment = ReactNativeFragment.newInstance(ReactConfiguration("Home"))
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                makeSupportActionBarVisible(false, resources.getString(R.string.beers_list))

                val fragment = ReactNativeFragment.newInstance(ReactConfiguration("List"))
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit()

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onEvent(name: String?, info: ReadableMap?) {
        makeSupportActionBarVisible(true, resources.getString(R.string.beer_detail))

        val fragment = ReactNativeFragment.newInstance(ReactConfiguration("Detail"))
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout, fragment)
            .addToBackStack("PUSH")
            .commit()
    }

    override fun onEventCallback(name: String?, info: ReadableMap?, callback: MSREventBridgeReceiverCallback?) {
        // empty implementation
    }

    private fun makeSupportActionBarVisible(visible: Boolean, title: String) {
        supportActionBar?.setHomeButtonEnabled(visible)
        supportActionBar?.setDisplayHomeAsUpEnabled(visible)
        supportActionBar?.title = title
    }
}
