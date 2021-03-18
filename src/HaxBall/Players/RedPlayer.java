package HaxBall.Players;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RedPlayer {

    private Image image;
    private double posX, posY, velX, velY, width, height;
    private int dirX, dirY;

    public RedPlayer(Image image) {
        this.posX = 1200;
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

    public void move() {
        if(dirX == 1) {
            posX += velX;
            if(posX>=1345-width) dirX = (-1)*dirX;
        }else {
            posX -= velX;
            if(posX<=0) dirX = (-1)*dirX;
        }
        if(dirY == 1){
            posY += velY;
            if(posY>=650-height) dirY = (-1)*dirY;
        }
        else {
            posY -= velY;
            if(posY<=0) dirY = (-1)*dirY;
        }
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
