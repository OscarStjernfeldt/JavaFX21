package se.iths.shapes.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.shapes.Shape;

public final class Square extends Shape {

    public Square(Color color, double x, double y, double size) {
        super(color, x, y, size);

    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(getColor());
        graphicsContext.fillRect(getX() - getSize() / 2, getY() - getSize() / 2, getSize(), getSize());
    }

    public boolean isInside(double x, double y) {
        return false;
    }

    @Override
    public String toSvg() {
        return "";
    }
}
