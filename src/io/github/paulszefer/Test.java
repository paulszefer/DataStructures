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
    /** Initializes a test. */
    public Test() {
    }

    /**
     * Runs all test methods for this class.
     *
     * @param log the log to print pass or fail messages to
     */
    public void runTests(TestLog log) {
        Method[] methods = getClass().getDeclaredMethods();
        Arrays.sort(methods, new MethodComparator());
        for (Method method : methods) {
            if (isTestMethod(method)) {
                runTest(method, log);
            }
        }
    /**
     * Compares the names of given methods.
     */
    private class MethodComparator implements Comparator<Method>
    {
        /**
         * Compares the names of the given methods.
         *
         * @param a the first method
         * @param b the second method
         *
         * @returns a negative number if a comes before b, a postive number if b comes before a,
         *   or 0 if they are equal
         */
        public int compare(Method a, Method b)
        {
            return a.getName().compareTo(b.getName());
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
            Throwable targetexception = e.getTargetException();
            if (TestException.class.isInstance(targetexception)) {
                log.fail(method.getName(), targetexception.getMessage());
                passing = false;
            } else {
                log.fail(method.getName(), targetexception.toString());
            }
        } catch (IllegalAccessException e) {
            log.fail(method.getName(), e.getMessage());
        }
    }
}