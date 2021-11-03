package se.iths.shapes.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.shapes.Shape;

public final class Square extends Shape {


    private double size;

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Square(Color color, double x, double y, double size) {
        super(color, x, y);
        this.size = size;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(getColor());
        graphicsContext.fillRect(getX() - size / 2, getY() - size / 2, size, size);
    }

    public boolean isInside(double x, double y) {
        return false;
    }
}
