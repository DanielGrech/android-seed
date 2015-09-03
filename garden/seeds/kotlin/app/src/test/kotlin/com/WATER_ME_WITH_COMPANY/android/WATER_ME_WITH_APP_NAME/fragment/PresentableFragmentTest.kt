package com.{{company_name}}.android.{{app_package_name_prefix}}.fragment


import android.os.Bundle
import com.{{company_name}}.android.{{app_package_name_prefix}}.{{app_class_prefix}}TestRunner
import com.{{company_name}}.android.{{app_package_name_prefix}}.TestUtils
import com.{{company_name}}.android.{{app_package_name_prefix}}.module.AppServicesComponent
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.presenter.Presenter
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.view.MvpView
import junit.framework.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.*
import org.junit.Test
import org.junit.Test
import org.junit.Test
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil

RunWith({{app_class_prefix}}TestRunner::class)
public class PresentableFragmentTest {

    Test
    public fun testDelegatesToPresenter() {
        val frag = DummyFragment()

        SupportFragmentTestUtil.startFragment(frag)

        val presenter = frag.presenter

        verify(presenter).onCreate(any(javaClass<Bundle>()))
        verify(presenter).onStart()
        verify(presenter).onResume()

        frag.onSaveInstanceState(mock(javaClass<Bundle>()))
        verify(presenter).onSaveInstanceState(any(javaClass<Bundle>()))

        frag.onPause()
        verify(presenter).onPause()

        frag.onStop()
        verify(presenter).onStop()

        frag.onDestroy()
        verify(presenter).onDestroy()
    }

    interface DummyMvpView : MvpView {

    }

    open class DummyPresenter(view: DummyMvpView, component : AppServicesComponent) : Presenter<DummyMvpView>(view, component) {

    }

    public class DummyFragment : PresentableFragment<DummyMvpView, DummyPresenter>() {

        override protected fun createPresenter(servicesComponent: AppServicesComponent, savedInstanceState: Bundle?): DummyPresenter {
            return spy(DummyPresenter(TestUtils.createView(javaClass<PresentableFragmentTest.DummyMvpView>()), servicesComponent))
        }

        override protected fun getLayoutId(): Int {
            return 0
        }
    }
}