package se.iths.shapes.shapes;

public enum Shapes {
    CIRCLE("Circle"),
    SQUARE("Square");

    String name;

    Shapes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
