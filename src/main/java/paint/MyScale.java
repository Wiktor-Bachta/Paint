package paint;

import java.io.Serializable;
import javafx.scene.transform.Scale;

//! Klasa scale potrzebna do wykorzystania serialize
public class MyScale extends Scale implements Serializable {
    public MyScale(double x, double y) {
        super(x, y);
    }
}
