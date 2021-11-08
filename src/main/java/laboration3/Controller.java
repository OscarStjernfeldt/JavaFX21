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
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Controller {

    private Stage stage;
    private Model model;
    private final ObjectProperty<Optional<Shape>> selectedShape = new SimpleObjectProperty<>(Optional.empty());

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
                    .findFirst().ifPresent(shape -> { model.changeColor(shape, model.getColor()); model.changeSize(shape, model.getSize());});
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
        if(file == null)
            return;
        try {
            Files.write(file.toPath(),textReader());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> textReader() {
        List<String> shapeList = new ArrayList<>();
        shapeList.add("<svg width=\"" + getCanvas().getWidth() + "\" height=\"" +
                getCanvas().getHeight() + "\" xmlns=\"http://www.w3.org/2000/svg\" >");
        model.getShapes().forEach(shape -> shapeList.add(shape.toSvg()));
        shapeList.add("</svg>");
        return shapeList;
    }

    public void exit(){
        stage.close();
    }
}