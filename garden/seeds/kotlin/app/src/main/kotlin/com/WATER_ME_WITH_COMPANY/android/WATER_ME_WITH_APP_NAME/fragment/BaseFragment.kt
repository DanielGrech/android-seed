package com.{{company_name}}.android.{{app_package_name_prefix}}.fragment

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.{{company_name}}.android.{{app_package_name_prefix}}.{{app_class_prefix}}AppImpl

/**
 * Common functionality for all fragments in the app
 */
public abstract class BaseFragment : RxFragment() {

    /**
     * @return id of the layout to use in this fragment
     */
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    private var app: {{app_class_prefix}}AppImpl? = null

    override public fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = getActivity().getApplicationContext() as {{app_class_prefix}}AppImpl
    }

    /**
     * {@inheritDoc}
     */
    override public fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        @LayoutRes val layoutId = getLayoutId()
        if (layoutId > 0) {
            val view = inflater.inflate(layoutId, container, false)
            onCreateView(view, savedInstanceState)
            return view
        } else {
            return null
        }
    }

    @SuppressWarnings("UNUSED_PARAMETER")
    protected fun onCreateView(rootView: View, savedInstanceState: Bundle?) {
        // Hook for subclasses to override
    }

    protected fun getApp() : {{app_class_prefix}}AppImpl {
        return app!!
    }

    override public fun onDestroy() {
        super.onDestroy()
        app?.refWatcher?.watch(this, javaClass.getSimpleName())
    }
}