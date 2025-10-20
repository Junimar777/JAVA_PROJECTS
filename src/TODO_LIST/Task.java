package TODO_LIST;

public class Task {
     String description;
     boolean isDone;


    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void markDone(){
        this.isDone = true;
    }

    @Override
    public String toString() {
        return (isDone ? "[✔]" : "[ ]") + " " + description;//eto parang ternary  true or false
    }
}
