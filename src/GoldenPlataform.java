import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class GoldenPlataform extends Platform {
    private static int MULTIPLIER = 1;

    public static final Image texture = new ImageIcon("Textures/GoldenPlatform.png").getImage();


    public GoldenPlataform(int x, int y, int width, Game game) {
        super(x, y, width, game);
    }

    @Override
    public void touch() {
        if (!touched){
            this.game.addScore(100 * GoldenPlataform.MULTIPLIER++);
            touched = true;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(GoldenPlataform.texture, this.x, this.y, this.width, this.height, this.game);
//        g.setColor(Color.YELLOW);
//        g.fillRect(this.x, this.y, this.width, this.height);
    }
}
