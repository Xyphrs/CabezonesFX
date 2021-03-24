package HaxBall.InGame;

import HaxBall.Players.BluePlayer;
import HaxBall.Players.RedPlayer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.util.Duration;
import javafx.scene.media.*;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
      private static final int STARTTIME = 30;
      private Timeline timeline;
      private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
      @FXML private Label leftscore, rightscore, timer;
      static RedPlayer redPlayer;
      static BluePlayer bluePlayer;
      @FXML Canvas mainCanvas;
      GraphicsContext gc;
      private Image fons;

      Timeline start = new Timeline(new KeyFrame(Duration.seconds(0.0005), new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                  redPlayer.clear(gc);
                  bluePlayer.clear(gc);
                  gc.drawImage(fons, 0,0,1365,650);
                  bluePlayer.render(gc);
                  redPlayer.render(gc);
            }
      }));

      @Override
      public void initialize(URL url, ResourceBundle resourceBundle) {
            redPlayer = new RedPlayer(new Image("HaxBall/Resources/red_player.png"));
            bluePlayer = new BluePlayer(new Image("HaxBall/Resources/blue_player.png"));
            fons = new Image("HaxBall/Resources/football_field.png");
            gc = mainCanvas.getGraphicsContext2D();
            timer.textProperty().bind(timeSeconds.asString());
            start.setCycleCount(Timeline.INDEFINITE);
            start.play();
            startTime();
      }

      private void updateTime() {
            int seconds = timeSeconds.get();
            if (seconds == 0) {
                  timeline.stop();
                  start.stop();
            } else {
                  timeSeconds.set(seconds-1);
            }
      }

      public void startTime() {
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> updateTime()));
            timeline.setCycleCount(Animation.INDEFINITE); // repeat over and over again
            timeSeconds.set(STARTTIME);
            timeline.play();
      }
}