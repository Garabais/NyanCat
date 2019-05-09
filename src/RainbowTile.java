import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class RainbowTile extends GameElement {

    private static final Image texture = new ImageIcon("Textures/RainbowTile.png").getImage();

    public RainbowTile(int x, int y, Game game){
        super(x,y,8,32, game);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(RainbowTile.texture, this.x, this.y, (int)this.getWidth(), (int)this.getHeight(), this.game);
    }

}
