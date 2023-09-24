package dev.shubham.dataDrivenTask.service;

import java.util.List;
import java.util.StringJoiner;

public class DataProcessResponse {

    private final String testCaseNum;
    private final List<Integer> tasks;


    public DataProcessResponse(String testCaseNum, List<Integer> task) {
        this.testCaseNum = testCaseNum;
        this.tasks = task;
    }

    public String getTestCaseNum() {
        return testCaseNum;
    }

    public List<Integer> getTasks() {
        return tasks;
    }

    public String display() {
        StringJoiner joiner = new StringJoiner(",");
        for (Integer task : this.tasks) {
            joiner.add(String.valueOf(task));
        }
        return testCaseNum + joiner;
    }
}
