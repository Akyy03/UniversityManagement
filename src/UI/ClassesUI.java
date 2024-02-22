package UI;

import Models.ClassesModels;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ClassesUI {

    private Map<Integer, ClassesModels> classesMap = new TreeMap<Integer, ClassesModels>();
    private int idCounter = 1;
    private Scanner scanner = new Scanner(System.in);
    private Scanner classesUserInput = new Scanner(System.in);
    Menu menu = new Menu();
    public void ClassesMenu(){

        int choice = -1;
        while (choice != 0) {

            System.out.println("\nClasses Menu");
            System.out.println("Please choose an option to continue: ");
            System.out.println("--------------------------------");
            System.out.println("1. Show classes");
            System.out.println("2. Add a class");
            System.out.println("3. Update a class information");
            System.out.println("4. Remove a class");
            System.out.println("0. Back");

            int choice1 = scanner.nextInt();
            if (choice1 == 1) {
                showClasses(classesMap);
            } else if (choice1 == 2) {
                addClass();
            } else if (choice1 == 3) {
                updateClass();
            } else if (choice1 == 4) {
                removeClass();
            } else if (choice1 == 0) {
                menu.menu();
            }

            if (choice1 < 0 || choice1 > 4) {
                System.out.println("Please use a valid option (0 - 4)");
                ClassesMenu();
            }
        }
    }

    public void addClass() {
        while (true) {
            System.out.println("Enter class name (or type 'quit' to cancel): ");
            String className = classesUserInput.nextLine();
            if (className.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println("Enter the number of credits the class is worth: ");
            int credits = classesUserInput.nextInt();
            classesUserInput.nextLine();

            ClassesModels classes = new ClassesModels();
            classes.setId(idCounter++);
            classes.setClassName(className);
            classes.setCredits(credits);
            classesMap.put(classes.getId(), classes);

        }
    }

    public static void showClasses(Map<Integer, ClassesModels> classesMap) {
        if (classesMap.isEmpty()) {
            System.out.println("No classes found.");
        } else {
            System.out.println("\nAll classes: ");
            for (ClassesModels classes : classesMap.values()) {
                System.out.println("ID: " + classes.getId() + ", Name: " + classes.getClassName()
                + ", Credits Worth: " + classes.getCredits());
            }
        }
    }

    public void updateClass() {
        System.out.println("Enter the ID of the desired class: ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        ClassesModels classes = classesMap.get(classId);
        if (classes == null) {
            System.out.println("Class with ID " + classId + " not found.");
            return;
        }

        System.out.println("Enter new class name (or leave blank to keep current): ");
        String newClassName = classesUserInput.nextLine();
        if (!newClassName.isEmpty()) {
            classes.setClassName(newClassName);
        }

        System.out.println("Enter new number of credits (or leave blank to keep current): ");
        String newCredits = classesUserInput.nextLine();
        if (!newCredits.isEmpty()) {
            classes.setCredits(Integer.parseInt(newCredits));
        }

        System.out.println("Class information updated successfully!");
    }

    public void removeClass() {
        System.out.println("Enter the ID of the class that you want to remove: ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        ClassesModels removedClass = classesMap.remove(classId);
        if (removedClass == null) {
            System.out.println("Class with ID " + classId + " not found.");
        } else {
            System.out.println("Class removed successfully!");
        }
    }
}
