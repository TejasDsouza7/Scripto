package tracker;

import java.util.List;

public class Project {
    private String name;
    private String status;
    private List<String> languages;
    private String startDate;
    private String deadline;
    private int progress;
    private String notes;
    private String gitLink;

    public Project(String name, String status, List<String> languages, String startDate,
                String deadline, int progress, String notes, String gitLink) {
        this.name = name;
        this.status = status;
        this.languages = languages;
        this.startDate = startDate;
        this.deadline = deadline;
        this.progress = progress;
        this.notes = notes;
        this.gitLink = gitLink;
    }

    public String getName() { return name; }
    public String getStatus() { return status; }
    public List<String> getLanguages() { return languages; }
    public String getStartDate() { return startDate; }
    public String getDeadline() { return deadline; }
    public int getProgress() { return progress; }
    public String getNotes() { return notes; }
    public String getGitLink() { return gitLink; }

    public void setProgress(int progress) { this.progress = progress; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return  name + "\n" +
                " - Status: " + status + " [" + progress + "%]\n" +
                " - Langs: " + String.join(", ", languages) + "\n" +
                " - Start: " + startDate + ", Deadline: " + deadline + "\n" +
                " - Notes: " + notes + "\n" +
                " - Git: " + (gitLink.isEmpty() ? "N/A" : gitLink) + "\n";
    }
}
