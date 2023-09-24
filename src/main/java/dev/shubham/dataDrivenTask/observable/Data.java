package dev.shubham.dataDrivenTask.observable;


import dev.shubham.dataDrivenTask.observers.Task;

import java.util.Comparator;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents the data required to run Task also data
 */
public class Data {

    private Integer id;
    private final TreeSet<Task> observers = new TreeSet<>(Comparator.comparing(Task::id));
    private Queue<Task> taskQueue;
    private Set<Task> visited;

    public Data(Integer id, Queue<Task> taskQueue,Set<Task> visited){
        this.id = id;
        this.taskQueue = taskQueue;
        this.visited = visited;
    }

    public Integer id() {
        return id;
    }

    public void registerTask(Task observer) {
        this.observers.add(observer);
    }

    public void notifyObservers() {
        for (Task observer : this.observers) {
            // do not queue same task again. it might create a cycle of tasks
            if(visited.contains(observer)){
                continue;
            }
            visited.add(observer);
            this.taskQueue.offer(observer);
            observer.trigger();
        }
    }

    public void updateData() {
        notifyObservers();
    }
}
