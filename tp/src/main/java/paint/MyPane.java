package paint;

import javafx.event.EventHandler;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;

//! Klasa pane, na którym rysowane są figury
public class MyPane extends Pane {
    //! Konstruktor klasy pane
    public MyPane() {
        super();
        Circle cir = new Circle(450, 350, 3);
        getChildren().addAll(cir);
        setOnMouseClicked(new MyPaneEventHandler());
        setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
    }

    class MyPaneEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            Edytor.contextmenu.hide();
        }

    }
}
