package com.example.javafx21;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private final StringProperty text;
    private final BooleanProperty inColor;
    private final ObjectProperty<Color> color;

    ObservableList<String> observableList =
            FXCollections.observableArrayList();

    List<Shape> shapes = new ArrayList<>();

    public Model() {
        this.text = new SimpleStringProperty();
        this.inColor = new SimpleBooleanProperty();
        this.color = new SimpleObjectProperty<>();
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public boolean isInColor() {
        return inColor.get();
    }

    public BooleanProperty inColorProperty() {
        return inColor;
    }

    public void setInColor(boolean inColor) {
        this.inColor.set(inColor);
    }

    public String getText() {
        return text.getValue();
    }

    public void setText(String text) {
        this.text.setValue(text);
    }

    public StringProperty textProperty() {
        return text;
    }

}