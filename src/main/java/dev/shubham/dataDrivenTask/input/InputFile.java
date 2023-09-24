package dev.shubham.dataDrivenTask.input;

import java.util.ArrayList;
import java.util.List;

public class InputFile {

    private final List<TestCase> testCases;

    public InputFile() {
        this.testCases = new ArrayList<>();
    }

    public void addTestCase(TestCase testCase){
        this.testCases.add(testCase);
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }
}
