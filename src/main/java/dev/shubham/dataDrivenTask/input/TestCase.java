package dev.shubham.dataDrivenTask.input;



import dev.shubham.dataDrivenTask.observable.Data;
import dev.shubham.dataDrivenTask.observers.Task;

import java.util.*;

public class TestCase {

    private final String testCaseNum;
    private final List<RawTaskReq> rawTaskReqs;
    private final List<Integer> dataChange;


    public TestCase(String testCaseNum, List<RawTaskReq> rawTaskReqs, List<Integer> dataChange) {
        this.testCaseNum = testCaseNum;
        this.rawTaskReqs = rawTaskReqs;
        this.dataChange = dataChange;
    }

    public String getTestCaseNum() {
        return testCaseNum;
    }

    public List<RawTaskReq> getRequests() {
        return rawTaskReqs;
    }

    public List<Integer> getDataChange() {
        return dataChange;
    }

    public List<Integer> init() {
        // using queue to preserve the order in which tasks are executed as per data change
        // even though in output file as per req. you will see the task order are sorted order
        Queue<Task> taskQueue = new LinkedList<>();
        // Using visited to check if task execution creates a cycle of task execution
        // which might result in stackoverflow. In input.txt test case number 31 creates a cycle
        Set<Task> visited = new HashSet<>();
        // map stores data object by its id, while parsing data change list it identifies the relevant data
        // and call trigger task accordingly
        Map<Integer,Data> datumMapById = new HashMap<>();
        for (int i = 0; i < rawTaskReqs.size(); i++) {
            RawTaskReq rawTaskReq = rawTaskReqs.get(i);

            Task task = new Task(i);
            for (Integer dataId : rawTaskReq.getInput()) {
                Data taskInput = datumMapById.getOrDefault(dataId,new Data(dataId, taskQueue, visited));
                taskInput.registerTask(task);
                datumMapById.putIfAbsent(dataId,taskInput);
            }
            for (Integer dataId : rawTaskReq.getOutput()) {
                Data taskOutput = datumMapById.getOrDefault(dataId,new Data(dataId, taskQueue, visited));
                task.addOutput(taskOutput);
                datumMapById.putIfAbsent(dataId,taskOutput);
            }
        }
        for (Integer dataId : this.dataChange) {
            Data dataChange = datumMapById.get(dataId);
            dataChange.updateData();
        }
        List<Integer> tasksProcessed = new LinkedList<>();
        while (!taskQueue.isEmpty()){
            tasksProcessed.add(taskQueue.poll().id());
        }
        return tasksProcessed;
    }
}
