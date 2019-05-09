import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

public class Star extends GameElement{

    public static final Image texture1 = new ImageIcon("Textures/Star1.png").getImage();
    public static final Image texture2 = new ImageIcon("Textures/Star2.png").getImage();
    public static final Image texture3 = new ImageIcon("Textures/Star3.png").getImage();
    public static final Image texture4 = new ImageIcon("Textures/Star4.png").getImage();

    private int c, state;

    public Star(int x, int y, Game game) {
        super(x, y, 22, 22, game);
        this.c = 0;
        this.state =0;
    }

    @Override
    public void draw(Graphics g) {
        if (c++ % 6 == 0) {
            state++;
        }
        switch (state % 5) {
            case 0:
                g.drawImage(Star.texture1, this.x, this.y,this.width,this.height, this.game);
                break;
            case 1:
                g.drawImage(Star.texture2, this.x, this.y,this.width,this.height, this.game);
                break;
            case 2:
                g.drawImage(Star.texture3, this.x, this.y,this.width,this.height, this.game);
                break;
            case 3:
                g.drawImage(Star.texture4, this.x, this.y, this.width, this.height, this.game);
                break;
            case 4:
                this.selfDestroy();
                break;
        }
    }

    @Override
    public void selfDestroy() {
        this.game.destroy(this);
    }
}
