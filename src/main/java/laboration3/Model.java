package laboration3;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import se.iths.shapes.Shape;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.MouseEvent;
import se.iths.shapes.Shapes;

public class Model {

    private final StringProperty text;
    private final IntegerProperty size;
    private final BooleanProperty inColor;
    private final ObjectProperty<Color> color;

    ObservableList<String> observableList =
            FXCollections.observableArrayList();

    List<Shape> shapes = new ArrayList<>();

    double[] xcoords = new double[3];
    double[] ycoords = new double[3];
    int clickCounter = 0;

    public Model() {
        this.text = new SimpleStringProperty("");
        this.inColor = new SimpleBooleanProperty();
        this.color = new SimpleObjectProperty<>(Color.BLACK);
        this.size = new SimpleIntegerProperty(5);
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

    private void addCircle(MouseEvent event) {
        shapes.add(Shapes.circleOf(event.getX(), event.getY(),
                ObjectProperty.getValue(), size.getValue()));
    }

    private void addSquare(MouseEvent event) {
        shapes.add(Shapes.squareOf(event.getX(), event.getY(),
                ObjectProperty.getValue(), size.getValue()));
    }
}