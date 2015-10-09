package com.{{company_name}}.android.{{app_package_name_prefix}}.activity

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.MenuItem
import com.{{company_name}}.android.{{app_package_name_prefix}}.{{app_class_prefix}}AppImpl
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

public abstract class BaseActivity : RxActivity() {

    private lateinit var app: {{app_class_prefix}}AppImpl

    /**
     * @return the layout resource to use for this activity,
     * * or a value <= 0 if no layout should be used
     */
    @LayoutRes protected abstract fun getLayoutResource(): Int

    override protected fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = getApplicationContext() as {{app_class_prefix}}AppImpl

        val layoutResId = getLayoutResource()
        if (layoutResId > 0) {
            setContentView(layoutResId)
        }
    }

    protected fun getApp() : {{app_class_prefix}}AppImpl {
        return app
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true;
        } else {
            return super.onOptionsItemSelected(item)
        }
    }
}

