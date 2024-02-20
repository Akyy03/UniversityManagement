package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public void menu() {

        int choice = -1;
        while (choice != 0) {
            System.out.println("\nWelcome to our University!");
            System.out.println("Please choose an option to continue: ");
            System.out.println("---------------------");
            System.out.println("1. Students Menu");
            System.out.println("2. Professors Menu");
            System.out.println("3. Classes Menu");
            System.out.println("4. Schedule Menu");
            System.out.println("0. Close");
            try {
                StudentsUI studentsUI = new StudentsUI();
                ProfessorsUI professorsUI = new ProfessorsUI();
                ClassesUI classesUI = new ClassesUI();
                SchedulesUI schedulesUI = new SchedulesUI();

                Scanner scanner = new Scanner(System.in);
                int choice2 = scanner.nextInt();

                if (choice2 == 1) {
                    studentsUI.StudentsMenu();
                } else if (choice2 == 2) {
                    professorsUI.ProfessorsMenu();
                } else if (choice2 == 3) {
                    classesUI.ClassesMenu();
                } else if (choice2 == 4) {
                    schedulesUI.SchedulesMenu();
                } else if (choice2 == 0) {
                    System.exit(0);
                }

                if (choice2 < 0 || choice2 > 4) {
                    System.out.println("Please use a valid option (0 - 4)");
                    menu();
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option");
                menu();
            }
        }
    }
}
