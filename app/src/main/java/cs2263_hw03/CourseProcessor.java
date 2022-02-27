package cs2263_hw03;

/*
 * MIT License
 *
 * Copyright (c) 2022 Savannah Webb
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import com.google.gson.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.lang.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Holds the layout for the CourseProcessor and allows the addition of new classes.
 * @author Savannah Webb
 */

public class CourseProcessor extends Application {
    @Override
    /**
     * Allows the user to add classes, save classes, load classes, and exit with a popup verification.
     */
    public void start (Stage stage) throws IOException {
        stage.setTitle("Course Finder");

        // Departments ComboBox
        ComboBox deptComboBox = new ComboBox(FXCollections.observableArrayList(Course.getDepartmentsArray()));
        deptComboBox.setPromptText("Department");
        deptComboBox.setEditable(true);

        TextField courseName = new TextField();
        courseName.setPromptText("Course Name");

        TextField courseNumber = new TextField();
        courseNumber.setPromptText("Course Number");

        TextField numCredits = new TextField();
        numCredits.setPromptText("Number of Credits");

        // Buttons
        Button enterButton = new Button("Enter");

        Button saveButton = new Button("Save");

        Button loadButton = new Button("Load");

        Button exitButton = new Button("Exit");

        // Top Options
        HBox topEntry = new HBox(deptComboBox, courseName, courseNumber, numCredits, enterButton);
        HBox.setMargin(enterButton, new Insets(0, 0, 0, 25));
        HBox.setMargin(deptComboBox, new Insets(0, 10, 0, 0));
        HBox.setMargin(courseName, new Insets(0, 10, 0, 0));
        HBox.setMargin(courseNumber, new Insets(0, 10, 0, 0));
        HBox.setMargin(numCredits, new Insets(0, 10, 0, 0));
        topEntry.setPadding(new Insets(10, 10, 10, 10));


        // Middle Table
        TableView table = new TableView();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        TableColumn<Course, String> column1 = new TableColumn<>("Department");
        column1.setCellValueFactory(new PropertyValueFactory<>("Department"));

        TableColumn<Course, String> column5 = new TableColumn<>("Code");
        column5.setCellValueFactory(new PropertyValueFactory<>("deptCode"));

        TableColumn<Course, String> column2 = new TableColumn<>("Course Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("courseName"));

        TableColumn<Course, String> column3 = new TableColumn<>("Course Number");
        column3.setCellValueFactory(new PropertyValueFactory<>("courseNumber"));

        TableColumn<Course, String> column4 = new TableColumn<>("Number of Credits");
        column4.setCellValueFactory(new PropertyValueFactory<>("courseCredits"));

        table.getColumns().add(column1);
        table.getColumns().add(column5);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.getColumns().add(column4);

        VBox tableVbox = new VBox(table);

        table.setPadding(new Insets(10, 10, 10, 10));
        tableVbox.spacingProperty().set(10);
        tableVbox.setMinSize(400, 300);
        tableVbox.setPadding(new Insets(10, 10, 10, 10));
        tableVbox.setAlignment(Pos.CENTER);

        // Bottom Buttons
        HBox bottomButtons = new HBox(saveButton, loadButton);
        bottomButtons.setAlignment(Pos.CENTER);
        bottomButtons.setSpacing(10);
        bottomButtons.setPadding(new Insets(5, 0, 5, 0));

        VBox exitBox = new VBox(exitButton);
        exitBox.setAlignment(Pos.CENTER);

        // Layout Holder
        VBox layout = new VBox(topEntry, tableVbox, bottomButtons, exitBox);
        layout.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // Entering courses
        enterButton.setOnAction((event -> {
            if (courseName.getText().isEmpty() || courseNumber.getText().isEmpty() || deptComboBox.getSelectionModel().getSelectedItem().toString().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("All fields must be filled in. Please try again.");
                a.showAndWait().filter(r -> r == ButtonType.OK).ifPresent(r -> a.close());
            } else if (courseName.getText().matches("\\d") || deptComboBox.getSelectionModel().getSelectedItem().toString().matches("\\d")) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Department and course name cannot be numbers. Please try again.");
                a.showAndWait().filter(r -> r == ButtonType.OK).ifPresent(r -> a.close());
            } else if (courseNumber.getText().matches("\\D") || numCredits.getText().matches("\\D")) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Course number and number of credits must be numbers only. Please try again.");
                a.showAndWait().filter(r -> r == ButtonType.OK).ifPresent(r -> a.close());
           } else {
            String selectedDepartment = deptComboBox.getSelectionModel().getSelectedItem().toString();
            String deptCode = Course.courseCode(selectedDepartment);
            String enteredCourse = courseName.getText();
            String enteredCourseNum = courseNumber.getText();
            String enteredNumCredits = numCredits.getText();


            table.getItems().add(new Course(selectedDepartment, deptCode, enteredCourse, enteredCourseNum, enteredNumCredits));
            }

        }));

        saveButton.setOnAction(event -> {
            ObservableList tableList = table.getItems();
            String json = new Gson().toJson(tableList);

            Path currentWorkingDir = Paths.get("").toAbsolutePath();
            try {
                FileWriter writer = new FileWriter("my-courses.JSON");
                writer.write(json);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //System.out.println(currentWorkingDir + "\n" + json);
        });

        loadButton.setOnAction(event -> {
            FileChooser fileLoad = new FileChooser();
            fileLoad.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON file", "*.JSON"));
            File selectedFile = fileLoad.showOpenDialog(stage);
            System.out.println(selectedFile);

        });

        exitButton.setOnAction((event -> {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm Close");
            a.setContentText("Are you sure you want to exit?");
            a.showAndWait().filter(r -> r == ButtonType.OK).ifPresent(r -> stage.close());
        }));

        // Scene
        Scene scene = new Scene(layout);
        stage.setResizable(true);
        stage.sizeToScene();
        stage.setScene(scene);
        stage.show();

    }



    public static void main(String[] args) {
        Application.launch(args);
    }

}
