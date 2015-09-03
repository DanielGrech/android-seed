package com.{{company_name}}.android.{{app_package_name_prefix}}.util

import android.view.View
import android.widget.TextView
import com.{{company_name}}.android.{{app_package_name_prefix}}.{{app_class_prefix}}TestRunner
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.robolectric.RuntimeEnvironment
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

RunWith({{app_class_prefix}}TestRunner::class)
public class ViewUtilsTest {

    Test
    public fun testHide() {
        val view = View(RuntimeEnvironment.application)
        view.setVisibility(View.VISIBLE)

        view.hide()

        assertThat(view.getVisibility()).isEqualTo(View.GONE)
    }

    Test
    public fun testHideInvisibleHandlesOneViewInput() {
        val view = View(RuntimeEnvironment.application)
        view.setVisibility(View.VISIBLE)

        view.hideInvisible()

        assertThat(view.getVisibility()).isEqualTo(View.INVISIBLE)
    }


    Test
    public fun testShowHandlesOneViewInput() {
        val view = View(RuntimeEnvironment.application)
        view.setVisibility(View.GONE)

        view.show()

        assertThat(view.getVisibility()).isEqualTo(View.VISIBLE)
    }

    Test
    public fun testShowWhenTrue() {
        val view = View(RuntimeEnvironment.application)
        view.setVisibility(View.GONE)

        view.showWhen(true)

        assertThat(view.getVisibility()).isEqualTo(View.VISIBLE)
    }

    Test
    public fun testShowWhenFalse() {
        val view = View(RuntimeEnvironment.application)
        view.setVisibility(View.VISIBLE)

        view.showWhen(false)

        assertThat(view.getVisibility()).isEqualTo(View.GONE)
    }

    Test
    public fun testSetTextOrHideWithEmptyText() {
        val view = mock(javaClass<TextView>())

        view.setTextOrHide("")

        verify(view).setVisibility(View.GONE)
    }

    Test
    public fun testSetTextOrHide() {
        val EXPECTED_TEXT = "I'll be back"
        val view = mock(javaClass<TextView>())

        view.setTextOrHide(EXPECTED_TEXT)

        verify(view).setVisibility(View.VISIBLE)
        verify(view).setText(EXPECTED_TEXT)
    }
}