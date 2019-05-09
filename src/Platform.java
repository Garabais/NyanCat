import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Platform extends GameElement {

    protected int pastY;
    protected boolean touched;

    public static final Image texture1 = new ImageIcon("Textures/Platform1.png").getImage();
    public static final Image texture2 = new ImageIcon("Textures/Platform2.png").getImage();

    public Platform(int x, int y, int width, Game game){
        super(x,y,width,10, game);
        this.pastY = y;
        this.touched = false;
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(Platform.texture1, this.x, this.y, 7, this.height, this.game);
        g.drawImage(Platform.texture2, this.x + 7, this.y, this.width - 14, this.height, this.game);
        g.drawImage(Platform.texture1, this.x + this.width, this.y, -7, this.height, this.game);
    }

    @Override
    public void veticalTranslate(int move){
        if (move != 0){
            this.pastY = this.y;
            this.y += move;
        }
    }

    public int getPastY(){
        return pastY;
    }

    @Override
    public void selfDestroy(){
        this.game.destroy(this);
    }

    public void touch(){
        this.touched = true;
    }
}
