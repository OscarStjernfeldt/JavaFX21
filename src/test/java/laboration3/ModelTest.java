package laboration3;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import se.iths.shapes.shapes.Circle;
import se.iths.shapes.shapes.Shapes;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    Model model = new Model();

    @Test
    void testIfUndoFunctionWorks(){
        model.addShape(0,0, Shapes.CIRCLE);
        model.undo();
        assertTrue(model.shapes.isEmpty());
    }

    @Test
    void testShouldVerifyIfShapeHasBeenAdded(){
        model.addShape(0,0,Shapes.CIRCLE);
        assertEquals(1,model.shapes.size());
        assertEquals(new Circle(Color.BLACK,0,0,25), model.shapes.get(0));
    }
}