package tracker;

import java.util.Arrays;

import static tracker.Utils.input;

public class Main {
    public static void main(String[] args) {
        ProjectManager pm = new ProjectManager();

        while (true) {
            Utils.printHeader("Scripto - Dev Project Tracker");
            System.out.println("""
                    1. Add Project
                    2. List All
                    3. Filter by Status
                    4. Filter by Language
                    5. Search
                    6. Edit Progress
                    7. Change Status
                    8. Delete Project
                    9. View Stats
                    0. Exit
                    """);

            System.out.print("Choice: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Name: ");
                    String name = input.nextLine();
                    System.out.print("Status (In Progress / Completed / On Hold): ");
                    String status = input.nextLine();
                    System.out.print("Languages (comma-separated): ");
                    String[] langs = input.nextLine().split(",");
                    System.out.print("Deadline (yyyy-mm-dd): ");
                    String deadline = input.nextLine();
                    System.out.print("Progress (0-100): ");
                    int progress = Integer.parseInt(input.nextLine());
                    System.out.print("Notes: ");
                    String notes = input.nextLine();
                    System.out.print("Git Link (optional): ");
                    String git = input.nextLine();

                    Project p = new Project(name, status.trim(), Arrays.stream(langs).map(String::trim).toList(),
                            Utils.getTodayDate(), deadline.trim(), progress, notes, git);
                    pm.addProject(p);
                }
                case "2" -> pm.listAll();
                case "3" -> {
                    System.out.print("Status: ");
                    pm.filterByStatus(input.nextLine());
                }
                case "4" -> {
                    System.out.print("Language: ");
                    pm.filterByLang(input.nextLine());
                }
                case "5" -> {
                    System.out.print("Keyword: ");
                    pm.search(input.nextLine());
                }
                case "6" -> {
                    System.out.print("Project #: ");
                    int i = Integer.parseInt(input.nextLine()) - 1;
                    System.out.print("New progress (0-100): ");
                    int p = Integer.parseInt(input.nextLine());
                    pm.editProgress(i, p);
                }
                case "7" -> {
                    System.out.print("Project #: ");
                    int i = Integer.parseInt(input.nextLine()) - 1;
                    System.out.print("New Status: ");
                    String s = input.nextLine();
                    pm.changeStatus(i, s);
                }
                case "8" -> {
                    System.out.print("Project #: ");
                    int i = Integer.parseInt(input.nextLine()) - 1;
                    pm.deleteProject(i);
                }
                case "9" -> pm.showStats();
                case "0" -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
            Utils.pressEnter();
        }
    }
}
