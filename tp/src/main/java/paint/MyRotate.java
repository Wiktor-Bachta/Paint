package paint;

import java.io.Serializable;
import javafx.scene.transform.Rotate;

//! Klasa rotate potrzebna do wykorzystania serialize
public class MyRotate extends Rotate implements Serializable {
    public MyRotate(double angle, double pivotX, double pivotY) {
        super(angle, pivotX, pivotY);
    }
}
