package TODO_LIST_APP;

import TODO_LIST_APP.model.Task;
import TODO_LIST_APP.service.TaskService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TaskService taskService = new TaskService();
        int choice;

        do {
            System.out.println("\nTODO LIST MENU");
            System.out.println("1. Add Task");
            System.out.println("2. View All TASK");
            System.out.println("3. Edit Task");
            System.out.println("4. Mark Task as Done");
            System.out.println("5. Delete Task");
            System.out.println("6. Exit");
            System.out.println("Choose an option: ");
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice){
                case 1:
                System.out.println("Enter task title: ");
                String title = scan.nextLine();
                System.out.println("Enter description: ");
                String desc = scan.nextLine();
                taskService.addTask(title,desc);
                break;

                case 2:
                    taskService.showAllTasks();
                    break;

                case 3:
                    System.out.println("Enter Task ID to edit: ");
                    int editID = scan.nextInt();
                    scan.nextLine();

                    Task found = taskService.findTaskById(editID);
                    if(found != null) {
                        System.out.println("Task found " + found);
                        System.out.println("New Title: ");
                        String newTitle = scan.nextLine();
                        System.out.println("New Description: ");
                        String newDesc = scan.nextLine();
                        taskService.editTask(editID, newTitle, newDesc);
                    }else System.out.println("Invalid Task ID!");
                    break;

                case 4:
                    System.out.println("Enter task ID to mark as done: ");
                    int doneId = scan.nextInt();
                    scan.nextLine();
                    taskService.markAsDone(doneId);
                    break;

                case 5:
                    System.out.println("Enter task ID to delete: ");
                    int deleteId = scan.nextInt();
                    scan.nextLine();
                    taskService.deleteTask(deleteId);
                    break;
                case 6:
                    System.out.println("Exiting the App.");
                    System.exit(0);
                default:
                    System.out.println("Invalid input!");

            }

        }while (choice != 6);
        scan.close();
    }

}
