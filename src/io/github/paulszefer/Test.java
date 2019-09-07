package io.github.paulszefer;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * A test class containing test methods.
 */
public abstract class Test {

    /** The required prefix for a method to be considered a valid test */
    private static final String TEST_METHOD_PREFIX = "test_";

    // Prevent instantiation
    private Test() {}

    /**
     * Runs all test methods for this class.
     *
     * @param log the log to print pass or fail messages to
     */
    public void runTests(TestLog log) {
        for (Method method : getClass().getDeclaredMethods()) {
            if (isTestMethod(method)) {
                runTest(method, log);
            }
        }
    }

    /**
     * @return true if the given method is a test method (begins with "test_")
     *
     * @param method the method to check
     */
    private boolean isTestMethod(Method method) {
        return method.getName().substring(0, 5).equals(TEST_METHOD_PREFIX);
    }

    /** Sets up state. This is run prior to each test. */
    protected void setUp() {}

    /** Cleans up state. This is run after each test. */
    protected void tearDown() {}

    /**
     * Runs the given test method and prints pass or fail messages to the given log.
     *
     * @param method the test method to run
     * @param log the log to print pass or fail messages to
     */
    private void runTest(Method method, TestLog log) {
        setUp();
        try {
            method.invoke(this, (Object[])null);
            log.pass(method.getName());
        } catch (InvocationTargetException e) {
            log.fail(method.getName(), e.getTargetException().getMessage());
        } catch (IllegalAccessException e) {
            log.fail(method.getName(), e.getMessage());
        }
    }
}