package model;

public class Result {
    private int id;
    private int studentId;
    private int quizId;
    private int score;

    public Result() {}
    public Result(int studentId, int quizId, int score) {
        this.studentId = studentId;
        this.quizId = quizId;
        this.score = score;
    }

    // Getters and setters
    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getQuizId() { return quizId; }
    public int getScore() { return score; }

    public void setId(int id) { this.id = id; }
}
