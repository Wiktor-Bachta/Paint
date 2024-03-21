package paint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.shape.Shape;

//! Klasa przycisku wczytującego figury
public class MyButton extends Button {
    public MyButton(String str) {
        super(str);
        setOnAction(new MyButtonEventHandler());
    }

    class MyButtonEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent arg0) {

            HashMap<String, FigureBuilder> map = new HashMap<String, FigureBuilder>() {
                {
                    put("MyRectangle", new RectangleBuilder());
                    put("MyCircle", new CircleBuilder());
                    put("MyPolygon", new PolygonBuilder());

                }
            };

            try {
                FileInputStream fi = new FileInputStream(new File(Edytor.textarea.getText()));
                ObjectInputStream oi = new ObjectInputStream(fi);
                Object object = oi.readObject();
                Shape shape = map.get(object.getClass().getName()).buildFigure(object);
                Edytor.pole.getChildren().add(shape);

                oi.close();
                fi.close();
                System.out.println("wczytano");

            } catch (FileNotFoundException ex) {
                System.out.println("File not found");
            } catch (IOException ex) {
                System.out.println("Error initializing stream");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        }
    }
}
