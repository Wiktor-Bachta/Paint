package paint;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//! Główna klasa programu 
public class Edytor extends Application {

    /** chechbox obracania */
    public static CheckBox checkbox;
    /** pole, na którym rysujemy figury */
    public static Pane pole;
    /** menu prawego przycisku myszy */
    public static MyContextMenu contextmenu;
    /** menu wyboru koloru */
    public static ColorPicker colorpicker;
    /** miejsce na wpisywanie nazwy pliku */
    public static TextArea textarea;

    /** funkcja main */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        checkbox = new CheckBox("obracanie");
        pole = new MyPane();
        contextmenu = new MyContextMenu();
        colorpicker = new ColorPicker(Color.GREEN);
        textarea = new TextArea("nazwa pliku wczytywanego/zapisywanego");

        primaryStage.setTitle("Edytor figur");
        textarea.setPrefRowCount(1);

        BorderPane panel = new BorderPane();
        MenuBar menuBar = new MenuBar();
        HBox hbox = new HBox(15);
        Button loadButton = new MyButton("Wczytaj");
        Button infoButton = new DialogButton("Info");
        Menu menu = new MyMenu("Stworz figure");

        hbox.getChildren().addAll(checkbox, colorpicker, textarea, loadButton, infoButton);
        hbox.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setAlignment(Pos.CENTER_LEFT);

        menuBar.getMenus().addAll(menu);

        panel.setCenter(pole);
        panel.setTop(menuBar);
        panel.setBottom(hbox);

        Scene scene = new Scene(panel, 900, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
