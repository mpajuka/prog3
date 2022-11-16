package fi.tuni.prog3.calc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class Calculator extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Calculator");
        
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 350, 125);
        
        Label first = new Label("First operand: ");
        grid.add(first, 0, 1);
        
        Label second = new Label("Second operand: ");
        grid.add(second, 0, 2);
        
        Label resultText = new Label("Result: ");
        grid.add(resultText, 0, 4);
        
        TextField inputFirst = new TextField();
        grid.add(inputFirst, 1, 1);
        
        TextField inputSecond = new TextField();
        grid.add(inputSecond, 1, 2);
        
        TextField resultField = new TextField();
        grid.add(resultField, 1, 4);
        
        HBox hbBtn = new HBox(10);
        grid.add(hbBtn, 1, 3);
        
        Button add = new Button("Add");
        hbBtn.getChildren().add(add);
        
        Button subtract = new Button("Subtract");
        hbBtn.getChildren().add(subtract);
        
        Button multiply = new Button("Multiply");
        hbBtn.getChildren().add(multiply);
        
        Button divide = new Button("Divide");
        hbBtn.getChildren().add(divide);
        
        
        add.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent e) {
                var a = Double.parseDouble(inputFirst.getText());
                var b = Double.parseDouble(inputSecond.getText());
                var r = a + b;
                resultField.setText(String.format("%.1f", r));
            }
        });
        
        subtract.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent e) {
                var a = Double.parseDouble(inputFirst.getText());
                var b = Double.parseDouble(inputSecond.getText());
                var r = a - b;
                resultField.setText(String.format("%.1f", r));
            }
        });
        
        multiply.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent e) {
                var a = Double.parseDouble(inputFirst.getText());
                var b = Double.parseDouble(inputSecond.getText());
                var r = a * b;
                resultField.setText(String.format("%.1f", r));
            }
        });
        
        divide.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent e) {
                var a = Double.parseDouble(inputFirst.getText());
                var b = Double.parseDouble(inputSecond.getText());
                var r = a / b;
                resultField.setText(String.format("%.1f", r));
            }
        });
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}