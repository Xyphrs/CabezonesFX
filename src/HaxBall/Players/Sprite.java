package HaxBall.Players;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    private Image image;
    private double width;
    private double height;
    private double posX;
    private double posY;

    public Sprite(Image image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
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

    public Rectangle2D getBoundary() {
        return new Rectangle2D(posX, posY, width, height);
    }
}
