package UI;

import Models.ClassesModels;
import Models.SchedulesModels;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class SchedulesUI {

    private Map<Integer, SchedulesModels> schedulesMap = new TreeMap<Integer, SchedulesModels>();
    private int idCounter = 1;
    private Scanner scanner = new Scanner(System.in);
    private Scanner schedulesUserInput = new Scanner(System.in);
    Menu menu = new Menu();

    public void SchedulesMenu() {

        int choice = -1;
        while (choice != 0) {

            System.out.println("\nSchedules Menu");
            System.out.println("Please choose an option to continue: ");
            System.out.println("--------------------------------");
            System.out.println("1. Show schedules");
            System.out.println("2. Add a class to schedule");
            System.out.println("3. Update a schedule");
            System.out.println("4. Remove a class from the schedule");
            System.out.println("0. Back");

            int choice1 = scanner.nextInt();
            if (choice1 == 1) {
                showSchedules(schedulesMap);
            } else if (choice1 == 2) {
                addSchedules();
            } else if (choice1 == 3) {
                updateSchedules();
            } else if (choice1 == 4) {
                removeSchedules();
            } else if (choice1 == 0) {
                menu.menu();
            }

            if (choice1 < 0 || choice1 > 4) {
                System.out.println("Please use a valid option (0 - 4)");
                SchedulesMenu();
            }
        }
    }

    public void addSchedules() {
        while (true) {
            System.out.println("Enter class name (or type 'quit' to cancel): ");
            String subject = schedulesUserInput.nextLine();
            if (subject.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.println("Enter the day(s) on which this class takes place: ");
            String day = schedulesUserInput.nextLine();

            System.out.println("Enter the start hour for this class (HH:MM): ");
            String startHour = schedulesUserInput.nextLine();

            System.out.println("Enter the end hour for this class (HH:MM): ");
            String endHour = schedulesUserInput.nextLine();

            System.out.println("Enter the professor's name: ");
            String professorName = schedulesUserInput.nextLine();


            SchedulesModels schedules = new SchedulesModels();
            schedules.setId(idCounter++);
            schedules.setSubject(subject);
            schedules.setDay(day);
            schedules.setStartHour(startHour);
            schedules.setEndHour(endHour);
            schedules.setProfessor(professorName);
            schedulesMap.put(schedules.getId(), schedules);
        }
    }


    public static void showSchedules(Map<Integer, SchedulesModels> schedulesMap) {
        if (schedulesMap.isEmpty()) {
            System.out.println("No schedules found.");
        } else {
            System.out.println("\nAll schedules: ");
            for (SchedulesModels schedules : schedulesMap.values()) {
                System.out.println("ID: " + schedules.getId() + ", Class Name: " + schedules.getSubject()
                        + ", Day(s): " + schedules.getDay() + "\nHours: " + schedules.getStartHour() + " - "
                        + schedules.getEndHour() + ", Held by: " + schedules.getProfessor());
            }
        }
    }

    public void updateSchedules() {
        System.out.println("Enter the ID of the desired class: ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        SchedulesModels schedules = schedulesMap.get(classId);
        if (schedules == null) {
            System.out.println("Class with ID " + classId + " not found.");
            return;
        }

        System.out.println("Enter new class name (or leave blank to keep current): ");
        String newSubject = schedulesUserInput.nextLine();
        if (!newSubject.isEmpty()) {
            schedules.setSubject(newSubject);
        }

        System.out.println("Enter new day(s) on which this class takes place (or leave blank to keep current): ");
        String newDay = schedulesUserInput.nextLine();
        if (!newDay.isEmpty()) {
            schedules.setDay(newDay);
        }

        System.out.println("Enter new start hour (HH:MM) (or leave blank to keep current): ");
        String newStartHour = schedulesUserInput.nextLine();
        if (!newStartHour.isEmpty()) {
            schedules.setStartHour(newStartHour);
        }

        System.out.println("Enter new end hour (HH:MM) (or leave blank to keep current): ");
        String newEndHour = schedulesUserInput.nextLine();
        if (!newEndHour.isEmpty()) {
            schedules.setEndHour(newEndHour);
        }

        System.out.println("Enter new professor name (or leave blank to keep current): ");
        String newProfessor = schedulesUserInput.nextLine();
        if (!newProfessor.isEmpty()) {
            schedules.setProfessor(newProfessor);
        }

        System.out.println("Class information updated successfully!");
    }

    public void removeSchedules() {
        System.out.println("Enter the ID of the class that you want to remove from the schedule: ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        SchedulesModels removedClass = schedulesMap.remove(classId);
        if (removedClass == null) {
            System.out.println("Class with ID " + classId + " not found.");
        } else {
            System.out.println("Class removed successfully!");
        }
    }
}
