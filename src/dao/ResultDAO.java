package dao;

import db.DBConnection;
import java.sql.*;

public class ResultDAO {
    public void recordResult(int studentId, int quizId, int score) {
        String query = "INSERT INTO results(student_id, quiz_id, score) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, studentId);
            ps.setInt(2, quizId);
            ps.setInt(3, score);
            ps.executeUpdate();
            System.out.println("Result recorded.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewResultsByStudent(int studentId) {
        String query = """
            SELECT s.name AS student_name, q.title, r.score, q.total_marks
            FROM results r
            JOIN students s ON r.student_id = s.id
            JOIN quizzes q ON r.quiz_id = q.id
            WHERE r.student_id = ?
        """;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.printf("Student: %s | Quiz: %s | Score: %d/%d%n",
                        rs.getString("student_name"), rs.getString("title"),
                        rs.getInt("score"), rs.getInt("total_marks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAverageScores() {
        String query = """
            SELECT q.title, AVG(r.score) AS avg_score
            FROM results r
            JOIN quizzes q ON r.quiz_id = q.id
            GROUP BY q.title
        """;
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                System.out.printf("Quiz: %s | Average Score: %.2f%n",
                        rs.getString("title"), rs.getDouble("avg_score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
