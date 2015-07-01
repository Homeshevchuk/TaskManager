package hello;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by HomePC on 19.06.2015.
 */
public interface TaskRepository extends CrudRepository<Task,Long> {
}
