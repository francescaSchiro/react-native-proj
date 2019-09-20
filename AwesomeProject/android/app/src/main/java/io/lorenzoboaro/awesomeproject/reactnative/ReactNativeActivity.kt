package it.lorenzoboaro.awesomeproject.reactnative

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import io.lorenzoboaro.awesomeproject.R
import io.lorenzoboaro.awesomeproject.reactnative.ReactAwareActivity
import it.lorenzoboaro.awesomeproject.reactnative.ReactNativeIntents.EXTRA_ACTIVITY_NAME
import it.lorenzoboaro.awesomeproject.reactnative.ReactNativeIntents.EXTRA_MODULE_NAME

class ReactNativeActivity : ReactAwareActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_react_native)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra(EXTRA_ACTIVITY_NAME)) {
            supportActionBar?.title = intent.getStringExtra(EXTRA_ACTIVITY_NAME)
        }

        val moduleName = intent.getStringExtra(EXTRA_MODULE_NAME)
        ReactNativeFragment.newInstance(moduleName, null).let {
            supportFragmentManager.beginTransaction()
                .add(R.id.content, it)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private val TAG = ReactNativeActivity::class.java.simpleName

        fun newInstance(context: Context, activityName: String, moduleName: String): Intent {
            return Intent(context, ReactNativeActivity::class.java)
                .putExtra(EXTRA_MODULE_NAME, moduleName)
                .putExtra(EXTRA_ACTIVITY_NAME, activityName)
        }
    }
}
