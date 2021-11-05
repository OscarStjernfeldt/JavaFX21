package se.iths.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.shapes.shapes.Circle;
import se.iths.shapes.shapes.Square;

import java.util.Objects;

public abstract sealed class Shape permits Circle, Square{
    public double size;
    public Color color;
    public double x;
    public double y;

    public Shape(Color color, double x, double y, double size) {
        this.size = size;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public abstract void draw (GraphicsContext graphicsContext);

    public abstract boolean isInside(double x, double y);

    public abstract String toSvg();

    public Color getColor() {
        return color;
    }

    public Shape setColor(Color color) {
        this.color = color;
        return this;
    }

    public double getX() {
        return x;
    }

    public Shape setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public Shape setY(double y) {
        this.y = y;
        return this;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Double.compare(shape.size, size) == 0 && Double.compare(shape.x, x) == 0 && Double.compare(shape.y, y) == 0 && Objects.equals(color, shape.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, color, x, y);
    }
}