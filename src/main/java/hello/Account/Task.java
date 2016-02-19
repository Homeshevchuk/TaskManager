package hello.Account;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;


/**
 * Created by HomePC on 17.06.2015.
 */
@NodeEntity
public class Task {
    private Long id;
    private String task;
    public Task() {
    }
    public Task(String value) {
        this.task = value;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (!(o instanceof Task)) return false;

        Task task1 = (Task) o;

        if (getId() != null ? !getId().equals(task1.getId()) : task1.getId() != null) return false;
        return getTask() != null ? getTask().equals(task1.getTask()) : task1.getTask() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTask() != null ? getTask().hashCode() : 0);
        return result;
    }

}
