package HaxBall.Players;

import javafx.scene.image.Image;

public class Ball extends Sprite {
    private int UP = 6;
    private int DOWN = 6;
    private int LEFT = 6;
    private int RIGHT = 6;

    public Ball(Image image) {
        super(image);
        setX(657);
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

    public void move(String codes) {
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

        if (getPosX() < 116 || getPosX() > 1240 || getPosY() < 86|| getPosY() > 600) {
            setX(657);
            setY(315);
        }
    }
}
