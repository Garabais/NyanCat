import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

public class JumpablePlatform extends Platform{

    public static final Image texture = new ImageIcon("Textures/RainbowTile.png").getImage();


    public JumpablePlatform(int x, int y, int width, Game game) {
        super(x, y, width, game);
    }

    @Override
    public void touch() {
        this.game.applyJumpForce(this.width);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(JumpablePlatform.texture, this.x, this.y, this.width, this.height, this.game);
    }
}
