package dev.shubham.dataDrivenTask.input;

import java.util.List;

public class RawTaskReq {

    private final Integer taskId;
    private final List<Integer> input;
    private final List<Integer> output;

    public RawTaskReq(Integer taskId, List<Integer> input, List<Integer> output) {
        this.taskId = taskId;
        this.input = input;
        this.output = output;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public List<Integer> getInput() {
        return input;
    }

    public List<Integer> getOutput() {
        return output;
    }
}
