package HaxBall.Endscreen;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Endscreen {
    @FXML Label left, right;
    public Stage window = new Stage();
    public void show(Label leftscore, Label rightscore) {
        String musicFile = "champions.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        VBox layout = new VBox(10);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Final");

        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });

        left = leftscore;
        right = rightscore;

        Button closeButton = new Button("Empezar el Juego");
        closeButton.setOnAction(e -> {
            window.close();
            mediaPlayer.stop();

        });

        BackgroundImage background = new BackgroundImage(new Image("HaxBall/Resources/imagenfinal.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        layout.setBackground(new Background(background));

        layout.getChildren().addAll(leftscore, rightscore, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 1345, 650);
        window.setScene(scene);
        window.show();
    }
}
