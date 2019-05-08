import java.awt.Color;
import java.awt.Graphics;

public class BreakablePlattform extends Platform {

    private int counter;
    private boolean touched;

    public BreakablePlattform(int x, int y, int width, int height, Game game) {
        super(x, y, width, height, game);
        this.counter = 1;
        this.touched = false;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(this.x, this.y, this.width, this.height);

        if (touched){
            if (counter++ % 60 == 0){
                this.selfDestroy();
            }
        }
    }
    public void touch(){
        this.touched = true;
    }
}
