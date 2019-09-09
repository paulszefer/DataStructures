package io.github.paulszefer;

public class TestFramework {

    private static Test[] tests;

    public static void main(String[] args) {
        tests = new Test[] {
            new ListTest_Integer(),
            new ListTest_String()
        };
        run();
    }

    public static void run() {
        TestLog log = new TestLog();
        for (Test test : tests) {
            test.runTests(log);
        }
    }
}