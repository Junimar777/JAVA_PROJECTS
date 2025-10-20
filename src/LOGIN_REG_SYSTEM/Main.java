package LOGIN_REG_SYSTEM;

import LOGIN_REG_SYSTEM.service.UserService;

import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static int choice;

    public static void main(String[] args) {

        UserService userService = new UserService();

    do {
        menu();
        int choice = scan.nextInt();
        scan.nextLine();

        switch (choice){
                //register
            case 1:
                System.out.println("Enter username: ");
                String newUser = scan.nextLine();
                System.out.println("Enter password: ");
                String newPassword = scan.nextLine();

                if(userService.register(newUser,newPassword)){
                    System.out.println("Register successfully!");
                }else System.out.println("Error.. username already taken!");

                break;

                //login
            case 2:
                //bawal mag double login. kung may naka login pa kuhanin natin ung getUsername
                if(userService.isLoggedIn()){
                    System.out.println("Already logged-in " + userService.getCurrentUserName());
                    break;
                }

                System.out.println("user: ");
                String user = scan.nextLine();
                System.out.println("password: ");
                String password = scan.nextLine();

                if(userService.login(user,password)){
                    System.out.println("Login successfully!");
                }else System.out.println("Invalid user or password!");
                break;

                //logout
            case 3:
                if(userService.isLoggedIn()){
                    userService.loggedOut();
                    System.out.println("Logout succesfully");
                }else System.out.println("No user currently log-in");

                break;
            case 4:
                System.out.println("Exiting the Application.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice!");
        }

    }while (choice !=4);
    scan.close();
    }


    // menu
    public static void menu(){
        System.out.println("===========LOGIN_REGISTER SYSTEM================");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Logout");
        System.out.println("4. Exit");
        System.out.println("Enter your choice: ");
    }
}
