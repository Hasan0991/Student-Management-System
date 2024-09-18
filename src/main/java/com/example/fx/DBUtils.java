package com.example.fx;


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

    public static void changeScene(ActionEvent event,String FXMLFile,String username) {
        Parent root = null;

        if(username!=null){
            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(FXMLFile));
                root =loader.load();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }else{
            try{
                root=FXMLLoader.load(DBUtils.class.getResource(FXMLFile));
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void SignUpUser(ActionEvent event,User user) {
        Connection connection=null;
        PreparedStatement psInsert=null;
        PreparedStatement psCheckUserExists=null;
        ResultSet resultSet=null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "hasan099");
            psCheckUserExists = connection.prepareStatement("select * from user_table where email=? ");
            psCheckUserExists.setString(1,user.getEmail());
            resultSet=psCheckUserExists.executeQuery();
            if (resultSet.isBeforeFirst()){
                System.out.println("Username already exists");
                showAlert(Alert.AlertType.ERROR, "Error", "Email already exists.");
            }
            else{

                String hashedPassword = Encryptor.encryptString(user.getPassword());

                if (Encryptor.checkPassword(user.getPassword(), hashedPassword)) {
                    psInsert= connection.prepareStatement("insert into user_table(username,password,email) values(?,?,?)");
                    psInsert.setString(1,user.getName());
                    psInsert.setString(2,hashedPassword);
                    psInsert.setString(3,user.getEmail());
                    psInsert.executeUpdate();
                    showAlert(Alert.AlertType.INFORMATION, "Success", "User registered successfully.");
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if(resultSet!=null){
                try {
                    resultSet.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try{
                    psCheckUserExists.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try{
                    psInsert.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try{
                    connection.close();
                }
                catch (SQLException e){
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
        }
        finally {
            if (resultSet != null) {
                try{
                    resultSet.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try{
                    preparedStatement.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try{
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace(); ;
                }
            }
        }
    }
}