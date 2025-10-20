package TODO_LIST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
   static Scanner scan = new Scanner(System.in);
   static ArrayList<Task> tasks = new ArrayList<>();


    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("==== Welcome TODO-LIST ===");
            menu();
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTask();
                case 3 -> markTaskDone();
                case 4 -> deleteTask();
                case 5 -> searchTask();
                case 6 -> System.out.println("Exiting... The App...");
                default -> System.out.println("Invalid input. Please try again!");
            }
        } while (choice != 6);

    }

    // menu
    public static void menu(){
        System.out.println("Menu: ");
        System.out.println("1. Add Task");
        System.out.println("2. View Task");
        System.out.println("3. Marked Task Done");
        System.out.println("4. Delete Task");
        System.out.println("5. Search Task");
        System.out.println("6. Exit");
        System.out.println("Please enter your choice: ");

    }

    // ADD TASK
    public static void addTask(){
        System.out.println("Enter description: ");
        String desc = scan.nextLine();
        tasks.add(new Task(desc)); // need talaga mag new object para ma add sya
        System.out.println("Task added");
    }

    //view task
    public static  void viewTask(){
        if(tasks.isEmpty()){
            System.out.println("No Task Available");
            return;
        }
        System.out.println("Your Task: ");
        int count = 1;
        for (Task t : tasks){
            System.out.println(count + "." + t);
            count++;
        }



    }

    //Marked task done
    public static void markTaskDone(){
        viewTask();
        if(tasks.isEmpty()) return;

        //checking if invalid number
        System.out.println("Enter Task number to mark done: ");
        int taskNumber = scan.nextInt();

        if(taskNumber < 1 || taskNumber > tasks.size() ){
            System.out.println("Invalid Task number");
            return;
        }

        //get the task list then marked done
        tasks.get(taskNumber - 1).markDone();
        System.out.println("Task marked done");
    }

     // delete task
    public static void deleteTask(){
        viewTask();

        if(tasks.isEmpty()) return;

        System.out.println("Enter task number to remove: ");
        int remove = scan.nextInt();
        if(remove < 1 || remove > tasks.size()){
            System.out.println("Invalid task number");
            return;
        }

        // get the task then removed
        tasks.remove(remove - 1);
        System.out.println("Task deleted successfully!");
    }
    //search the task

    public static void searchTask(){
        System.out.println("Search task: ");
        String searchTask = scan.nextLine().toLowerCase();

        boolean found = false; //bakit false kasi wala pa naman
        System.out.println("Search result: " );
       for(Task task: tasks){
           if(task.description.toLowerCase().contains(searchTask)){
               System.out.println(task);
               found =  true; //pag may nakita markahan agad
           }



       }
        if(!found) {
            System.out.println("no task found");
        }
    }

}
