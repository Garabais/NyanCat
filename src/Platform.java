import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Platform extends Rectangle {

    protected int pastY;
    protected Game game;

    public Platform(int x, int y, int width, int height, Game game){
        super(x,y,width,height);
        this.pastY = y;
        this.game = game;
    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(this.x, this.y, this.width, this.height);
    }

    public void veticalTranslate(int move){
        if (move != 0){
            this.pastY = this.y;
            this.y += move;
        }

    }

    public int getPastY(){
        return pastY;
    }

    public void selfDestroy(){
        this.game.destroyPlatform(this);
    }
}
