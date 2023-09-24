package dev.shubham.dataDrivenTask.service;

import dev.shubham.dataDrivenTask.input.RawTaskReq;
import dev.shubham.dataDrivenTask.observable.Data;
import dev.shubham.dataDrivenTask.observers.Task;

import java.util.*;

public class DataProcessor {

    private static final DataProcessor INSTANCE = new DataProcessor();

    private DataProcessor(){
    }

    public static DataProcessor getInstance(){
        return INSTANCE;
    }

    public DataProcessResponse process(String testCaseNum, List<RawTaskReq> rawTaskRequest, List<Integer> dataChanges){
        // using queue to preserve the order in which tasks are executed as per data change
        // even though in output file as per req. you will see the task order are sorted order
        Queue<Task> taskQueue = new LinkedList<>();
        // Using visited to check if task execution creates a cycle of task execution
        // which might result in stackoverflow. In input.txt test case number 31 creates a cycle
        Set<Task> visited = new HashSet<>();
        // map stores data object by its id, while parsing data change list it identifies the relevant data
        // and call trigger task accordingly
        Map<Integer, Data> datumMapById = new HashMap<>();
        for (int i = 0; i < rawTaskRequest.size(); i++) {
            RawTaskReq rawTaskReq = rawTaskRequest.get(i);

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
        for (Integer dataId : dataChanges) {
            Data dataChange = datumMapById.get(dataId);
            dataChange.updateData();
        }
        List<Integer> tasksProcessed = new ArrayList<>();
        while (!taskQueue.isEmpty()){
            tasksProcessed.add(taskQueue.poll().id());
        }
        tasksProcessed.sort(Integer::compareTo);
        return new DataProcessResponse(testCaseNum,tasksProcessed);
    }
}
