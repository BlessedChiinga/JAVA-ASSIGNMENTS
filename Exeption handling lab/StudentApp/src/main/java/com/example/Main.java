package com.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField studentIdField;
    private TextField fullNameField;
    private ComboBox<String> genderBox;
    private DatePicker dateOfBirthPicker;
    private TextField programmeField;
    private ComboBox<String> yearBox;
    private TextField emailField;
    private TextField phoneField;

    private TableView<Student> studentTable;

    private ObservableList<Student> studentList =
            FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        Label title = new Label("STUDENT REGISTRATION SYSTEM");
        title.setStyle(
                "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;"
        );

        Label studentIdLabel = new Label("Student ID:");
        studentIdField = new TextField();
        studentIdField.setPromptText("Enter student ID");

        Label fullNameLabel = new Label("Full Name:");
        fullNameField = new TextField();
        fullNameField.setPromptText("Enter full name");

        Label genderLabel = new Label("Gender:");
        genderBox = new ComboBox<>();
        genderBox.getItems().addAll(
                "Male",
                "Female"
        );
        genderBox.setPromptText("Select gender");

        Label dobLabel = new Label("Date of Birth:");
        dateOfBirthPicker = new DatePicker();

        Label programmeLabel = new Label("Programme:");
        programmeField = new TextField();
        programmeField.setPromptText("Enter programme");

        Label yearLabel = new Label("Year of Study:");
        yearBox = new ComboBox<>();
        yearBox.getItems().addAll(
                "Year 1",
                "Year 2",
                "Year 3",
                "Year 4",
                "Year 5"
        );
        yearBox.setPromptText("Select year");

        Label emailLabel = new Label("Email:");
        emailField = new TextField();
        emailField.setPromptText("Enter email address");

        Label phoneLabel = new Label("Phone:");
        phoneField = new TextField();
        phoneField.setPromptText("Enter phone number");

        GridPane form = new GridPane();

        form.setHgap(15);
        form.setVgap(15);
        form.setPadding(new Insets(20));

        form.add(studentIdLabel, 0, 0);
        form.add(studentIdField, 1, 0);

        form.add(fullNameLabel, 2, 0);
        form.add(fullNameField, 3, 0);

        form.add(genderLabel, 0, 1);
        form.add(genderBox, 1, 1);

        form.add(dobLabel, 2, 1);
        form.add(dateOfBirthPicker, 3, 1);

        form.add(programmeLabel, 0, 2);
        form.add(programmeField, 1, 2);

        form.add(yearLabel, 2, 2);
        form.add(yearBox, 3, 2);

        form.add(emailLabel, 0, 3);
        form.add(emailField, 1, 3);

        form.add(phoneLabel, 2, 3);
        form.add(phoneField, 3, 3);

        Button registerButton = new Button("REGISTER");
        Button clearButton = new Button("CLEAR");
        Button deleteButton = new Button("DELETE SELECTED");

        registerButton.setOnAction(event -> registerStudent());

        clearButton.setOnAction(event -> clearFields());

        deleteButton.setOnAction(event -> deleteStudent());

        HBox buttons = new HBox(15);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(
                registerButton,
                clearButton,
                deleteButton
        );

        Label tableTitle = new Label("REGISTERED STUDENTS");
        tableTitle.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;"
        );

        studentTable = new TableView<>();

        TableColumn<Student, String> idColumn =
                new TableColumn<>("Student ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("studentId")
        );

        TableColumn<Student, String> nameColumn =
                new TableColumn<>("Full Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("fullName")
        );

        TableColumn<Student, String> genderColumn =
                new TableColumn<>("Gender");

        genderColumn.setCellValueFactory(
                new PropertyValueFactory<>("gender")
        );

        TableColumn<Student, String> dobColumn =
                new TableColumn<>("Date of Birth");

        dobColumn.setCellValueFactory(
                new PropertyValueFactory<>("dateOfBirth")
        );

        TableColumn<Student, String> programmeColumn =
                new TableColumn<>("Programme");

        programmeColumn.setCellValueFactory(
                new PropertyValueFactory<>("programme")
        );

        TableColumn<Student, String> yearColumn =
                new TableColumn<>("Year");

        yearColumn.setCellValueFactory(
                new PropertyValueFactory<>("year")
        );

        TableColumn<Student, String> emailColumn =
                new TableColumn<>("Email");

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        TableColumn<Student, String> phoneColumn =
                new TableColumn<>("Phone");

        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phone")
        );

        studentTable.getColumns().addAll(
                idColumn,
                nameColumn,
                genderColumn,
                dobColumn,
                programmeColumn,
                yearColumn,
                emailColumn,
                phoneColumn
        );

        studentTable.setItems(studentList);

        VBox mainLayout = new VBox(15);

        mainLayout.setPadding(new Insets(20));

        mainLayout.getChildren().addAll(
                title,
                form,
                buttons,
                tableTitle,
                studentTable
        );

        Scene scene = new Scene(
                mainLayout,
                1100,
                650
        );

        stage.setTitle("Student Registration System");
        stage.setScene(scene);
        stage.show();
    }

    private void registerStudent() {

        String studentId = studentIdField.getText().trim();
        String fullName = fullNameField.getText().trim();
        String gender = genderBox.getValue();

        String dateOfBirth = "";

        if (dateOfBirthPicker.getValue() != null) {
            dateOfBirth =
                    dateOfBirthPicker.getValue().toString();
        }

        String programme =
                programmeField.getText().trim();

        String year = yearBox.getValue();

        String email =
                emailField.getText().trim();

        String phone =
                phoneField.getText().trim();

        if (studentId.isEmpty() ||
                fullName.isEmpty() ||
                gender == null ||
                dateOfBirth.isEmpty() ||
                programme.isEmpty() ||
                year == null ||
                email.isEmpty() ||
                phone.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Registration Error",
                    "Please fill in all fields."
            );

            return;
        }

        if (!email.contains("@")) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Email",
                    "Please enter a valid email address."
            );

            return;
        }

        for (Student student : studentList) {

            if (student.getStudentId()
                    .equalsIgnoreCase(studentId)) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Duplicate Student ID",
                        "A student with this ID already exists."
                );

                return;
            }
        }

        Student student = new Student(
                studentId,
                fullName,
                gender,
                dateOfBirth,
                programme,
                year,
                email,
                phone
        );

        studentList.add(student);

        showAlert(
                Alert.AlertType.INFORMATION,
                "Registration Successful",
                "Student registered successfully!"
        );

        clearFields();
    }

    private void clearFields() {

        studentIdField.clear();
        fullNameField.clear();

        genderBox.setValue(null);

        dateOfBirthPicker.setValue(null);

        programmeField.clear();

        yearBox.setValue(null);

        emailField.clear();
        phoneField.clear();
    }

    private void deleteStudent() {

        Student selectedStudent =
                studentTable.getSelectionModel()
                        .getSelectedItem();

        if (selectedStudent == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "No Selection",
                    "Please select a student to delete."
            );

            return;
        }

        studentList.remove(selectedStudent);

        showAlert(
                Alert.AlertType.INFORMATION,
                "Student Deleted",
                "The selected student has been deleted."
        );
    }

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void main(String[] args) {

        launch();
    }
}