package dao;

import db.DBConnection;
import model.Quiz;
import java.sql.*;
import java.util.*;

public class QuizDAO {
    public void addQuiz(Quiz quiz) {
        String query = "INSERT INTO quizzes(title, total_marks) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, quiz.getTitle());
            ps.setInt(2, quiz.getTotalMarks());
            ps.executeUpdate();
            System.out.println("Quiz added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Quiz> getAllQuizzes() {
        List<Quiz> list = new ArrayList<>();
        String query = "SELECT * FROM quizzes";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Quiz(rs.getInt("id"), rs.getString("title"), rs.getInt("total_marks")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
