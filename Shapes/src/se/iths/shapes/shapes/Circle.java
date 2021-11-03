package se.iths.shapes.shapes;

import javafx.scene.canvas.GraphicsContext;
import se.iths.shapes.Shape;
import javafx.scene.paint.Color;

public final class Circle extends Shape {

    private double radius;

    public Circle(Color color, double x, double y, double radius) {
        super(color, x, y);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }


    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(this.getColor());
        graphicsContext.fillOval(getX()-radius, getY()-radius,2*radius,2*radius);
    }

    public boolean isInside(double x, double y) {
        double dx = x - getX();
        double dy = y - getY();

        double distanceFromCircleCenterSquared = dx * dx+ dy * dy;

        return distanceFromCircleCenterSquared < radius*radius;
    }
}
