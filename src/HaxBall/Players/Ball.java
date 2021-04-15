package HaxBall.Players;

import HaxBall.InGame.Controller;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class Ball extends Sprite {
    int redgoal = 0;
    int bluegoal = 0;

    public Ball(Image image) {
        super(image);
        setX(657);
        setY(315);
    }

    public void move(String codes, Label left, Label right, BluePlayer bluePlayer, RedPlayer redPlayer, Timeline start) throws InterruptedException {
        int RIGHT = 6;
        int LEFT = 6;
        int DOWN = 6;
        int UP = 6;
        if (codes.contains("BLUE UP")) {
            setY(getPosY() + UP);
        } else if (codes.contains("BLUE DOWN")) {
            setY(getPosY() - DOWN);
        } else if (codes.contains("BLUE LEFT")) {
            setX(getPosX() + LEFT);
        } else if (codes.contains("BLUE RIGHT")) {
            setX(getPosX() - RIGHT);
        }

        if (codes.contains("RED UP")) {
            setY(getPosY() + UP);
        } else if (codes.contains("RED DOWN")) {
            setY(getPosY() - DOWN);
        } else if (codes.contains("RED LEFT")) {
            setX(getPosX() + LEFT);
        } else if (codes.contains("RED RIGHT")) {
            setX(getPosX() - RIGHT);
        }

        if (getPosX() < 72 && getPosY() > 240 && getPosY() < 400) {
            setX(657);
            setY(315);
            bluePlayer.setX(400);
            bluePlayer.setY(315);
            redPlayer.setX(905);
            redPlayer.setY(315);
            redgoal++;
            right.setText(String.valueOf(redgoal));
        } else if (getPosX() > 1239 && getPosY() > 240 && getPosY() < 400) {
            setX(657);
            setY(315);
            bluePlayer.setX(400);
            bluePlayer.setY(315);
            redPlayer.setX(905);
            redPlayer.setY(315);
            bluegoal++;
            left.setText(String.valueOf(bluegoal));
        }

        if (getPosX() > 1240) {
            setX(1100);
        } else if (getPosY() < 41) {
            setY(150);
        } else if (getPosX() < 71) {
            setX(190);
        } else if (getPosY() > 590) {
            setY(500);
        }
    }
}
