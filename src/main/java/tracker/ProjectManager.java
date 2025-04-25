package tracker;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ProjectManager {
    private final List<Project> projects = new ArrayList<>();
    private final String DATA_FILE = "data/projects.json";
    private final Gson gson = new Gson();

    public ProjectManager() {
        loadProjects();
    }

    public void addProject(Project p) {
        projects.add(p);
        saveProjects();
        autoBackup();
    }

    public void listAll() {
        Utils.printHeader("All Projects");
        if (projects.isEmpty()) {
            System.out.println("No projects yet.");
        } else {
            int index = 1;
            for (Project p : projects) {
                System.out.println(index++ + ". " + p);
            }
        }
    }

    public void deleteProject(int index) {
        if (index >= 0 && index < projects.size()) {
            projects.remove(index);
            saveProjects();
            autoBackup();
        }
    }

    public void editProgress(int index, int progress) {
        if (index >= 0 && index < projects.size()) {
            projects.get(index).setProgress(progress);
            saveProjects();
        }
    }

    public void changeStatus(int index, String newStatus) {
        if (index >= 0 && index < projects.size()) {
            projects.get(index).setStatus(newStatus);
            saveProjects();
        }
    }

    public void filterByLang(String lang) {
        Utils.printHeader("Filter: Language = " + lang);
        projects.stream()
                .filter(p -> p.getLanguages().contains(lang))
                .forEach(System.out::println);
    }

    public void filterByStatus(String status) {
        Utils.printHeader("Filter: Status = " + status);
        projects.stream()
                .filter(p -> p.getStatus().equalsIgnoreCase(status))
                .forEach(System.out::println);
    }

    public void search(String keyword) {
        Utils.printHeader("Search Results");
        projects.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                            p.getNotes().toLowerCase().contains(keyword.toLowerCase()))
                .forEach(System.out::println);
    }

    public void showStats() {
        Utils.printHeader("Stats");
        long total = projects.size();
        long completed = projects.stream().filter(p -> p.getStatus().equalsIgnoreCase("Completed")).count();
        long inProgress = projects.stream().filter(p -> p.getStatus().equalsIgnoreCase("In Progress")).count();

        System.out.println("Total Projects: " + total);
        System.out.println("Completed: " + completed);
        System.out.println("In Progress: " + inProgress);

        Map<String, Long> langCount = projects.stream()
                .flatMap(p -> p.getLanguages().stream())
                .collect(Collectors.groupingBy(l -> l, Collectors.counting()));

        System.out.println("\nTop Languages:");
        langCount.forEach((lang, count) -> System.out.println(" - " + lang + ": " + count));
    }

    private void loadProjects() {
        try {
            Reader reader = new FileReader(DATA_FILE);
            List<Project> list = gson.fromJson(reader, new TypeToken<List<Project>>(){}.getType());
            if (list != null) projects.addAll(list);
        } catch (IOException e) {
            System.out.println("No saved data found.");
        }
    }

    private void saveProjects() {
        try {
            Writer writer = new FileWriter(DATA_FILE);
            gson.toJson(projects, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    private void autoBackup() {
        String fileName = "backups/backup_" + Utils.getTodayDate() + ".json";
        try {
            Files.copy(Paths.get(DATA_FILE), Paths.get(fileName),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Failed to create backup.");
        }
    }

    public int getSize() {
        return projects.size();
    }
}
