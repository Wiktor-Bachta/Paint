package paint;

import javafx.scene.shape.Shape;

/**
 * @see FigureBuilder
 */
public class PolygonBuilder extends FigureBuilder {

    @Override
    public Shape buildFigure(Object object) {
        return new MyPolygon((MyPolygon) object);
    }

}
