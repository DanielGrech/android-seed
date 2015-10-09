package com.{{company_name}}.android.{{app_package_name_prefix}}.util

import com.{{company_name}}.android.{{app_package_name_prefix}}.{{app_class_prefix}}TestRunner
import com.{{company_name}}.android.{{app_package_name_prefix}}.R
import com.{{company_name}}.android.{{app_package_name_prefix}}.activity.MainActivity
import com.{{company_name}}.android.{{app_package_name_prefix}}.fragment.BaseFragment
import com.{{company_name}}.android.{{app_package_name_prefix}}.fragment.RxFragment
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil
import rx.Observable
import rx.observers.TestSubscriber
import rx.schedulers.TestScheduler
import java.util.*
import java.util.concurrent.TimeUnit


@RunWith({{app_class_prefix}}TestRunner::class)
public class RxUtilsTest {

    @Test
    public fun testNullFilterWithNullInput() {
        val testSubscriber = TestSubscriber<Any>()

        Observable.just<Any>(null).filterNulls().subscribe(testSubscriber)

        testSubscriber.assertNoValues()
        testSubscriber.assertTerminalEvent()
    }

    @Test
    public fun testNullFilterWithNonNullInput() {
        val EXPECTED_OUTPUT = Object()

        val testSubscriber = TestSubscriber<Any>()
        Observable.just<Any>(EXPECTED_OUTPUT).filterNulls().subscribe(testSubscriber)

        testSubscriber.assertValue(EXPECTED_OUTPUT)
        testSubscriber.assertTerminalEvent()
    }

    @Test
    public fun testFlatMapList() {
        val listInput = listOf(123, 456, 789)

        val testSubscriber = TestSubscriber<Int>()
        Observable.just(listInput).flatMapList().subscribe(testSubscriber)

        testSubscriber.assertValues(123, 456, 789)
    }

    @Test
    public fun testBindActivity() {
        val observable = Observable.just("").delay(2, TimeUnit.SECONDS)
        val subscriber = TestSubscriber<String>()

        val controller = Robolectric.buildActivity(javaClass<MainActivity>())

        controller.create()
        controller.resume()

        observable.bind(controller.get()).subscribe(subscriber)
        assertThat(subscriber.isUnsubscribed()).isFalse()

        controller.pause()
        assertThat(subscriber.isUnsubscribed()).isTrue()
    }

    @Test
    throws(InterruptedException::class)
    public fun testBindFragment() {
        val observable = Observable.just("").delay(2, TimeUnit.SECONDS)
        val subscriber = TestSubscriber<String>()

        val frag = DummyFragment()

        SupportFragmentTestUtil.startFragment(frag)

        frag.onResume()
        observable.bind(frag).subscribe(subscriber)
        assertThat(subscriber.isUnsubscribed()).isFalse()

        frag.onPause()
        assertThat(subscriber.isUnsubscribed()).isTrue()
    }

    private class DummyFragment : BaseFragment() {
        override fun getLayoutId(): Int {
            return 0
        }
    }
}
