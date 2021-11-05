package se.iths.shapes.shapes;

import javafx.scene.canvas.GraphicsContext;
import se.iths.shapes.Shape;
import javafx.scene.paint.Color;

public final class Circle extends Shape {

    public Circle(Color color, double x, double y, double radius) {
        super(color, x, y, radius);
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(this.getColor());
        graphicsContext.fillOval(getX() - getSize() / 2, getY() - getSize() / 2, getSize(), getSize());
    }

    public boolean isInside(double x, double y) {
        double dx = x - getX();
        double dy = y - getY();

        double distanceFromCircleCenterSquared = dx * dx+ dy * dy;

        return distanceFromCircleCenterSquared < getSize()*getSize();
    }

    @Override
    public String toSvg() {
        return "<circle cx=\"" + getX() + "\" cy=\"" + getY() + "\" r=\"" + size / 2 + "\" fill=\"#" + getColor().toString().substring(2) + "\" />";
    }


}
