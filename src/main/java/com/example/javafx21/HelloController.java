package com.example.javafx21;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import javafx.embed.swing.SwingFXUtils;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class HelloController {

    Model model;

    public TextField textField1;

    public Canvas canvas;
    @FXML
    private Label welcomeText;
    @FXML
    private CheckBox checkBox1;
    @FXML
    private ListView<String> listView1;
    @FXML
    private ColorPicker colorPicker;

    public HelloController() {
    }

    public HelloController(Model model) {
        this.model = model;
    }

    public void initialize() {
        model = new Model();
        model.textProperty().bindBidirectional(textField1.textProperty());
        welcomeText.textProperty().bind(model.textProperty());

        textField1.disableProperty().bind(checkBox1.selectedProperty().not());
        checkBox1.selectedProperty().bindBidirectional(model.inColorProperty());
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());

        model.observableList.add("Noll");
        model.observableList.add("Ett");
        model.observableList.add("Två");
        model.observableList.add("Tre");

        listView1.setItems( model.observableList);
        listView1.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> ov,
                                        String old_val, String new_val) {
                        model.setText(new_val);
                    }
                });
        canvas.widthProperty().addListener(observable -> draw());
        canvas.heightProperty().addListener(observable -> draw());
    }

    @FXML
    protected void onHelloButtonClick() {

    }

    private void draw() {
       var gc = canvas.getGraphicsContext2D();
       gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
       gc.strokeLine(0,0,canvas.getWidth(),canvas.getHeight());
    }



    public void mouseClicked(MouseEvent mouseEvent) {
        model.setText("");
        System.out.println(mouseEvent.getX() + ":" + mouseEvent.getY());
        System.out.println(mouseEvent.getSceneX() + ":" + mouseEvent.getSceneY());
        System.out.println(mouseEvent.getScreenX() + ":" + mouseEvent.getScreenY());
    }


    public void canvasClicked(MouseEvent event) {
        System.out.println("Clicked on canvas");
        var context = canvas.getGraphicsContext2D();
        if(model.isInColor())
            context.setFill(model.getColor());
        context.fillOval(event.getX(),event.getY(),25,25);
        context.setFill(Color.BLACK);
    }

}