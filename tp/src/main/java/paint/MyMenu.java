package paint;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

//! Klasa menu odpowiedzialnego za wybór rysowanej figury
public class MyMenu extends Menu {

    public MyMenu(String str) {

        super(str);
        MenuItem menuRectangle = new MenuItem("Prostokat");
        MenuItem menuCircle = new MenuItem("Kolo");
        MenuItem menuPolygon = new MenuItem("Wielokat");
        getItems().addAll(menuRectangle, menuCircle, menuPolygon);

        menuRectangle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Edytor.pole.setOnMouseClicked(new MyRectanglePaneEventHandler());
            }
        });

        menuCircle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Edytor.pole.setOnMouseClicked(new MyCirclePaneEventHandler());
            }
        });

        menuPolygon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Edytor.pole.setOnMouseClicked(new MyPolygonPaneEventHandler());
            }
        });
    }
}

//! Rysowanie prostokąta
class MyRectanglePaneEventHandler implements EventHandler<MouseEvent> {
    Pane pane;
    boolean first_click = true;
    double x;
    double y;

    @Override
    public void handle(MouseEvent e) {
        if (first_click == true) {
            x = e.getX();
            y = e.getY();
            first_click = false;
        } else {
            pane = (Pane) e.getSource();
            pane.getChildren().add(new MyRectangle(Math.min(x, e.getX()), Math.min(y, e.getY()), Math.abs(x - e.getX()),
                    Math.abs(y - e.getY())));
            pane.setOnMouseClicked(null);
        }
    }

}

//! Rysowanie koła
class MyCirclePaneEventHandler implements EventHandler<MouseEvent> {
    Pane pane;
    boolean first_click = true;
    double x;
    double y;

    @Override
    public void handle(MouseEvent e) {
        if (first_click == true) {
            x = e.getX();
            y = e.getY();
            first_click = false;
        } else {
            pane = (Pane) e.getSource();
            pane.getChildren().add(new MyCircle(x, y, Math.hypot(e.getX() - x, e.getY() - y)));
            pane.setOnMouseClicked(null);
        }
    }

}

//! Rysowanie wielokąta
class MyPolygonPaneEventHandler implements EventHandler<MouseEvent> {
    Pane pane;
    ArrayList<Double> points = new ArrayList<Double>();

    @Override
    public void handle(MouseEvent e) {
        if (e.getEventType() == MouseEvent.MOUSE_CLICKED && e.getClickCount() == 2) {
            pane = (Pane) e.getSource();
            points.add(e.getX());
            points.add(e.getY());
            double[] array = new double[points.size()];
            for (int i = 0; i < points.size(); i++) {
                array[i] = points.get(i);
            }
            pane.getChildren().add(new MyPolygon(array));
            pane.setOnMouseClicked(null);
        } else if (e.getEventType() == MouseEvent.MOUSE_CLICKED) {
            points.add(e.getX());
            points.add(e.getY());
        }
    }
}
