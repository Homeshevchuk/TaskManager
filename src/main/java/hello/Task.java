package hello;

import javax.persistence.*;


/**
 * Created by HomePC on 17.06.2015.
 */
@Entity
public class Task {
    @Id
    @GeneratedValue
    private long number;
    private String task;

    public Task() {

    }

    public Task(int id, String value) {
        this.number = id;
        this.task = value;
    }

    public long getNumber() {
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
        int result = (int) (number ^ (number >>> 32));
        result = 31 * result + (task != null ? task.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "number=" + number +
                ", task='" + task + '\'' +
                '}';
    }
}
