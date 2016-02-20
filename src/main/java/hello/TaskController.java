package hello;

import java.awt.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hello.Account.Account;
import hello.Account.AccountRepository;
import hello.Account.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TaskController {

    @Autowired
    private AccountRepository userRepository;
    @RequestMapping(value = "/Task/addTask", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Task> addTask(@RequestBody Task task, Principal principal) {
        Account account = userRepository.findByUsername(principal.getName());
        if (task != null) {
            if(account.getTaskList()==null){
                account.setTaskList(new ArrayList<>());
            }
            account.getTaskList().add(task);
            userRepository.save(account);
        }

        return new ResponseEntity(account.getTaskList().get(account.getTaskList().size()-1), HttpStatus.OK);
    }

    @RequestMapping(value = "/Task/deleteTask", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Task> deleteTask(@RequestBody Task taskToDelete,@RequestBody long id, Principal principal) {
        if (taskToDelete != null) {
           Account account = userRepository.findOne(id);
           account.getTaskList().remove(taskToDelete);
           userRepository.save(account);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/Task/editTask", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Task> editTask(@RequestBody Task taskToEdit, Principal principal) {
        if (taskToEdit != null) {
            Account account = userRepository.findByUsername(principal.getName());
            for(Task task :account.getTaskList()){
                if(task.getId()==taskToEdit.getId()){
                    userRepository.UpdateTask(principal.getName(),taskToEdit.getId(),taskToEdit.getTask());
                }
            }

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/Task/getTasks", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Task>> getTasks(Principal principal) {
        List<Task> list = userRepository.findByUsername(principal.getName()).getTaskList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
