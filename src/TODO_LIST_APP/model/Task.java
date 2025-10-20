package TODO_LIST_APP.model;

public class Task {
    private int id;
    private String title;
    private String description;
    private boolean completed; // status true-done pag false-pending

    //constructor
    public Task(int id, String title, String description){
        this.id =id;
        this.title=title;
        this.description=description;
        this.completed=false; // eto ung default nya false muna

    }
    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        String status = completed ? "DONE" : "PENDING";
        return id + ". " + title + ". " + status + "\n " + description;
    }
}
