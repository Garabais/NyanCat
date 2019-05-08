import java.awt.Color;
import java.awt.Graphics;

public class BreakablePlattform extends Platform {

    private int counter;

    public BreakablePlattform(int x, int y, int width, int height, Game game) {
        super(x, y, width, height, game);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(this.x, this.y, this.width, this.height);
    }
}
