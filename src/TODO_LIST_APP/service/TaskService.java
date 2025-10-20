package TODO_LIST_APP.service;

import TODO_LIST_APP.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private List<Task> tasks = new ArrayList<>();// eto ay storage
    private int nextID = 1; //auto increment



    //add new task
    public void addTask(String title, String description){
        Task task = new Task(nextID++, title, description);
        tasks.add(task);
        System.out.println(" Task added successfully");
    }

    //show all tasks
    public void showAllTasks(){
        //checking if task is empty
        if(tasks.isEmpty()){
            System.out.println("no task found!");
            return;
        }

        System.out.println("**** Task LIST ****");
       for(Task task: tasks){
           System.out.println(task);
       }
    }

    // Edit existing task
    public void editTask(int id, String newTitle, String newDesc){
        for(Task task:tasks){
            if(task.getId() == id){
                task.setTitle(newTitle);
                task.setDescription(newDesc);
                System.out.println("Task updated successfully!");
                return;
            }
        }
        System.out.println("Task not found!");
    }

    public void markAsDone(int id){
        for (Task task: tasks){
            if(task.getId() == id){
                task.setCompleted(true);
                System.out.println("Task marked as done!");
                return;
            }
        }
        System.out.println("Task not found");
    }




    //Delete task
    public void deleteTask(int id){
        Task toRemove = null; // so walang laman
        for(Task task: tasks){
            if(task.getId() == id){
                toRemove = task;
                break;

            }
        }

        if(toRemove != null){
            tasks.remove(toRemove);
            System.out.println("Task deleted");
        }else {
            System.out.println("Task not found");
        }
    }

    //findbyID
    public Task findTaskById(int id){
        for (Task task:tasks){
            if(task.getId() == id){
                return  task;
            }
        }
        return null;//walang mahanap
    }

}
