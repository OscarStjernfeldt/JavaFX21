package laboration3;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import se.iths.shapes.Shape;
import se.iths.shapes.Shapes;

public class Controller {

    Model model;

    public Canvas canvas;
    @FXML

    private ColorPicker colorPicker;

    public Controller() {
    }

    public Controller (Model model) {
        this.model = model;
    }

    public void initialize() {
        model = new Model();

        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        canvas.widthProperty().addListener(observable -> draw());
        canvas.heightProperty().addListener(observable -> draw());
    }

    @FXML
    protected void onCircleButtonClicked() {
        model.setColor(Color.BLACK);
        model.setText("Circle button clicked");
    }

    @FXML
    protected void onSquareButtonClicked() {
        model.setColor(Color.BLACK);
        model.setText("Square button clicked");
    }

    @FXML
    protected void onTriangleButtonClicked() {
        model.setColor(Color.BLACK);
        model.setText("Triangle button clicked");
    }

    private void draw() {
        var gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (var shape : model.shapes) {
            shape.draw(gc);
        }
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        model.setText("");
        System.out.println(mouseEvent.getX() + ":" + mouseEvent.getY());
        System.out.println(mouseEvent.getSceneX() + ":" + mouseEvent.getSceneY());
        System.out.println(mouseEvent.getScreenX() + ":" + mouseEvent.getScreenY());
    }

    public void canvasClicked(MouseEvent event) {
        if( event.getButton().name().equals("PRIMARY"))
            model.shapes.add(Shapes.circleOf(event.getX(), event.getY(), 10.0, model.getColor()));
            //model.shapes.add(Shapes.rectangleOf(event.getX(), event.getY(), 10.0, model.getColor()));
        else if( event.getButton().name().equals("SECONDARY"))
        {
            model.shapes.stream()
                    .filter(shape -> shape.isInside(event.getX(), event.getY()))
                    .findFirst().ifPresent(shape -> shape.setColor(Color.RED));
        }
        draw();
    }
}