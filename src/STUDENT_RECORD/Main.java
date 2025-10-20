package STUDENT_RECORD;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   static ArrayList<Student> students = new ArrayList<>();
   static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            menu();
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice){
             case 1 -> addStudent();
             case 2 -> viewStudents();
             case 3 -> searchByID();
             case 4 -> updateGrades();
             case 5 -> deleteStudentByID();
             case 6 -> System.out.println("Exiting the Student Grade.");
                default -> System.out.println("Invalid input. Please try again!");
            }
        }while (choice != 6);



    }


    // gawa tau ng menu
    public  static void menu(){
        System.out.println("=== Student menu ====");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Grades");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.println("Enter choice: ");
    }

    //add student
    public static void addStudent(){
        System.out.println("Enter Student id: ");
        String id = scan.nextLine();
        System.out.println("Enter Student name: ");
        String name = scan.nextLine();
        System.out.println("Enter Student prelim: ");
        double prelim = scan.nextDouble();
        System.out.println("Enter Student midterm: ");
        double midterm = scan.nextDouble();
        System.out.println("Enter Student finals: ");
        double finals = scan.nextDouble();

        students.add(new Student(id,name,prelim,midterm,finals));
        System.out.println("Student Grade Added Successfully.");
    }

    // view all students
    public static void viewStudents(){
        if (students.isEmpty()){
            System.out.println("no Students record");
            return;
        }
        System.out.println("=== Students Record ===");
        for (Student student: students){
            System.out.println(student);
        }

    }

    // search student by ID only
    public static  void searchByID(){
        if (students.isEmpty()) {
            System.out.println("No records to search");
            return;

        }
        System.out.println("Enter Student ID: ");
        String id = scan.nextLine().toLowerCase();
        boolean found= false;

       for (Student student: students){
            if (student.id.contains(id)){
                System.out.println("Found Student: " + student);
                found=true;

            }
       }

       if (!found){
           System.out.println("No Student id found");
       }
    }

    // mag update tau ng grades
    public static void updateGrades(){
        System.out.println("Enter Student id: ");
        String id = scan.nextLine();
        boolean found = false;

        // mag loop tau para makuha natin ung student id
        for(Student student:students){
            if(student.id.equalsIgnoreCase(id)){
                System.out.println("Enter new prelim grade: ");
                double prelim =scan.nextDouble();
                System.out.println("Enter new midterm grade: ");
                double midterm = scan.nextDouble();
                System.out.println("Enter new finals grade: ");
                double finals = scan.nextDouble();

                student.UpdateGrades(prelim,midterm,finals);
                System.out.println("Grades updated successfully!");
                found = true;
                break;//lalabas lang sa loop
            }
        }
        if(!found){
            System.out.println("No student id found.");
        }
    }

    // delete Student by id
    public static  void deleteStudentByID(){
        if (students.isEmpty()){
            System.out.println("no records to delete");
            return;
        }
        viewStudents();
        System.out.println("Enter Student id to delete: ");
        String id = scan.nextLine();
        //dito no need na sya e loop pag removeif
        boolean removed = students.removeIf(student -> student.id.equalsIgnoreCase(id));


        if (removed){
            System.out.println("Student record deleted successfully");
        }else System.out.println("Student id not found.");

    }

}
