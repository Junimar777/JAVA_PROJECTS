package CONTACT_BOOK;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
  static   Scanner scan = new Scanner(System.in);
  static   ArrayList<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        int choice;

        do {
            menu();
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice){
            case 1 -> addContact();
            case 2 -> viewContacts();
            case 3 -> searchContact();
            case 4 -> deleteContact();
            case 5 -> System.out.println("Exiting the Application...");
            default -> System.out.println("Invalid input. Please try again.");
         }
        }while (choice !=5);


    }


    //menu
    public static void menu(){
        System.out.println("===== MENU =====");
        System.out.println("1. Add Contact.");
        System.out.println("2. View Contacts.");
        System.out.println("3. Search Contact.");
        System.out.println("4. Delete Contact.");
        System.out.println("5. Exit ");
        System.out.println("Enter choice: ");
    }

    //add contact
    public static void addContact(){
        System.out.println("Enter name: ");
        String name = scan.nextLine();
        System.out.println("Enter phone: ");
        String phone = scan.nextLine();
        System.out.println("Enter email: ");
        String email = scan.nextLine();

        contacts.add(new Contact(name, phone,email));
        System.out.println("Successfully Added.");
    }

    // view contacts
    public static void viewContacts(){
        if(contacts.isEmpty()){
            System.out.println("no contacts available");
            return;
        }
        int count = 1;
        for(Contact c: contacts){
            System.out.println(count + ". " + c);
            count++;
        }
    }

    // search contact
    public static void searchContact(){
        if (contacts.isEmpty()) {
            System.out.println("no contacts available");
            return;
        }

        System.out.println("Enter name or number to search:");
        String search = scan.nextLine().toLowerCase();
        boolean found = false;
        for(Contact c: contacts){
            if (c.name.contains(search) || c.phone.contains(search)){
                System.out.println("found contact: " + c);
                found = true;
            }

        }

        if(!found){
            System.out.println("no matching contact found.");
        }
    }

    // delete contact
    public static void deleteContact(){
        viewContacts();
        if(contacts.isEmpty()) return;

        System.out.println("Enter number to delete: ");
        int toDelete = scan.nextInt();
        //checking the number
        if (toDelete < 1 || toDelete > contacts.size()){
            System.out.println("Invalid contact number!");
            return;
        }

        contacts.remove(toDelete - 1);
        System.out.println("Contact deleted successfully!");

    }

}
