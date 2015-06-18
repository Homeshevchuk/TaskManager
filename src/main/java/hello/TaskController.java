package hello;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo.Task;
import todo.TaskBox;

@RestController
public class TaskController {

    @RequestMapping(value = "/addTask",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        if(task!=null){
            TaskBox.getInstance().addTask(task);
        }
        return new ResponseEntity<Task>(task,HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteTasks",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Task> addTask(@RequestBody Task[] tasksToDelete) {
        if(tasksToDelete!=null){
            TaskBox.getInstance().deleteTasks(tasksToDelete);
        }
        return new ResponseEntity<Task>(HttpStatus.OK);
    }
    @RequestMapping(value = "/editTasks",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Task> editTask(@RequestBody Task[] tasksToEdit) {
        if(tasksToEdit!=null){
            TaskBox.getInstance().editTasks(tasksToEdit);
        }
        return new ResponseEntity<Task>(HttpStatus.OK);
    }
    @RequestMapping(value = "/getTasks",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Task>> getTasks() {
        return new ResponseEntity<List<Task>>(TaskBox.getInstance().getAll(),HttpStatus.OK);
    }
}