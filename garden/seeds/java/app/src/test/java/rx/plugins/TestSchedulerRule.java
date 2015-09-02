package rx;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.TestScheduler;

/**
 * Rule which provides a {@link TestScheduler} that can manipulate tests
 */
public class TestSchedulerRule implements TestRule {

    private static final TestScheduler TEST_SCHEDULER = new TestScheduler();

    @Override
    public Statement apply(final Statement base, Description description) {
        return base;
    }

    public TestScheduler getScheduler() {
        return TEST_SCHEDULER;
    }

    public static class RxSchedulersHook extends RxJavaSchedulersHook {

        @Override
        public Scheduler getComputationScheduler() {
            return TEST_SCHEDULER;
        }

        @Override
        public Scheduler getIOScheduler() {
            return TEST_SCHEDULER;
        }
    }
}
