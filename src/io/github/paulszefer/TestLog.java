package io.github.paulszefer;

import static io.github.paulszefer.TestAsserts.assertEqual;

/**
 * A log that deals with passing and failing tests.
 */
public class TestLog extends Log {

    /**
     * Prints a success message to the log.
     *
     * @param methodName the name of the method
     */
    public void pass(String methodName) {
        System.out.println(String.format("PASSED: %s", methodName));
    }

    /**
     * Prints a failure message to the log.
     *
     * @param methodName the name of the method
     * @param reason the reason for the failure
     */
    public void fail(String methodName, String reason) {
        System.out.println(String.format("FAILED: %s - %s", methodName, reason));
    }
}