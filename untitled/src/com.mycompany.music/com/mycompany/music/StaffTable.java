package com.mycompany.music;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Insets;

import java.sql.*;
import java.lang.*;


public class StaffTable extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Button viewButton = new Button("View");
        Button insertButton = new Button("Insert");
        Button updateButton = new Button("Update");
        Button clearButton = new Button("Clear");

        HBox buttons = new HBox(2);
        buttons.getChildren().addAll(viewButton, insertButton, updateButton, clearButton);
        buttons.setAlignment(Pos.CENTER);

        Text textbox = new Text();
        textbox.setText("Database connected.");

        Label label1 = new Label("Name:");
        TextField textField = new TextField ();
        HBox text = new HBox();
        text.getChildren().addAll(label1, textField);
        text.setSpacing(10);

        //Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        final TextField ID = new TextField();
        ID.setPromptText("Enter your ID.");
        ID.setPrefColumnCount(10);
        ID.getText();
        GridPane.setConstraints(ID, 0, 0);
        grid.getChildren().add(ID);

        final TextField lastName = new TextField();
        lastName.setPromptText("Enter your last name.");
        GridPane.setConstraints(lastName, 0, 1);
        grid.getChildren().add(lastName);

        final TextField firstName = new TextField();
        firstName.setPromptText("Enter your first name.");
        GridPane.setConstraints(firstName, 1, 1);
        grid.getChildren().add(firstName);

        final TextField MI = new TextField();
        MI.setPromptText("Enter your middle initial.");
        GridPane.setConstraints(MI, 2, 1);
        grid.getChildren().add(MI);

        final TextField address = new TextField();
        address.setPromptText("Enter your address.");
        GridPane.setConstraints(address, 0, 2);
        grid.getChildren().add(address);

        final TextField city = new TextField();
        city.setPromptText("Enter your city.");
        GridPane.setConstraints(city, 0, 3);
        grid.getChildren().add(city);

        final TextField state = new TextField();
        state.setPromptText("Enter your state.");
        GridPane.setConstraints(state, 1, 3);
        grid.getChildren().add(state);

        final TextField telephone = new TextField();
        telephone.setPromptText("Enter your phone number.");
        GridPane.setConstraints(telephone, 0, 4);
        grid.getChildren().add(telephone);

        viewButton.setOnAction(value ->  {
            String url = "jdbc:mysql://127.0.0.1:3306/stafftable";
            String username = "root";
            String password = "Pa$$word";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                String newID = null;
                if(ID.getText().length() == 9 && isNumeric(ID.getText())) {
                    newID = ID.getText();
                    ResultSet resultSet = statement.executeQuery("select * from stafftable where ID = " + newID);
                    resultSet.next();
                    ID.setText(Integer.toString(resultSet.getInt(1)));
                    lastName.setText(resultSet.getString(2));
                    firstName.setText(resultSet.getString(3));
                    MI.setText(resultSet.getString(4));
                    address.setText(resultSet.getString(5));
                    city.setText(resultSet.getString(6));
                    state.setText(resultSet.getString(7));
                    telephone.setText(resultSet.getString(8));
                    textbox.setText("Displaying data for ID: " + newID);
                }else{
                    textbox.setText("Enter a valid ID.");
                }

                connection.close();

            } catch (Exception e) {
                System.out.println(e);
            }

        });

        insertButton.setOnAction(value ->  {
            String url = "jdbc:mysql://127.0.0.1:3306/stafftable";
            String username = "root";
            String password = "Pa$$word";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);

                String newID = null;
                if(ID.getText().length() == 9 && isNumeric(ID.getText())) {
                    newID = ID.getText();
                }

                String newLastName = null;
                if(isLetters(lastName.getText())){
                    newLastName = lastName.getText();
                }

                String newFirstName = null;
                if(isLetters(firstName.getText())){
                    newFirstName = firstName.getText();
                }

                String newMI = null;
                if(MI.getText().length() == 1 && isLetters(MI.getText())){
                    newMI = MI.getText();
                }

                String newAddress = address.getText();

                String newCity = null;
                if(isLetters(city.getText())){
                    newCity = city.getText();
                }

                String newState = null;
                if(state.getText().length() == 2 && isLetters(state.getText())){
                    newState = state.getText();
                }

                String newTelephone = null;
                if(telephone.getText().length() == 12){
                    Character check1 = telephone.getText().charAt(3);
                    Character check2 = telephone.getText().charAt(7);
                    if( isANumber(telephone.getText(), 0) && isANumber(telephone.getText(), 1) && isANumber(telephone.getText(), 2) && isANumber(telephone.getText(), 4) && isANumber(telephone.getText(), 5) && isANumber(telephone.getText(), 6) && isANumber(telephone.getText(), 8) && isANumber(telephone.getText(), 9) && isANumber(telephone.getText(), 10) && isANumber(telephone.getText(), 11) ){
                        if(check1.equals('-') && check2.equals('-')){
                            newTelephone = telephone.getText();
                        }
                    }
                }

                String query = "INSERT INTO `stafftable`.`stafftable` (`ID`, `Last Name`, `First Name`, `Middle Initial`, `Address`, `City`, `State`, `Telephone`) VALUES ('" + newID + "', '" + newLastName + "', '" + newFirstName + "', '" + newMI + "', '" + newAddress + "', '" + newCity + "', '" + newState + "', '" + newTelephone + "');";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                if(newID != null && newLastName != null && newFirstName != null && newMI != null && newAddress != null && newCity != null && newState != null && newTelephone != null){
                    preparedStmt.executeUpdate();
                    textbox.setText("Data inserted successfully.");
                }else{
                    textbox.setText("Error: One or more fields were inputted incorrectly. Try Again.");
                }

                connection.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        });

        updateButton.setOnAction(value ->  {
            String url = "jdbc:mysql://127.0.0.1:3306/stafftable";
            String username = "root";
            String password = "Pa$$word";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);

                String newID = null;
                if(ID.getText().length() == 9 && isNumeric(ID.getText())) {
                    newID = ID.getText();
                }

                String newLastName = null;
                if(isLetters(lastName.getText())){
                    newLastName = lastName.getText();
                }

                String newFirstName = null;
                if(isLetters(firstName.getText())){
                    newFirstName = firstName.getText();
                }

                String newMI = null;
                if(MI.getText().length() == 1 && isLetters(MI.getText())){
                    newMI = MI.getText();
                }

                String newAddress = address.getText();

                String newCity = null;
                if(isLetters(city.getText())){
                    newCity = city.getText();
                }

                String newState = null;
                if(state.getText().length() == 2 && isLetters(state.getText())){
                    newState = state.getText();
                }

                String newTelephone = null;
                if(telephone.getText().length() == 12){
                    Character check1 = telephone.getText().charAt(3);
                    Character check2 = telephone.getText().charAt(7);
                    if( isANumber(telephone.getText(), 0) && isANumber(telephone.getText(), 1) && isANumber(telephone.getText(), 2) && isANumber(telephone.getText(), 4) && isANumber(telephone.getText(), 5) && isANumber(telephone.getText(), 6) && isANumber(telephone.getText(), 8) && isANumber(telephone.getText(), 9) && isANumber(telephone.getText(), 10) && isANumber(telephone.getText(), 11) ){
                        if(check1.equals('-') && check2.equals('-')){
                            newTelephone = telephone.getText();
                        }
                    }
                }

                String query1 = "DELETE FROM `stafftable`.`stafftable` WHERE (`ID` = '" + newID + "')";
                String query2 = "INSERT INTO `stafftable`.`stafftable` (`ID`, `Last Name`, `First Name`, `Middle Initial`, `Address`, `City`, `State`, `Telephone`) VALUES ('" + newID + "', '" + newLastName + "', '" + newFirstName + "', '" + newMI + "', '" + newAddress + "', '" + newCity + "', '" + newState + "', '" + newTelephone + "');";

                PreparedStatement preparedStmt1 = connection.prepareStatement(query1);
                PreparedStatement preparedStmt2 = connection.prepareStatement(query2);
                if(newID != null && newLastName != null && newFirstName != null && newMI != null && newAddress != null && newCity != null && newState != null && newTelephone != null){
                    preparedStmt1.executeUpdate();
                    preparedStmt2.executeUpdate();
                    textbox.setText("Data updated successfully.");
                }else{
                    textbox.setText("Error: One or more fields were inputted incorrectly. Try Again.");
                }

                connection.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        });

        clearButton.setOnAction(value ->  {
            ID.clear();
            lastName.clear();
            firstName.clear();
            MI.clear();
            address.clear();
            city.clear();
            state.clear();
            telephone.clear();
            textbox.setText("All fields have been cleared.");

        });

        BorderPane pane = new BorderPane();
        pane.setTop(grid);
        pane.setCenter(textbox);
        pane.setBottom(buttons);

        Scene scene = new Scene(pane, 400, 250);
        primaryStage.setTitle("Staff Table");

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isLetters(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    public boolean isANumber(String str, int index){
        char ch = str.charAt(index);
        if(Character.isLetter(ch)){
            return false;
        }else{
            return true;
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}