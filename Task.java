
public class Task {
    static int count = 1;
    int id;
    String title;
    String description;
    String status;
    String date;

    public Task(String title, String description, String status, String date) {
        this.id = count++;
        this.title = title;
        this.description = description;
        this.status = status;
        this.date = date;
    }

    public String toString() {
        String str = "ID: " + this.id + ", Title: " + this.title + ", description: " + this.description + ", status: "
                + this.status + ", Date: " + this.date;

        return str;
    }
}
