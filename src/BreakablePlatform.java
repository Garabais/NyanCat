import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class BreakablePlatform extends Platform {

    private int counter;

    public static final Image texture = new ImageIcon("Textures/BreakablePlatform.png").getImage();


    public BreakablePlatform(int x, int y, int width, Game game) {
        super(x, y, width, game);
        this.counter = 1;
    }

    @Override
    public void draw(Graphics g) {

        int offset = this.counter % 2 == 0 ? 3 : 0;
        g.drawImage(BreakablePlatform.texture, this.x, this.y + offset, this.width, this.height, this.game);
        if (touched){
            if (counter++ %15 == 0){
                this.selfDestroy();
            }
        }
    }
    @Override
    public void touch(){
        this.touched = true;
    }
}
