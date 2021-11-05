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
    @Override
    public boolean isInside(double x, double y) {
        return x >= this.x - size / 2 &&
                x <= this.x + size / 2 &&
                y >= this.y - size / 2 &&
                y <= this.y + size / 2;
    }
    @Override
    public String toSvg() {
        return "<rect x=\"" + (getX() - size / 2) + "\" y=\"" + (getY() - size / 2) + "\" width=\"" + size + "\" height=\"" + size + "\" fill=\"#" + getColor().toString().substring(2) + "\" />";
    }
}
