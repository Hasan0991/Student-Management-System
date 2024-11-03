package com.example.fx;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {
    public static boolean isValidEmail(String email) {

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(emailRegex) && email.endsWith("@gmail.com");
    }

    public static void changeScene(ActionEvent event, String FXMLFile, String username) {
        Parent root = null;

        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(FXMLFile));
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(FXMLFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void SignUpUser(ActionEvent event, User user) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        ResultSet resultSet = null;
        PreparedStatement psCheckUserExists = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "hasan099");
            psCheckUserExists = connection.prepareStatement("select * from user_table where email=? ");
            psCheckUserExists.setString(1, user.getEmail());
            resultSet = psCheckUserExists.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("Username already exists");
                showAlert(Alert.AlertType.ERROR, "Error", "Email already exists.");
            } else {

                String hashedPassword = Encryptor.encryptString(user.getPassword());

                if (Encryptor.checkPassword(user.getPassword(), hashedPassword)) {
                    psInsert = connection.prepareStatement("insert into user_table(username,password,email) values(?,?,?)");
                    psInsert.setString(1, user.getName());
                    psInsert.setString(2, hashedPassword);
                    psInsert.setString(3, user.getEmail());
                    psInsert.executeUpdate();
                    showAlert(Alert.AlertType.INFORMATION, "Success", "User registered successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void SignInUser(ActionEvent event, String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (email.equals("admin") && password.equals("admin123")) {
            DBUtils.changeScene(event, "admin-view.fxml", "admin");
            return;
        }
        try {
            String hashedPassword = Encryptor.encryptString(password);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "hasan099");
            preparedStatement = connection.prepareStatement("select password from user_table where email=?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Email not found in Database");
            } else {
                String storedPassword = resultSet.getString("password");
                System.out.println("Entered Password: " + password);
                System.out.println("Stored Password: " + storedPassword);
                boolean isPasswordCorrect = Encryptor.checkPassword(password, storedPassword);
                if (isPasswordCorrect) {
                    showAlert(Alert.AlertType.INFORMATION, "Congratulations", "Everything is okay");
                    changeScene(event, "admin-view.fxml", email);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Provided credentials are incorrect");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ObservableList<StudentData> getStudents() {
        ObservableList<StudentData> students = FXCollections.observableArrayList();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "hasan099");
            Statement stmt = conn.createStatement();
            String query = "SELECT number,name,surname, gender,dateOfBirth,country, course, status,student_id FROM student";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                students.add(new StudentData(
                        rs.getInt("number"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("gender"),
                        rs.getDate("dateOfBirth").toLocalDate(),
                        rs.getString("course"),
                        rs.getString("country"),
                        rs.getString("status"),
                        rs.getInt("student_id")
                ));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
    public ObservableList<Course> getCourses() {
        ObservableList<Course> courses = FXCollections.observableArrayList();
        String query = "SELECT course_id,course_name FROM course";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "hasan099");
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){


            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name")
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    public Boolean deleteSelectedStudent(int student_id) {
        String query = "DELETE FROM student where student_id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "hasan099");
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, student_id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public void AddStudent(ActionEvent event, StudentData student) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "hasan099");
            psInsert = connection.prepareStatement("INSERT INTO student (number, name, surname, gender, dateOfBirth, course, country, status, student_id) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?)");

            psInsert.setInt(1, student.getNumber());
            psInsert.setString(2, student.getName());
            psInsert.setString(3, student.getSurname());
            psInsert.setString(4, student.getGender());

            java.sql.Date birthDate = java.sql.Date.valueOf(student.getDateOfBirth());
            psInsert.setDate(5, birthDate);

            psInsert.setString(6, student.getCourse());
            psInsert.setString(7, student.getCountry());
            psInsert.setString(8, student.getStatus());
            psInsert.setInt(9, student.getStudentId());

            int rowsAffected = psInsert.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student added successfully.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psInsert != null) psInsert.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public boolean replace_studentData(int studentId, String course) {
        String url = "jdbc:mysql://localhost:3306/javafx";
        String user = "root";
        String password = "hasan099";
        String query = "UPDATE student SET course = ? WHERE student_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, course);
            stmt.setInt(2, studentId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void insertSubject(int course_id,String subject_name,int etc_points){
        String query = "INSERT INTO subjects(course_id,subject_name,etc_points) VALUES(?,?,?)";
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "hasan099");
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,course_id);
            stmt.setString(2,subject_name);
            stmt.setInt(3,etc_points);
            stmt.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Success", "Subject added successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Subject> getSubjects(int course_id) {
        String query  = "SELECT * FROM subjects WHERE course_id = ?";
        ObservableList<Subject> subjects = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "hasan099");
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1,course_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String subjectName = rs.getString("subject_name");
                int etcPoints = rs.getInt("etc_points");
                subjects.add(new Subject(subjectName,etcPoints));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

}