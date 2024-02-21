package UI;

import Models.StudentsModels;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class StudentsUI {

    private Map<Integer, StudentsModels> studentsMap = new TreeMap<Integer, StudentsModels>();
    private int idCounter = 1;
    private Scanner scanner = new Scanner(System.in);
    private Scanner studentsUserInput = new Scanner(System.in);
    Menu menu = new Menu();

    public void StudentsMenu() {

        int choice = -1;
        while (choice != 0) {

            System.out.println("\nStudents Menu");
            System.out.println("Please choose an option to continue: ");
            System.out.println("--------------------------------");
            System.out.println("1. Show students");
            System.out.println("2. Add a student");
            System.out.println("3. Update student's information");
            System.out.println("4. Remove a student");
            System.out.println("0. Back");

            int choice1 = scanner.nextInt();
            if (choice1 == 1) {
                showStudents(studentsMap);
            } else if (choice1 == 2) {
                addStudent();
            } else if (choice1 == 3) {
                updateStudent();
            } else if (choice1 == 4) {
                removeStudent();
            } else if (choice1 == 0) {
                menu.menu();
            }

            if (choice1 < 0 || choice1 > 4) {
                System.out.println("Please use a valid option (0 - 4)");
                StudentsMenu();
            }
        }
    }

    public static void showStudents(Map<Integer, StudentsModels> studentsMap) {
        if (studentsMap.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("\nAll students: ");
            for (StudentsModels student : studentsMap.values()) {
                System.out.println("ID: " + student.getId() + ", Name: " + student.getFirstName() + " " + student.getLastName()
                        + ", Personal ID: " + student.getPersonalId() + "\nAddress: " + student.getEmail()
                        + ", Specialization: " + student.getSpecialization());
            }
        }
    }

    public void addStudent() {
        while (true) {
            System.out.println("Enter student's first name (or type 'quit' to cancel): ");
            String firstName = studentsUserInput.nextLine();
            if (firstName.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println("Enter student's last name: ");
            String lastName = studentsUserInput.nextLine();
            System.out.println("Enter student's email address: ");
            String email = studentsUserInput.nextLine();
            System.out.println("Enter student's specialization: ");
            String specialization = studentsUserInput.nextLine();


            StudentsModels student = new StudentsModels();
            student.setId(idCounter++);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEmail(email);
            student.setSpecialization(specialization);
            studentsMap.put(student.getId(), student);

        }
    }

    public void updateStudent(){
        System.out.println("Enter the ID of the desired student: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        StudentsModels student = studentsMap.get(studentId);
        if(student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }

        System.out.println("Enter new first name (or leave blank to keep current): ");
        String newFirstName = studentsUserInput.nextLine();
        if (!newFirstName.isEmpty()) {
            student.setFirstName(newFirstName);
        }

        System.out.println("Enter new last name (or leave blank to keep current): ");
        String newLastName = studentsUserInput.nextLine();
        if (!newLastName.isEmpty()) {
            student.setLastName(newLastName);
        }

        System.out.println("Enter new email address (or leave blank to keep current): ");
        String newEmail = studentsUserInput.nextLine();
        if (!newEmail.isEmpty()) {
            student.setEmail(newEmail);
        }

        System.out.println("Enter new specialization (or leave blank to keep current): ");
        String newSpecialization = studentsUserInput.nextLine();
        if (!newSpecialization.isEmpty()) {
            student.setSpecialization(newSpecialization);
        }

        System.out.println("Student information updated successfully!");
    }

    public void removeStudent(){
        System.out.println("Enter the ID of the student that you want to remove: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        StudentsModels removedStudent = studentsMap.remove(studentId);
        if (removedStudent == null){
            System.out.println("Student with ID " + studentId + " not found.");
        } else {
            System.out.println("Student removed successfully!");
        }
    }
}
