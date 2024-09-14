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
    public void SignUpUser(ActionEvent event,String username,String password,String email) {
        Connection connection=null;
        PreparedStatement psInsert=null;
        PreparedStatement psCheckUserExists=null;
        ResultSet resultSet=null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "hasan099");
            psCheckUserExists = connection.prepareStatement("select * from user_table where email=? ");
            psCheckUserExists.setString(1,email);
            resultSet=psCheckUserExists.executeQuery();
            if (resultSet.isBeforeFirst()){
                System.out.println("Username already exists");
                showAlert(Alert.AlertType.ERROR, "Error", "Email already exists.");
            }
            else{
                psInsert= connection.prepareStatement("insert into user_table(username,password,email) values(?,?,?)");
                psInsert.setString(1,username);
                psInsert.setString(2,password);
                psInsert.setString(3,email);
                psInsert.executeUpdate();
                showAlert(Alert.AlertType.INFORMATION, "Success", "User registered successfully.");
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

}
