package model;

public class Quiz {
    private int id;
    private String title;
    private int totalMarks;

    public Quiz() {}
    public Quiz(String title, int totalMarks) {
        this.title = title;
        this.totalMarks = totalMarks;
    }
    public Quiz(int id, String title, int totalMarks) {
        this(title, totalMarks);
        this.id = id;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getTotalMarks() { return totalMarks; }
    public void setId(int id) { this.id = id; }
}
