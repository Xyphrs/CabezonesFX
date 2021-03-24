package HaxBall.Players;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class BluePlayer{
    private Image image;
    private double posX, posY, velX, velY, width, height;
    private int dirX, dirY;

    public BluePlayer(Image image) {
        this.posX = 400;
        this.posY = 300;
        this.velX = 1.0;
        this.velY = 1.0;
        this.dirX = 1;
        this.dirY = 1;
        setImage(image);
    }

    public void clear(GraphicsContext gc) {
        gc.clearRect(posX,posY, width, height);
    }

    public void setX(double x) {
        posX = x;
    }

    public void setY(double y) {
        posY = y;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, posX, posY);
    }

    public void setImage(Image i) {
        image = i;
        width = image.getWidth();
        height = image.getHeight();
    }
}

