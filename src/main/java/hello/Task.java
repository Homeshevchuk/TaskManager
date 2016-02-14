package hello;

import javax.persistence.*;


/**
 * Created by HomePC on 17.06.2015.
 */
@Entity
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name="task")
    private String task;
    public Task() {

    }

    public Task(int id, String value) {
        this.id = id;
        this.task = value;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task1 = (Task) o;

        if (getId() != task1.getId()) return false;
        return getTask() != null ? getTask().equals(task1.getTask()) : task1.getTask() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getTask() != null ? getTask().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", task='" + task + '\'' +
                '}';
    }
}
