package paint;

import javafx.scene.shape.Polygon;
import java.io.Serializable;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

//! Klasa wielokąta
public class MyPolygon extends Polygon implements Serializable {

    /** obrót */
    MyRotate rotate = new MyRotate(0, 450, 350);
    /** skalowanie */
    MyScale scale = new MyScale(1, 1);
    /** wartość obrotu */
    double rotation;
    /** punkty wielokąta */
    double[] points;
    /** kolor wielokąta */
    String paint;
    /** wartość skalowania */
    double scalar;

    /**
     * @param points = punkty wielokąta
     */
    public MyPolygon(double[] points) {
        super(points);
        setOnMouseClicked(new MyPolygonEventHandler());
        setOnMouseDragged(new MyPolygonEventHandler());
        setOnScroll(new MyPolygonScrollHandler());
        setOnContextMenuRequested(new MyShapeContextMenuEventHandler());
        getTransforms().addAll(rotate, scale);
        setStrokeWidth(3);
        setStroke(Color.BLACK);
        setFill(Edytor.colorpicker.getValue());
        this.points = points;
        rotation = 0;
        scalar = 0;
    }

    // ! Konstruktor do wczytywania z pliku
    /**
     * @param polygon = wczytywana figura
     */
    public MyPolygon(MyPolygon polygon) {
        super(polygon.points);
        rotation = polygon.rotation;
        scalar = polygon.scalar;
        paint = polygon.paint;
        points = polygon.points;
        rotate.setAngle(rotation);
        scale.setX(scalar);
        scale.setY(scalar);
        setFill(Color.valueOf(paint));
        getTransforms().addAll(rotate, scale);
        setOnMouseClicked(new MyPolygonEventHandler());
        setOnMouseDragged(new MyPolygonEventHandler());
        setOnScroll(new MyPolygonScrollHandler());
        setOnContextMenuRequested(new MyShapeContextMenuEventHandler());
        setStrokeWidth(3);
        setStroke(Color.BLACK);
    }

    // ! Metoda sprawdza czy najechaliśmy na figurę
    /**
     * @param x = wpółrzędna x myszy
     * @param y = wpółrzędna y myszy
     * @return true, jeśli mysz w figurze
     */
    public boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(x, y);
    }

    // ! Zmiana wspolrzednej x
    /**
     * @param x = wartość zmiany
     */
    public void addX(double x) {
        for (int i = 0; i < getPoints().size(); i += 2) {
            getPoints().set(i, getPoints().get(i) + x);
        }
    }

    // ! Zmiana wspolrzednej y
    /**
     * @param y = wartość zmiany
     */
    public void addY(double y) {
        for (int i = 1; i < getPoints().size(); i += 2) {
            getPoints().set(i, getPoints().get(i) + y);
        }
    }

    // ! Obracanie
    /**
     * @param v = wartość obrotu
     */
    public void rotate(double v) {
        rotate.setAngle(rotate.getAngle() + v);
    }

    // ! Zmiana rozmiaru Wielokata
    /**
     * @param v = wartość zmiany
     */
    public void scale(double v) {
        scale.setX(scale.getX() + v);
        scale.setY(scale.getY() + v);
    }

    // ! Zapisywanie zmian przed zapisaniem figury
    private void update() {
        for (int i = 0; i < points.length; i++) {
            points[i] = getPoints().get(i);
        }
        paint = getFill().toString();
        rotation = rotate.getAngle();
        scalar = scale.getX();
    }

    // Implementacja przesuwania
    class MyPolygonEventHandler implements EventHandler<MouseEvent> {

        MyPolygon polygon;
        private double x;
        private double y;

        private void doMove(MouseEvent event) {

            double dx = event.getX() - x;
            double dy = event.getY() - y;

            // Jesli nacisnelismy na elipse
            if (polygon.isHit(x, y)) {
                polygon.addX(dx);
                polygon.addY(dy);
            }
            x += dx;
            y += dy;
            update();
        }

        @Override
        public void handle(MouseEvent event) {

            polygon = (MyPolygon) event.getSource();
            if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                x = event.getX();
                y = event.getY();
            }
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                doMove(event);
            }

        }
    }

    // Implementacja scrollowania
    class MyPolygonScrollHandler implements EventHandler<ScrollEvent> {

        MyPolygon polygon;

        private void doScale(ScrollEvent e) {

            double x = e.getX();
            double y = e.getY();

            // Jesli nacisnelismy na elipse
            if (polygon.isHit(x, y)) {
                polygon.scale(e.getDeltaY() * 0.01);
            }
        }

        @Override
        public void handle(ScrollEvent e) {

            polygon = (MyPolygon) e.getSource();
            if (e.getEventType() == ScrollEvent.SCROLL) {
                if (Edytor.checkbox.isSelected()) {
                    polygon.rotate(e.getDeltaY() * 0.2);
                } else {
                    doScale(e);
                }
            }
        }
    }
}
