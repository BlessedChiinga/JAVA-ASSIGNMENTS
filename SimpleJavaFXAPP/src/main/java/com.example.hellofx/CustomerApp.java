package com.example.hellofx;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerApp extends Application {

    // Customer model
    public static class Customer {
        private final String name;
        private final String province;

        public Customer(String name, String province) {
            this.name = name;
            this.province = province;
        }

        public String getName() {
            return name;
        }

        public String getProvince() {
            return province;
        }
    }

    private final ObservableList<Customer> customers =
            FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        Label nameLabel = new Label("_Name:");
        nameLabel.setMnemonicParsing(true);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter customer name");
        nameField.setAccessibleText("Customer name");
        nameLabel.setLabelFor(nameField);

        Label provinceLabel = new Label("_Province:");
        provinceLabel.setMnemonicParsing(true);

        ComboBox<String> provinceBox = new ComboBox<>(
                FXCollections.observableArrayList(
                        "Eastern",
                        "Copperbelt",
                        "Central",
                        "Lusaka",
                        "Southern",
                        "North West",
                        "Northern",
                        "Western"
                )
        );
        provinceBox.setPromptText("Select a province");
        provinceBox.setMaxWidth(Double.MAX_VALUE);
        provinceBox.setAccessibleText("Customer province");
        provinceLabel.setLabelFor(provinceBox);

        Button addButton = new Button("_Add Customer");
        addButton.setMnemonicParsing(true);
        addButton.setDefaultButton(true);

        Button deleteButton = new Button("_Delete Selected");
        deleteButton.setMnemonicParsing(true);

        TableView<Customer> table = new TableView<>(customers);
        table.setAccessibleText("Customer list");

        TableColumn<Customer, String> nameColumn =
                new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName()));

        TableColumn<Customer, String> provinceColumn =
                new TableColumn<>("Province");
        provinceColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getProvince()));

        nameColumn.setPrefWidth(220);
        provinceColumn.setPrefWidth(220);
        table.getColumns().addAll(nameColumn, provinceColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        addButton.setOnAction(event -> {
            String name = nameField.getText().trim();
            String province = provinceBox.getValue();

            if (name.isEmpty()) {
                showError("Please enter a customer name.");
                nameField.requestFocus();
                return;
            }

            if (province == null) {
                showError("Please select a province.");
                provinceBox.requestFocus();
                return;
            }

            Customer customer = new Customer(name, province);
            customers.add(customer);
            table.getSelectionModel().select(customer);

            nameField.clear();
            provinceBox.getSelectionModel().clearSelection();
            nameField.requestFocus();
        });

        deleteButton.setOnAction(event -> {
            Customer selected = table.getSelectionModel().getSelectedItem();

            if (selected == null) {
                showError("Select a customer in the table first.");
                table.requestFocus();
                return;
            }

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirm deletion");
            confirmation.setHeaderText("Delete " + selected.getName() + "?");
            confirmation.setContentText(
                    "This customer will be removed from the list."
            );

            confirmation.showAndWait().ifPresent(response -> {
                if (response == javafx.scene.control.ButtonType.OK) {
                    customers.remove(selected);
                }
            });
        });

        // Press Delete while a table row is selected to start deletion.
        table.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                deleteButton.fire();
            }
        });

        HBox buttons = new HBox(10, addButton, deleteButton);
        buttons.setAlignment(Pos.CENTER_LEFT);

        VBox form = new VBox(
                8,
                nameLabel, nameField,
                provinceLabel, provinceBox,
                buttons
        );

        VBox layout = new VBox(14, form, table);
        layout.setPadding(new Insets(18));
        VBox.setVgrow(table, javafx.scene.layout.Priority.ALWAYS);

        Scene scene = new Scene(layout, 520, 480);
        stage.setTitle("Customer Manager");
        stage.setScene(scene);
        stage.show();

        nameField.requestFocus();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid input");
        alert.setHeaderText("Please check your entry");
        alert.setContentText(message);
        alert.showAndWait();
    }
}