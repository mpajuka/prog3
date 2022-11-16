package fi.tuni.prog3.calc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
        
        Label labelOp1 = new Label("First operand:");
        grid.add(labelOp1, 0, 1);
        
        Label labelOp2 = new Label("Second operand:");
        grid.add(labelOp2, 0, 2);
        
        Label labelRes = new Label("Result:");
        grid.add(labelRes, 0, 4);
        
        TextField fieldOp1 = new TextField();
        grid.add(fieldOp1, 1, 1);
        
        TextField fieldOp2 = new TextField();
        grid.add(fieldOp2, 1, 2);
        
        TextField fieldRes = new TextField();
        fieldRes.setBackground(new Background(new BackgroundFill(Color.WHITE, 
                null, null)));
        grid.add(fieldRes, 1, 4);
        
        HBox hbBtn = new HBox(10);
        grid.add(hbBtn, 1, 3);
        
        Button btnAdd = new Button("Add");
        hbBtn.getChildren().add(btnAdd);
        
        Button btnSub = new Button("Subtract");
        hbBtn.getChildren().add(btnSub);
        
        Button btnMul = new Button("Multiply");
        hbBtn.getChildren().add(btnMul);
        
        Button btnDiv = new Button("Divide");
        hbBtn.getChildren().add(btnDiv);
        
        
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent e) {
                var a = Double.parseDouble(fieldOp1.getText());
                var b = Double.parseDouble(fieldOp2.getText());
                var r = a + b;
                fieldRes.setText(String.format("%.1f", r));
            }
        });
        
        btnSub.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent e) {
                var a = Double.parseDouble(fieldOp1.getText());
                var b = Double.parseDouble(fieldOp2.getText());
                var r = a - b;
                fieldRes.setText(String.format("%.1f", r));
            }
        });
        
        btnMul.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent e) {
                var a = Double.parseDouble(fieldOp1.getText());
                var b = Double.parseDouble(fieldOp2.getText());
                var r = a * b;
                fieldRes.setText(String.format("%.2f", r));
            }
        });
        
        btnDiv.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent e) {
                var a = Double.parseDouble(fieldOp1.getText());
                var b = Double.parseDouble(fieldOp2.getText());
                var r = a / b;
                fieldRes.setText(String.format("%.1f", r));
            }
        });
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}