import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

public class Milk extends Multiplier{

    public static final Image texture = new ImageIcon("Textures/Milk.png").getImage();


    public Milk(int x, int y, Game game){
        super(x,y,16,32,game);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Milk.texture, this.x, this.y, this.width, this.height, this.game);
    }

    @Override
    public void addToMultiplier() {
        this.game.addMultiplier(100);
    }
}
