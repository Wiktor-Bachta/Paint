package paint;

import javafx.scene.shape.Shape;

//! Klasa pomagająca wczytywać figury
public abstract class FigureBuilder {
    public abstract Shape buildFigure(Object object);
}
