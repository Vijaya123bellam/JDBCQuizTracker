package service;

import dao.*;
import model.*;
import java.util.*;

public class QuizService {
    Scanner sc = new Scanner(System.in);
    StudentDAO studentDAO = new StudentDAO();
    QuizDAO quizDAO = new QuizDAO();
    ResultDAO resultDAO = new ResultDAO();

    public void start() {
        while (true) {
            System.out.println("\n== Quiz Result Tracker ==");
            System.out.println("1. Add Student");
            System.out.println("2. Add Quiz");
            System.out.println("3. Record Result");
            System.out.println("4. View Student Results");
            System.out.println("5. View Average Scores");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addStudent();
                case 2 -> addQuiz();
                case 3 -> recordResult();
                case 4 -> viewStudentResults();
                case 5 -> resultDAO.viewAverageScores();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        studentDAO.addStudent(new Student(name, email));
    }

    private void addQuiz() {
        System.out.print("Enter quiz title: ");
        String title = sc.nextLine();
        System.out.print("Enter total marks: ");
        int total = sc.nextInt();
        quizDAO.addQuiz(new Quiz(title, total));
    }

    private void recordResult() {
        System.out.print("Enter student ID: ");
        int sid = sc.nextInt();
        System.out.print("Enter quiz ID: ");
        int qid = sc.nextInt();
        System.out.print("Enter score: ");
        int score = sc.nextInt();
        resultDAO.recordResult(sid, qid, score);
    }

    private void viewStudentResults() {
        System.out.print("Enter student ID: ");
        int sid = sc.nextInt();
        resultDAO.viewResultsByStudent(sid);
    }
}
