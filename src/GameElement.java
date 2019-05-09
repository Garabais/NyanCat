import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameElement extends Rectangle {

    protected Game game;

    public GameElement(int x, int y, int width, int height, Game game){
        super(x,y,width,height);
        this.game = game;
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(this.x, this.y, this.width, this.height);
    }

    public void veticalTranslate(int move){
        this.y += move;

    }

    public void horizontalTranslate(int move){
        this.x -= move;
    }


    public void selfDestroy(){

    }
}
