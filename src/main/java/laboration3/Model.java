package laboration3;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import se.iths.shapes.Shape;
import se.iths.shapes.shapes.Shapes;

import java.util.ArrayDeque;
import java.util.Deque;

public class Model {

    private Command command = () -> {};
    private final StringProperty text;
    private final IntegerProperty size;
    private final ObjectProperty<Color> color;
    private final BooleanProperty selectMode;
    private final ObjectProperty<Shapes> shapeOption;
    private final ObjectProperty<Shape> selectedShape;
    static Deque<Command> deque = new ArrayDeque<>();

    ObservableList<Shape> shapes = FXCollections.observableArrayList();

    public Model() {

        this.text = new SimpleStringProperty("");
        this.color = new SimpleObjectProperty<>(Color.BLACK);
        this.size = new SimpleIntegerProperty(25);
        selectMode = new SimpleBooleanProperty(false);
        shapeOption = new SimpleObjectProperty<>(Shapes.CIRCLE);
        selectedShape = null;
    }

    public boolean isSelectMode() {
        return selectMode.get();
    }

    public BooleanProperty selectModeProperty() {
        return selectMode;
    }

    public void setSelectMode(boolean selectMode) {
        this.selectMode.set(selectMode);
    }

    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public int getSize() {
        return size.get();
    }

    public IntegerProperty sizeProperty() {
        return size;
    }

    public void setSize(int size) {
        this.size.set(size);
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

    public Shapes getShapeOption() {
        return shapeOption.get();
    }

    public ObjectProperty<Shapes> shapeOptionProperty() {
        return shapeOption;
    }

    public void setShapeOption(Shapes shapeOption) {
        this.shapeOption.set(shapeOption);
    }

    public ObservableList<Shape> getShapes() {
        return shapes;
    }

    public void addShape(double x, double y, Shapes shape) {
        switch (shape) {
            case CIRCLE -> addCircle(x, y);
            case SQUARE -> addSquare(x, y);
        }

    }
    private void addCircle(double x, double y) {
        add(se.iths.shapes.Shapes.circleOf(x, y, getSize(), getColor()));
    }

    private void addSquare(double x, double y) {
        add(se.iths.shapes.Shapes.squareOf(x, y, getSize(), getColor()));
    }

    public void add(Shape shape) {
        shapes.add(shape);
        deque.addLast(() -> shapes.remove(shape));

    }

    public void changeColor(Shape shape, Color color) {
        Color previousColor = shape.getColor();
        shape.setColor(color);
        deque.addLast(() -> shape.setColor(previousColor));
    }

    public void changeSize(Shape shape, double size) {
        double previousSize = shape.getSize();
        shape.setSize(size);
        deque.addLast(() -> shape.setSize(previousSize));
    }

    public void undo() {
        if( deque.isEmpty() )
            return;
        Command command = deque.removeLast();
        command.execute();
    }

}