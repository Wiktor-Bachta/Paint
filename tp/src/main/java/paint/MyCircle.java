package paint;

import java.io.Serializable;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

//! Klasa koła
public class MyCircle extends Circle implements Serializable {

    /** obrót */
    MyRotate rotate = new MyRotate(0, 450, 350);
    /** wartość obrotu */
    double rotation;
    /** współrzędna x */
    double x;
    /** współrzędna y */
    double y;
    /** promień */
    double radius;
    /** kolor */
    String paint;

    /**
     * @param x      = współrzędna x środka
     * @param y      = współrzędna y środka
     * @param radius = promień
     */
    public MyCircle(double x, double y, double radius) {
        super(x, y, radius);
        setOnMouseClicked(new MyCircleEventHandler());
        setOnMouseDragged(new MyCircleEventHandler());
        setOnScroll(new MyCircleScrollHandler());
        setOnContextMenuRequested(new MyShapeContextMenuEventHandler());
        getTransforms().add(rotate);
        setStrokeWidth(3);
        setStroke(Color.BLACK);
        setFill(Edytor.colorpicker.getValue());
        this.x = x;
        this.y = y;
        this.radius = radius;
        rotation = 0;
    }

    // ! Konstruktor do wczytywania z pliku
    /**
     * @param circle = wczytywana figura
     */
    public MyCircle(MyCircle circle) {
        x = circle.x;
        y = circle.y;
        radius = circle.radius;
        paint = circle.paint;
        rotation = circle.rotation;
        rotate.setAngle(rotation);
        setCenterX(x);
        setCenterY(y);
        setRadius(radius);
        setFill(Color.valueOf(paint));
        getTransforms().add(rotate);
        setOnMouseClicked(new MyCircleEventHandler());
        setOnMouseDragged(new MyCircleEventHandler());
        setOnScroll(new MyCircleScrollHandler());
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

    // ! Skalowanie promienia
    /**
     * @param a = wartość zmiany
     */
    public void increaseRadius(double a) {
        setRadius(getRadius() + a);
    }

    // ! Zmiana wspolrzednej x
    /**
     * @param x = wartość zmiany
     */
    public void addX(double x) {
        setCenterX(getCenterX() + x);
    }

    // ! Zmiana wspolrzednej y
    /**
     * @param y = wartość zmiany
     */
    public void addY(double y) {
        setCenterY(getCenterY() + y);
    }

    // ! Obracanie
    /**
     * @param v = wartość obrotu
     */
    public void rotate(double v) {
        rotate.setAngle(rotate.getAngle() + v);
    }

    // ! Zapisywanie zmian przed zapisaniem figury
    private void update() {
        x = getCenterX();
        y = getCenterY();
        radius = getRadius();
        paint = getFill().toString();
        rotation = rotate.getAngle();
    }

    class MyCircleScrollHandler implements EventHandler<ScrollEvent> {

        MyCircle circle;

        private void doScale(ScrollEvent e) {

            double x = e.getX();
            double y = e.getY();

            if (circle.isHit(x, y)) {
                circle.increaseRadius(e.getDeltaY() * 0.2);
            }
        }

        @Override
        public void handle(ScrollEvent e) {

            circle = (MyCircle) e.getSource();
            if (e.getEventType() == ScrollEvent.SCROLL) {
                if (Edytor.checkbox.isSelected()) {
                    circle.rotate(e.getDeltaY() * 0.2);
                } else {
                    doScale(e);
                }
            }
        }

    }

    class MyCircleEventHandler implements EventHandler<MouseEvent> {

        MyCircle circle;
        private double x;
        private double y;

        private void doMove(MouseEvent e) {

            double dx = e.getX() - x;
            double dy = e.getY() - y;

            if (circle.isHit(x, y)) {
                circle.addX(dx);
                circle.addY(dy);
            }
            x += dx;
            y += dy;
            update();
        }

        @Override
        public void handle(MouseEvent e) {

            circle = (MyCircle) e.getSource();
            if (e.getEventType() == MouseEvent.MOUSE_CLICKED) {
                x = e.getX();
                y = e.getY();
            }
            if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                doMove(e);
            }
        }
    }
}