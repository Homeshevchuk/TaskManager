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
}
