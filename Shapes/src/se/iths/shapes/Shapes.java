package se.iths.shapes;

import javafx.scene.paint.Color;
import se.iths.shapes.shapes.Circle;
import se.iths.shapes.shapes.Square;

public class Shapes {

    public static Shape circleOf(double x, double y, double radius, Color color) {
        return new Circle(color, x, y, radius);
    }

    public static Shape squareOf(double x, double y, double size, Color color) {
        return new Square(color, x, y, size);
    }
}
