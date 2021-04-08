package HaxBall.InGame;

import HaxBall.Players.BluePlayer;
import HaxBall.Players.RedPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main extends Application {

    @Override
    public void start(Stage fieldStage) throws Exception {
        Controller controller = new Controller();
        String css = Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        fieldStage.setTitle("Haxball World Cup");
        Scene field = new Scene(root, 1345, 650);
        field.getStylesheets().add(css);
        fieldStage.setScene(field);
        controller.setScene(field);
        fieldStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
