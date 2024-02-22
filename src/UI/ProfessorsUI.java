package UI;

import Models.ProfessorsModels;
import Models.StudentsModels;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ProfessorsUI {

    private Map<Integer, ProfessorsModels> professorsMap = new TreeMap<Integer, ProfessorsModels>();
    private int idCounter = 1;
    private Scanner scanner = new Scanner(System.in);
    private Scanner professorsUserInput = new Scanner(System.in);
    Menu menu = new Menu();

    public void ProfessorsMenu() {

        int choice = -1;
        while (choice != 0) {

            System.out.println("\nProfessors Menu");
            System.out.println("Please choose an option to continue: ");
            System.out.println("--------------------------------");
            System.out.println("1. Show professors");
            System.out.println("2. Add a professor");
            System.out.println("3. Update professor's information");
            System.out.println("4. Remove a professor");
            System.out.println("0. Back");

            int choice1 = scanner.nextInt();
            if (choice1 == 1) {
                showProfessors(professorsMap);
            } else if (choice1 == 2) {
                addProfessor();
            } else if (choice1 == 3) {
                updateProfessor();
            } else if (choice1 == 4) {
                removeProfessor();
            } else if (choice1 == 0) {
                menu.menu();
            }

            if (choice1 < 0 || choice1 > 4) {
                System.out.println("Please use a valid option (0 - 4)");
                ProfessorsMenu();
            }
        }
    }

    public void addProfessor() {
        while (true) {
            System.out.println("Enter professor's first name (or type 'quit' to cancel): ");
            String firstName = professorsUserInput.nextLine();
            if (firstName.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println("Enter professor's last name: ");
            String lastName = professorsUserInput.nextLine();
            System.out.println("Enter professor's email address: ");
            String email = professorsUserInput.nextLine();
            System.out.println("Enter professor's specialization: ");
            String specialization = professorsUserInput.nextLine();


            ProfessorsModels professor = new ProfessorsModels();
            professor.setId(idCounter++);
            professor.setFirstName(firstName);
            professor.setLastName(lastName);
            professor.setEmail(email);
            professor.setSpecialization(specialization);
            professorsMap.put(professor.getId(), professor);

        }
    }

    public static void showProfessors(Map<Integer, ProfessorsModels> professorsMap) {
        if (professorsMap.isEmpty()) {
            System.out.println("No professors found.");
        } else {
            System.out.println("\nAll professors: ");
            for (ProfessorsModels professor : professorsMap.values()) {
                System.out.println("ID: " + professor.getId() + ", Name: " + professor.getFirstName() + " " + professor.getLastName()
                        + "\nAddress: " + professor.getEmail()
                        + ", Specialization: " + professor.getSpecialization());
            }
        }
    }

    public void updateProfessor() {
        System.out.println("Enter the ID of the desired professor: ");
        int professorId = scanner.nextInt();
        scanner.nextLine();

        ProfessorsModels professor = professorsMap.get(professorId);
        if (professor == null) {
            System.out.println("Professor with ID " + professorId + " not found.");
            return;
        }

        System.out.println("Enter new first name (or leave blank to keep current): ");
        String newFirstName = professorsUserInput.nextLine();
        if (!newFirstName.isEmpty()) {
            professor.setFirstName(newFirstName);
        }

        System.out.println("Enter new last name (or leave blank to keep current): ");
        String newLastName = professorsUserInput.nextLine();
        if (!newLastName.isEmpty()) {
            professor.setLastName(newLastName);
        }

        System.out.println("Enter new email address (or leave blank to keep current): ");
        String newEmail = professorsUserInput.nextLine();
        if (!newEmail.isEmpty()) {
            professor.setEmail(newEmail);
        }

        System.out.println("Enter new specialization (or leave blank to keep current): ");
        String newSpecialization = professorsUserInput.nextLine();
        if (!newSpecialization.isEmpty()) {
            professor.setSpecialization(newSpecialization);
        }

        System.out.println("Professor information updated successfully!");
    }

    public void removeProfessor() {
        System.out.println("Enter the ID of the professor that you want to remove: ");
        int professorId = scanner.nextInt();
        scanner.nextLine();

        ProfessorsModels removedProfessor = professorsMap.remove(professorId);
        if (removedProfessor == null) {
            System.out.println("Professor with ID " + professorId + " not found.");
        } else {
            System.out.println("Professor removed successfully!");
        }
    }
}
