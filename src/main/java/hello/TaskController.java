package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        if(task!=null){
            taskRepository.save(task);
        }
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteTasks", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Task> addTask(@RequestBody List<Task> tasksToDelete) {
        if (tasksToDelete != null) {
            taskRepository.delete(tasksToDelete);
        }
        return new ResponseEntity<Task>(HttpStatus.OK);
    }

    @RequestMapping(value = "/editTasks", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Task> editTask(@RequestBody Task[] tasksToEdit) {
        if (tasksToEdit != null) {

        }
        return new ResponseEntity<Task>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getTasks", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> list = new ArrayList<>();
        for(Task task:taskRepository.findAll()){
            list.add(task);
        }
        return new ResponseEntity<List<Task>>(list, HttpStatus.OK);
    }
}