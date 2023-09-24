package dev.shubham.dataDrivenTask.observers;


import dev.shubham.dataDrivenTask.observable.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Task class represents the task engine which gets {@link Task#trigger()} when any of task input data is changed.
 * On trigger each task refers to its output {@link Task#output} data and call  {@link Data#updateData()}.
 * Delegated the responsibility of calling trigger to {@link Data} class which act as observable. Task class as observer
 */
public class Task {

    private final Integer id;
    private final List<Data> output = new ArrayList<>();

    public Task(Integer id) {
        this.id = id;
    }

    public Integer id() {
        return id;
    }

    public void addOutput(Data data){
        this.output.add(data);
    }

    public void trigger() {
        for (Data data : this.output) {
            data.updateData();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
