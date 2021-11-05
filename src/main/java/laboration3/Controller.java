package laboration3;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.iths.shapes.Shape;

import java.io.File;
import java.util.Optional;


public class Controller {

    private Stage stage;
    private Model model;
    private ObjectProperty<Optional<Shape>> selectedShape = new SimpleObjectProperty<>(Optional.empty());

    @FXML
    public Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider sizeSlider;

    @FXML
    private CheckBox selectBox;

    @FXML
    private ChoiceBox<se.iths.shapes.shapes.Shapes> choiceBox;

    public Controller() {

    }

    public Controller (Model model) {
        this.model = model;
    }

    public void initialize() {
        model = new Model();

        choiceBox.getItems().setAll(se.iths.shapes.shapes.Shapes.values());
        choiceBox.valueProperty().bindBidirectional(model.shapeOptionProperty());
        selectBox.selectedProperty().bindBidirectional(model.selectModeProperty());
        sizeSlider.valueProperty().bindBidirectional(model.sizeProperty());
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
    }

    private void draw() {
        var graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (var shape : model.shapes) {
            shape.draw(graphicsContext);
        }
    }

    public void canvasClicked(MouseEvent event) {
        if(!selectBox.isSelected())
        model.addShape(event.getX(),event.getY(),model.getShapeOption());
        else
            model.shapes.stream()
                    .filter(shape -> shape.isInside(event.getX(), event.getY()))
                    .findFirst().ifPresent(shape -> model.changeColor(shape, model.getColor()));

        draw();
    }

    public void undo(){
        model.undo();
        draw();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void save(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Svg file", "*.svg"));
        File file = fileChooser.showSaveDialog(stage);
    }

    public void exit(){
        stage.close();
    }
}