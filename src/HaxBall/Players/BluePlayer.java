package HaxBall.Players;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class BluePlayer extends Sprite {
    private int UP = 6;
    private int DOWN = 6;
    private int LEFT = 6;
    private int RIGHT = 6;

    public BluePlayer(Image image) {
        super(image);
        setX(400);
        setY(315);
    }

    public void setUP(int UP) {
        this.UP = UP;
    }

    public void setDOWN(int DOWN) {
        this.DOWN = DOWN;
    }

    public void setLEFT(int LEFT) {
        this.LEFT = LEFT;
    }

    public void setRIGHT(int RIGHT) {
        this.RIGHT = RIGHT;
    }

    public void move(Set<KeyCode> codes) {
        if (codes.contains(KeyCode.UP)) {
            if (getPosY() - 3 >= 86) {
                setY(getPosY() - UP);
            }
        } else if (codes.contains(KeyCode.DOWN)) {
            if (getPosY() + 53 <= 600) {
                setY(getPosY() + DOWN);
            }
        } else if (codes.contains(KeyCode.LEFT)) {
            if (getPosX() - 3 >= 116) {
                setX(getPosX() - LEFT);
            }
        } else if (codes.contains(KeyCode.RIGHT)) {
            if (getPosX() + 53 <= 1240) {
                setX(getPosX() + RIGHT);
            }
        }
    }
}

