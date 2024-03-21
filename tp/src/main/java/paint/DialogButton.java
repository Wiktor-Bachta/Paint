package paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;

//! Klasa przycisku odpowiedzialnego za okno dialogowe 
public class DialogButton extends Button {

    //! Tworzy button odpowiedzialny za dialog 
    public DialogButton(String str) {

        super(str);

        Dialog<String> dialog = new Dialog<String>();
        ButtonType type = new ButtonType("Ok", ButtonData.CANCEL_CLOSE);
        dialog.setTitle("Info");
        String text = "Nazwa: Edytor Figur      Autor: Wiktor Bachta \n\n"
                + "Instrukcja: \nProstakat oraz Kolo rysuje sie dwoma kliknieciami\n"
                + "Wielokat rysuje sie pojedynczymi kliknieciami i konczy rysowanie dwuklikiem\n\n"
                + "Figure powiekszamy scrollem, jesli nie jest zaznaczony checkbox obracania\n"
                + "Figury obracaja sie wokol wyroznionego punktu\n\n"
                + "Kolor zmieniamy prawym przyciskiem myszy\n\n" + "Zapisywanie do pliku:\n"
                + "Wpisujemy nazwe pliku w polu i zapisujemy prawym przyciskiem myszy\n\n"
                + "Wczytywanie z pliku:\n" + "Wpisujemy nazwe pliku i klikamy przycisk Wczytaj \n";
        dialog.setContentText(text);
        dialog.getDialogPane().getButtonTypes().add(type);

        // Obsluga wyswietlenia okna dialogowego dialogu
        EventHandler<ActionEvent> evnHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.showAndWait();
            }
        };
        setOnAction(evnHandler);

    }
}
