package HaxBall.Menu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Menu {
    public static void menu(String title, String name) {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(String.valueOf(title));
        //Tamaños de la pantalla
        window.setMinWidth(1365);
        window.setMinHeight(650);

        Label label = new Label();
        label.setText(name);
        Button closeButton = new Button("Empezar el Juego");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);

        //Assigno el color de fondo
        layout.setStyle("-fx-background-color: orange");

        layout.getChildren().addAll(label, closeButton);
        //Assigno donde se vera el contenido
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}

