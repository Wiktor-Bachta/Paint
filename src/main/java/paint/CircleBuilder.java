package paint;

import javafx.scene.shape.Shape;

/**
 * @see FigureBuilder
 */
public class CircleBuilder extends FigureBuilder {

    @Override
    public Shape buildFigure(Object object) {
        return new MyCircle((MyCircle) object);
    }
    
}
