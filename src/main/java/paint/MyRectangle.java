package paint;

import java.io.Serializable;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//! Klasa kwadratu
public class MyRectangle extends Rectangle implements Serializable {

    /** obrót */
    MyRotate rotate = new MyRotate(0, 450, 350);
    /** wartość obrotu */
    double rotation;
    /** współrzędna x */
    double x;
    /** współrzędna y */
    double y;
    /** szerokość */
    double width;
    /** wysokość */
    double height;
    /** kolor */
    String paint;

    /**
     * @param x      = współrzędna x
     * @param y      = współrzędna y
     * @param width  = szerokość
     * @param height = wysokość
     */
    public MyRectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
        setOnMouseClicked(new MyRectangleEventHandler());
        setOnMouseDragged(new MyRectangleEventHandler());
        setOnScroll(new MyRectangleScrollHandler());
        setOnContextMenuRequested(new MyShapeContextMenuEventHandler());
        getTransforms().add(rotate);
        setStrokeWidth(3);
        setStroke(Color.BLACK);
        setFill(Edytor.colorpicker.getValue());
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rotation = 0;
    }

    // ! Konstruktor do wczytywania z pliku
    /**
     * @param rectangle = wczytywana figura
     */
    public MyRectangle(MyRectangle rectangle) {
        x = rectangle.x;
        y = rectangle.y;
        width = rectangle.width;
        height = rectangle.height;
        paint = rectangle.paint;
        rotation = rectangle.rotation;
        rotate.setAngle(rotation);
        setX(this.x);
        setY(this.y);
        setWidth(this.width);
        setHeight(this.height);
        setFill(Color.valueOf(paint));
        getTransforms().add(rotate);
        setOnMouseClicked(new MyRectangleEventHandler());
        setOnMouseDragged(new MyRectangleEventHandler());
        setOnScroll(new MyRectangleScrollHandler());
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
        setX(getX() + x);
    }

    // ! Zmiana wspolrzednej y
    /**
     * @param y = wartość zmiany
     */
    public void addY(double y) {
        setY(getY() + y);
    }

    // ! Zmiana szerokości
    /**
     * @param w = wartość zmiany
     */
    public void addWidth(double w) {
        setWidth(getWidth() + w);
    }

    // ! Zmiana wysokości
    /**
     * @param h = wartość zmiany
     */
    public void addHeight(double h) {
        setHeight(getHeight() + h);
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
        x = getX();
        y = getY();
        width = getWidth();
        height = getHeight();
        paint = getFill().toString();
        rotation = rotate.getAngle();
    }

    // ! Implementacja przesuwania
    class MyRectangleEventHandler implements EventHandler<MouseEvent> {

        MyRectangle rectangle;
        private double x;
        private double y;

        private void doMove(MouseEvent event) {

            double dx = event.getX() - x;
            double dy = event.getY() - y;

            if (rectangle.isHit(x, y)) {
                rectangle.addX(dx);
                rectangle.addY(dy);
            }
            x += dx;
            y += dy;
        }

        @Override
        public void handle(MouseEvent event) {

            rectangle = (MyRectangle) event.getSource();
            if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                rectangle.update();
                x = event.getX();
                y = event.getY();
            }
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                doMove(event);
            }

        }
    }

    // ! Implementacja scrollowania
    class MyRectangleScrollHandler implements EventHandler<ScrollEvent> {

        MyRectangle rectangle;

        private void doScale(ScrollEvent e) {

            double x = e.getX();
            double y = e.getY();

            if (rectangle.isHit(x, y)) {
                rectangle.addWidth(e.getDeltaY() * 0.2);
                rectangle.addHeight(e.getDeltaY() * 0.2);
            }
        }

        @Override
        public void handle(ScrollEvent e) {

            rectangle = (MyRectangle) e.getSource();
            if (e.getEventType() == ScrollEvent.SCROLL) {
                if (Edytor.checkbox.isSelected()) {
                    rectangle.rotate(e.getDeltaY() * 0.2);
                } else {
                    doScale(e);
                }
            }
        }
    }
}
