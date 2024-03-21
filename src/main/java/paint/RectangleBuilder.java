package paint;

import javafx.scene.shape.Shape;

/**
 * @see FigureBuilder
 */
public class RectangleBuilder extends FigureBuilder {

    @Override
    public Shape buildFigure(Object object) {
        return new MyRectangle((MyRectangle) object);
    }

}
