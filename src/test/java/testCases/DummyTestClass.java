package testCases;

import org.testng.TestNG;

public class DummyTestClass {
    public static void main(String[] args) {
        TestNG tng = new TestNG();
        tng.setTestClasses(new Class[]{TestBase.class, TB.class});
        tng.run();
    }
}
