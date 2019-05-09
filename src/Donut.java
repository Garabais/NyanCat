import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

public class Donut extends Multiplier{

    public static final Image texture = new ImageIcon("Textures/Donut.png").getImage();


    public Donut(int x, int y, Game game){
        super(x,y,32,32,game);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Donut.texture, this.x, this.y, this.width, this.height, this.game);
    }

    @Override
    public void addToMultiplier() {
        this.game.addMultiplier(50);
    }
}
