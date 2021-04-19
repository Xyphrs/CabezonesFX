package HaxBall.InGame;

import HaxBall.Endscreen.Endscreen;
import HaxBall.Menu.Menu;
import HaxBall.Players.Ball;
import HaxBall.Players.BluePlayer;
import HaxBall.Players.RedPlayer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    private static final int STARTTIME = 90;
    private Timeline timeline;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    @FXML private Label leftscore, rightscore, timer;
    static RedPlayer redPlayer;
    static BluePlayer bluePlayer;
    static Ball ball;
    String pitido = "pitido.mp3";
    Endscreen endscreen = new Endscreen();
    @FXML Canvas mainCanvas;
    GraphicsContext gc;
    private Image fons;

    Timeline start = new Timeline(new KeyFrame(Duration.seconds(0.001), event -> {
        try {
            gc.drawImage(fons, 0, 0, 1365, 650);
            bluePlayer.render(gc);
            redPlayer.render(gc);
            ball.render(gc);
            playerVSplayer();
            blueplayerVSball();
            redplayerVSball();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Menu.menu();
        redPlayer = new RedPlayer(new Image("HaxBall/Resources/red_player.png"));
        bluePlayer = new BluePlayer(new Image("HaxBall/Resources/blue_player.png"));
        ball = new Ball(new Image("HaxBall/Resources/ball.png"));
        fons = new Image("HaxBall/Resources/football_field.png");
        gc = mainCanvas.getGraphicsContext2D();
        timer.textProperty().bind(timeSeconds.asString());
        start.setCycleCount(Timeline.INDEFINITE);
        start.play();
        startTime();
    }

    private void updateTime() throws IOException {
        int seconds = timeSeconds.get();

        if (seconds == 0) {
            timeline.stop();
            start.stop();
            endscreen.show(leftscore, rightscore);
        } else {
            timeSeconds.set(seconds - 1);
        }
    }

    public void startTime() {
        Media sound = new Media(new File(pitido).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> {
            try {
                updateTime();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeSeconds.set(STARTTIME);
        timeline.play();
    }

    public void setScene(Scene sc) {
        final List<KeyCode> acceptedCodes = Arrays.asList(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D);
        final Set<KeyCode> codes = new HashSet<>();
        sc.setOnKeyReleased(e -> codes.clear());
        sc.setOnKeyPressed(e -> {
            if (acceptedCodes.contains(e.getCode())) {
                codes.add(e.getCode());
                bluePlayer.move(codes);
                redPlayer.move(codes);
            }
        });
    }

    private void playerVSplayer() {
        float blueplayer_bottom = (float) (bluePlayer.getPosY() + bluePlayer.getBoundary().getHeight());
        float redplayer_bottom = (float) (redPlayer.getPosY() + redPlayer.getBoundary().getHeight());
        float blueplayer_right = (float) (bluePlayer.getPosX() + bluePlayer.getBoundary().getWidth());
        float redplayer_right = (float) (redPlayer.getPosX() + redPlayer.getBoundary().getWidth());
        float bottom_collision = (float) (redplayer_bottom - bluePlayer.getPosY());
        float top_collision = (float) (blueplayer_bottom - redPlayer.getPosY());
        float left_collision = (float) (blueplayer_right - redPlayer.getPosX());
        float right_collision = (float) (redplayer_right - bluePlayer.getPosX());

        int seconds = timeSeconds.get();

        if (seconds < 30) {
            bluePlayer.setDOWN(12);
            bluePlayer.setUP(12);
            bluePlayer.setRIGHT(12);
            bluePlayer.setLEFT(12);

            redPlayer.setDOWN(12);
            redPlayer.setUP(12);
            redPlayer.setRIGHT(12);
            redPlayer.setLEFT(12);
        } else {
            bluePlayer.setDOWN(6);
            bluePlayer.setUP(6);
            bluePlayer.setRIGHT(6);
            bluePlayer.setLEFT(6);

            redPlayer.setDOWN(6);
            redPlayer.setUP(6);
            redPlayer.setRIGHT(6);
            redPlayer.setLEFT(6);
        }

        if (bluePlayer.getBoundary().intersects(redPlayer.getBoundary())) {
            if (seconds > 30) {
                if (top_collision < bottom_collision && top_collision < left_collision && top_collision < right_collision) {
                    System.out.println("TOP");
                    bluePlayer.setDOWN(0);
                    bluePlayer.setUP(6);
                    bluePlayer.setRIGHT(6);
                    bluePlayer.setLEFT(6);

                    redPlayer.setDOWN(6);
                    redPlayer.setUP(0);
                    redPlayer.setRIGHT(6);
                    redPlayer.setLEFT(6);
                }

                if (bottom_collision < top_collision && bottom_collision < left_collision && bottom_collision < right_collision) {
                    System.out.println("BOTTOM");
                    bluePlayer.setDOWN(6);
                    bluePlayer.setUP(0);
                    bluePlayer.setRIGHT(6);
                    bluePlayer.setLEFT(6);

                    redPlayer.setDOWN(0);
                    redPlayer.setUP(6);
                    redPlayer.setRIGHT(6);
                    redPlayer.setLEFT(6);
                }

                if (left_collision < right_collision && left_collision < top_collision && left_collision < bottom_collision) {
                    System.out.println("LEFT");
                    bluePlayer.setDOWN(6);
                    bluePlayer.setUP(6);
                    bluePlayer.setRIGHT(0);
                    bluePlayer.setLEFT(6);

                    redPlayer.setDOWN(6);
                    redPlayer.setUP(6);
                    redPlayer.setRIGHT(6);
                    redPlayer.setLEFT(0);
                }

                if (right_collision < left_collision && right_collision < top_collision && right_collision < bottom_collision) {
                    System.out.println("RIGHT");
                    bluePlayer.setDOWN(6);
                    bluePlayer.setUP(6);
                    bluePlayer.setRIGHT(6);
                    bluePlayer.setLEFT(0);

                    redPlayer.setDOWN(6);
                    redPlayer.setUP(6);
                    redPlayer.setRIGHT(0);
                    redPlayer.setLEFT(6);
                }
            } else if (seconds < 30) {
                if (top_collision < bottom_collision && top_collision < left_collision && top_collision < right_collision) {
                    System.out.println("TOP");
                    bluePlayer.setDOWN(0);
                    bluePlayer.setUP(12);
                    bluePlayer.setRIGHT(12);
                    bluePlayer.setLEFT(12);

                    redPlayer.setDOWN(12);
                    redPlayer.setUP(0);
                    redPlayer.setRIGHT(12);
                    redPlayer.setLEFT(12);
                }

                if (bottom_collision < top_collision && bottom_collision < left_collision && bottom_collision < right_collision) {
                    System.out.println("BOTTOM");
                    bluePlayer.setDOWN(12);
                    bluePlayer.setUP(0);
                    bluePlayer.setRIGHT(12);
                    bluePlayer.setLEFT(12);

                    redPlayer.setDOWN(0);
                    redPlayer.setUP(12);
                    redPlayer.setRIGHT(12);
                    redPlayer.setLEFT(12);
                }

                if (left_collision < right_collision && left_collision < top_collision && left_collision < bottom_collision) {
                    System.out.println("LEFT");
                    bluePlayer.setDOWN(12);
                    bluePlayer.setUP(12);
                    bluePlayer.setRIGHT(0);
                    bluePlayer.setLEFT(12);

                    redPlayer.setDOWN(12);
                    redPlayer.setUP(12);
                    redPlayer.setRIGHT(12);
                    redPlayer.setLEFT(0);
                }

                if (right_collision < left_collision && right_collision < top_collision && right_collision < bottom_collision) {
                    System.out.println("RIGHT");
                    bluePlayer.setDOWN(12);
                    bluePlayer.setUP(12);
                    bluePlayer.setRIGHT(12);
                    bluePlayer.setLEFT(0);

                    redPlayer.setDOWN(12);
                    redPlayer.setUP(12);
                    redPlayer.setRIGHT(0);
                    redPlayer.setLEFT(12);
                }
            }
        }
    }

    private void blueplayerVSball() throws InterruptedException {
        String dir;
        float blueplayer_bottom = (float) (bluePlayer.getPosY() + bluePlayer.getBoundary().getHeight());
        float ball_bottom = (float) (ball.getPosY() + ball.getBoundary().getHeight());
        float blueplayer_right = (float) (bluePlayer.getPosX() + bluePlayer.getBoundary().getWidth());
        float ball_right = (float) (ball.getPosX() + ball.getBoundary().getWidth());
        float bottom_collision = (float) (ball_bottom - bluePlayer.getPosY());
        float top_collision = (float) (blueplayer_bottom - ball.getPosY());
        float left_collision = (float) (blueplayer_right - ball.getPosX());
        float right_collision = (float) (ball_right - bluePlayer.getPosX());

        if (bluePlayer.getBoundary().intersects(ball.getBoundary())) {
            if (top_collision < bottom_collision && top_collision < left_collision && top_collision < right_collision) {
                System.out.println("BLUE UP");
                dir = "BLUE UP";
                ball.move(dir, leftscore, rightscore, bluePlayer, redPlayer, start);
            }

            if (bottom_collision < top_collision && bottom_collision < left_collision && bottom_collision < right_collision) {
                System.out.println("BLUE DOWN");
                dir = "BLUE DOWN";
                ball.move(dir, leftscore, rightscore, bluePlayer, redPlayer, start);
            }

            if (left_collision < right_collision && left_collision < top_collision && left_collision < bottom_collision) {
                System.out.println("BLUE LEFT");
                dir = "BLUE LEFT";
                ball.move(dir, leftscore, rightscore, bluePlayer, redPlayer, start);
            }

            if (right_collision < left_collision && right_collision < top_collision && right_collision < bottom_collision) {
                System.out.println("BLUE RIGHT");
                dir = "BLUE RIGHT";
                ball.move(dir, leftscore, rightscore, bluePlayer, redPlayer, start);
            }
        } else {
            dir = "";
        }
    }

    private void redplayerVSball() throws InterruptedException {
        String dir;
        float blueplayer_bottom = (float) (redPlayer.getPosY() + redPlayer.getBoundary().getHeight());
        float ball_bottom = (float) (ball.getPosY() + ball.getBoundary().getHeight());
        float blueplayer_right = (float) (redPlayer.getPosX() + redPlayer.getBoundary().getWidth());
        float ball_right = (float) (ball.getPosX() + ball.getBoundary().getWidth());
        float bottom_collision = (float) (ball_bottom - redPlayer.getPosY());
        float top_collision = (float) (blueplayer_bottom - ball.getPosY());
        float left_collision = (float) (blueplayer_right - ball.getPosX());
        float right_collision = (float) (ball_right - redPlayer.getPosX());

        if (redPlayer.getBoundary().intersects(ball.getBoundary())) {
            if (top_collision < bottom_collision && top_collision < left_collision && top_collision < right_collision) {
                System.out.println("RED UP");
                dir = "RED UP";
                ball.move(dir, leftscore, rightscore, bluePlayer, redPlayer, start);
            }

            if (bottom_collision < top_collision && bottom_collision < left_collision && bottom_collision < right_collision) {
                System.out.println("RED DOWN");
                dir = "RED DOWN";
                ball.move(dir, leftscore, rightscore, bluePlayer, redPlayer, start);
            }

            if (left_collision < right_collision && left_collision < top_collision && left_collision < bottom_collision) {
                System.out.println("RED LEFT");
                dir = "RED LEFT";
                ball.move(dir, leftscore, rightscore, bluePlayer, redPlayer, start);
            }

            if (right_collision < left_collision && right_collision < top_collision && right_collision < bottom_collision) {
                System.out.println("RED RIGHT");
                dir = "RED RIGHT";
                ball.move(dir, leftscore, rightscore, bluePlayer, redPlayer, start);
            }
        } else {
            dir = "";
        }
    }
}