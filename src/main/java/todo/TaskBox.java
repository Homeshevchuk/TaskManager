package todo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HomePC on 17.06.2015.
 */
public class TaskBox {
    private static TaskBox instance;
    private List<Task> tasks;

    private TaskBox() {
        tasks = new ArrayList<>();
        tasks.add(new Task(1, "Покушать"));

    }

    public static TaskBox getInstance() {
        if (instance == null) {
            instance = new TaskBox();
        }
        return instance;
    }


    public void addTask(Task task) {
        task.setNumber(this.tasks.get(this.tasks.size()-1).getNumber() + 1);
        this.tasks.add(task);
    }
    public void deleteTasks(Task[] tasksToDelete){
        for(Task taskToDelete:tasksToDelete){
            for(Task task:tasks){
                if(task.equals(taskToDelete)){
                    tasks.remove(task);
                    break;
                }
            }
        }
    }

    public List<Task> getAll() {
        return tasks;
    }

    public void editTasks(Task[] tasksToEdit) {
        for(Task taskToEdit :tasksToEdit){
            for(Task task:tasks){
                if(task.getNumber()==taskToEdit.getNumber()){
                    task.setTask(taskToEdit.getTask());
                    break;
                }
            }
        }
    }
}
