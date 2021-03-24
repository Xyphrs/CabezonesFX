package HaxBall.InGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String css = getClass().getResource("style.css").toExternalForm();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Haxball World Cup");
        Scene scene = new Scene(root, 1345, 650);
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        final List<KeyCode> acceptedCodes = Arrays.asList(KeyCode.S, KeyCode.W, KeyCode.D, KeyCode.A, KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT);
        final Set<KeyCode> codes = new HashSet<>();
        scene.setOnKeyReleased(e -> codes.clear());
        scene.setOnKeyPressed(e -> {
            if (acceptedCodes.contains(e.getCode())) {
                codes.add(e.getCode());
                if (codes.contains(KeyCode.W)) {
                    if (Controller.redPlayer.getPosY() - 3 >= 86) {
                        Controller.redPlayer.setY(Controller.redPlayer.getPosY() - 6);
                    }
                } else if (codes.contains(KeyCode.S)) {
                    if (Controller.redPlayer.getPosY() + 53 <= 600) {
                        Controller.redPlayer.setY(Controller.redPlayer.getPosY() + 6);
                    }
                } else if (codes.contains(KeyCode.A )) {
                    if (Controller.redPlayer.getPosX() - 3 >= 116) {
                        Controller.redPlayer.setX(Controller.redPlayer.getPosX() - 6);
                    }
                } else if (codes.contains(KeyCode.D)) {
                    if (Controller.redPlayer.getPosX() + 53 <= 1240) {
                        Controller.redPlayer.setX(Controller.redPlayer.getPosX() + 6);
                    }
                }

                if (codes.contains(KeyCode.UP)) {
                    if (Controller.bluePlayer.getPosY() - 3 >= 86) {
                        Controller.bluePlayer.setY(Controller.bluePlayer.getPosY() - 6);
                    }
                } else if (codes.contains(KeyCode.DOWN)) {
                    if (Controller.bluePlayer.getPosY() + 53 <= 600) {
                        Controller.bluePlayer.setY(Controller.bluePlayer.getPosY() + 6);
                    }
                } else if (codes.contains(KeyCode.LEFT)) {
                    if (Controller.bluePlayer.getPosX() - 3 >= 116) {
                        Controller.bluePlayer.setX(Controller.bluePlayer.getPosX() - 6);
                    }
                } else if (codes.contains(KeyCode.RIGHT)) {
                    if (Controller.bluePlayer.getPosX() + 53 <= 1240) {
                        Controller.bluePlayer.setX(Controller.bluePlayer.getPosX() + 6);
                    }
                }
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
