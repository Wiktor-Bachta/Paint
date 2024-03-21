package paint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.shape.Shape;

//! Klasa menu pod prawym przyciskiem myszy
public class MyContextMenu extends ContextMenu {

    /** figura do zapisywania */
    Shape figure;

    public MyContextMenu() {
        super();
        MenuItem item1 = new MenuItem("Zapisz");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    FileOutputStream f = new FileOutputStream(new File(Edytor.textarea.getText()));
                    ObjectOutputStream o = new ObjectOutputStream(f);

                    o.writeObject(figure);
                    o.close();
                    f.close();
                    System.out.println("zapisano");

                } catch (FileNotFoundException ex) {
                    System.out.println("File not found");
                } catch (IOException ex) {
                    System.out.println("Error initializing stream");
                }

            }
        });

        MenuItem item2 = new MenuItem("Ustaw Kolor");
        item2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                figure.setFill(Edytor.colorpicker.getValue());
            }
        });
        getItems().addAll(item1, item2);
    }

    public void setFigure(Shape figure) {
        this.figure = figure;
    }
}

class MyShapeContextMenuEventHandler implements EventHandler<ContextMenuEvent> {

    @Override
    public void handle(ContextMenuEvent e) {
        Edytor.contextmenu.show(Edytor.pole, e.getScreenX(), e.getScreenY());
        Edytor.contextmenu.figure = (Shape) e.getSource();
    }
}