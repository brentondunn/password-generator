package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    Label errorLabel;
    Label passwordOutput;
    String passwordStr = "";

    @Override
    public void start(Stage primaryStage) throws Exception {

        // window set-up
        Stage window = primaryStage;
        window.setTitle("Password Generator");
        window.setWidth(750);
        window.setHeight(500);

        // input password length
        TextField passwordLength = new TextField();
        passwordLength.setPromptText("5-20");
        passwordLength.setStyle("-fx-background-radius: 0; -fx-background-color: #5e6472; -fx-text-fill: white");

        // displays error
        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 7pt;");

        // left side labels
        Label enterChar = new Label("Enter password length: ");
        enterChar.setStyle("-fx-font-size: 15;");
        Label uppercase = new Label("Uppercase");
        uppercase.setStyle("-fx-font-size: 15;");
        Label symbols = new Label("Symbols");
        symbols.setStyle("-fx-font-size: 15;");
        Label numbers = new Label("Numbers");
        numbers.setStyle("-fx-font-size: 15;");
        Label passwordLabel = new Label("New password: ");
        passwordLabel.setStyle("-fx-font-size: 15;");

        // checkboxes
        CheckBox wantUpperCase = new CheckBox();
        wantUpperCase.setStyle("-fx-selected-box-color: lime; -fx-box-color: red; -fx-mark-color: #5e6472;");
        CheckBox wantSymbols = new CheckBox();
        CheckBox wantNumbers = new CheckBox();

        // button to generate password
        Button generatePassword = new Button("Generate Password");
        generatePassword.setOnAction(e -> getUserInput(passwordLength, wantUpperCase, wantSymbols, wantNumbers));
        generatePassword.setStyle("-fx-background-radius: 0; -fx-background-color: #aed9e0;");

        // shows password
        passwordOutput = new Label();
        passwordOutput.setText("");

        // button to copy to clipboard
        Button copyPassword = new Button("Copy to Clipboard");
        copyPassword.setOnAction(e -> copyToClipboard());
        copyPassword.setStyle("-fx-background-radius: 0; -fx-background-color: #aed9e0;");

        // grid stuff
        GridPane grid = new GridPane();
        grid.setStyle("-fx-padding: 20pt");
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setVgap(20);
        grid.setHgap(10);
        grid.add(enterChar, 0, 0);
        grid.add(passwordLength, 1, 0);
        grid.add(errorLabel, 1, 1);
        grid.add(wantUpperCase, 1, 2);
        grid.add(uppercase, 0, 2);
        grid.add(symbols, 0,3);
        grid.add(wantSymbols, 1, 3);
        grid.add(numbers, 0, 4);
        grid.add(wantNumbers, 1, 4);
        grid.add(generatePassword, 1, 5);
        grid.add(passwordLabel, 0, 6);
        grid.add(passwordOutput, 1, 6);
        grid.add(copyPassword, 1, 7);

        // random end of code stuff
        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.show();
    }

    /**
     * @param pwLength
     * @param upperCase
     * @param symbols
     * @param numbers
     */
    public void getUserInput(TextField pwLength,CheckBox upperCase,CheckBox symbols, CheckBox numbers) {

        Boolean wantUpperCase = false;
        Boolean wantSymbols = false;
        Boolean wantNumbers = false;
        int strToInt = 0;

        try {
            strToInt = Integer.parseInt(pwLength.getText());
            errorLabel.setText("");
        } catch (NumberFormatException e) {
            errorLabel.setText("ERROR! Please enter a number between 5 and 20!");
            return;
        }
        if (strToInt < 5 || strToInt > 20) {
            errorLabel.setText("ERROR! Please enter a number between 5 and 20!");
            return;
        }

        if (upperCase.isSelected()) {
            wantUpperCase = true;
        }
        if (symbols.isSelected()) {
            wantSymbols = true;
        }
        if (numbers.isSelected()) {
            wantNumbers = true;
        }

        Password password = new Password(wantUpperCase, wantSymbols, wantNumbers, strToInt);
        passwordStr = password.returnPassword();
        passwordOutput.setText(passwordStr);
    }

    /**
     * copies password to clipboard
     */
    public void copyToClipboard() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(passwordStr);
        clipboard.setContent(content);
    }

    public static void main(String[] args) {
        launch(args);
    }
}