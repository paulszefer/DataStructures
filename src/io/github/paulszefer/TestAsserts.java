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
}