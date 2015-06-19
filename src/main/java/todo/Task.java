package todo;

import javax.persistence.*;


/**
 * Created by HomePC on 17.06.2015.
 */
@Entity
public class Task {
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private String task;

    public Task() {

    }

    public Task(int id, String value) {
        this.id = id;
        this.task = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        if (id != task1.id) return false;
        return !(task != null ? !task.equals(task1.task) : task1.task != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (task != null ? task.hashCode() : 0);
        return result;
    }
}
