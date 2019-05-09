import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

public class Sausage extends Multiplier{

    public static final Image texture = new ImageIcon("Textures/Sausage.png").getImage();


    public Sausage(int x, int y, Game game){
        super(x,y,16,32,game);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Sausage.texture, this.x, this.y, this.width, this.height, this.game);
    }

    @Override
    public void addToMultiplier() {
        this.game.addMultiplier(25);
    }
}
