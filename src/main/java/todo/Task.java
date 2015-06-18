package todo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by HomePC on 17.06.2015.
 */
public class Task {
    private int number;
    private String task;

    public Task() {

    }

    public Task(int id, String value) {
        this.number = id;
        this.task = value;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task1 = (Task) o;

        if (number != task1.number) return false;
        return !(task != null ? !task.equals(task1.task) : task1.task != null);

    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (task != null ? task.hashCode() : 0);
        return result;
    }
}
