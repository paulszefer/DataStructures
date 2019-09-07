package io.github.paulszefer;

/**
 * A container for static assertion methods.
 *
 * Each assertion method throws a {@link io.github.paulszefer.TestException TestException} if
 * it's logical assertion fails.
 */
public class TestAsserts {

    // Prevent instantiation
    private TestAsserts() {}

    /**
     * @throws TestException if the actual value is not equal to the expected value
     *
     * @param expected the expected value
     * @param actual the actual value
     */
    public static void assertEqual(Object expected, Object actual) throws TestException {
        if (!expected.equals(actual)) {
            throw new TestException(String.format("Expected: %s, Actual: %s", expected, actual));
        }
    }

    /**
     * @throws TestException if the expected exception is not thrown
     *
     * @param expected the class of the expected exception
     * @param executable the executable code
     */
    public static void assertThrows(Class expected, Executable executable) throws TestException, Exception {
        try {
            executable.execute();
            throw new TestException(String.format("Expected: %s, Actual: %s", expected.getName(), "No exception"));
        } catch (Exception e) {
            if (!expected.isInstance(e)) {
                throw new TestException(String.format("Expected: %s, Actual: %s", expected, e.getMessage()));
            }
        }
    }
}