package com.{{company_name}}.android.{{app_package_name_prefix}}.activity

import android.os.Bundle
import com.{{company_name}}.android.{{app_package_name_prefix}}.{{app_class_prefix}}TestRunner
import com.{{company_name}}.android.{{app_package_name_prefix}}.TestUtils
import com.{{company_name}}.android.{{app_package_name_prefix}}.module.AppServicesComponent
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.presenter.MainPresenter
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.presenter.Presenter
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.view.MainMvpView
import com.{{company_name}}.android.{{app_package_name_prefix}}.mvp.view.MvpView
import com.{{company_name}}.{{app_package_name_prefix}}.DataSource
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.any
import org.mockito.Mockito.RETURNS_DEEP_STUBS
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.robolectric.Robolectric
import org.robolectric.Robolectric.buildActivity

RunWith({{app_class_prefix}}TestRunner::class)
public class PresentableActivityTest {

    Test
    public fun testDelegatesToPresenter() {
        val controller = buildActivity(javaClass<PresentableActivityWithMockPresenter>())

        val presenter = controller.setup().get().presenter
        controller.saveInstanceState(mock(javaClass<Bundle>())).pause().stop().destroy().get()

        verify(presenter).onCreate(any(javaClass<Bundle>()))
        verify(presenter).onStart()
        verify(presenter).onResume()
        verify(presenter).onSaveInstanceState(any(javaClass<Bundle>()))
        verify(presenter).onPause()
        verify(presenter).onStop()
        verify(presenter).onDestroy()
    }

    open class PresentableActivityWithMockPresenter : PresentableActivity<DummyMvpView, DummyPresenter>() {
        protected override fun createPresenter(component: AppServicesComponent): DummyPresenter {
            return spy(DummyPresenter(TestUtils.createView(javaClass<DummyMvpView>()), component))
        }

        protected override fun getLayoutResource(): Int {
            return 0
        }
    }

    interface DummyMvpView : MvpView {

    }

    open class DummyPresenter(view: DummyMvpView, component : AppServicesComponent) : Presenter<DummyMvpView>(view, component) {

    }
}