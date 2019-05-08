import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class NyanCat extends Rectangle {

    private int jumpCount;
    private Game game;
    private boolean ground, jumpable;

    public NyanCat(int x, int y, Game game){
        super(x, y, 50, 50);
        this.jumpCount = 2;
        this.game = game;
        this.ground = false;
        this.jumpable = false;
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(this.x, this.y, this.width, this.height);
    }


    public void setJumpable(){
        this.jumpable = true;
    }
    public void jump(){
        if (this.jumpable == true){
            if (this.jumpCount > 0){
                this.jumpCount--;
                this.game.applyJumpForce(300);
            }

            this.jumpable = false;
        }

    }

    public <T extends Platform> void checkCollision(T platform){
        if (this.intersects(platform) && platform.getPastY() >= this.y + this.getHeight()){
            this.ground = true;
            this.jumpCount = 2;
        }
    }

    public <T extends BreakablePlattform> void checkCollision(T platform){
        if (this.intersects(platform) && platform.getPastY() >= this.y + this.getHeight()){
            this.ground = true;
            this.jumpCount = 2;
            platform.touch();
        }
    }

    public void resetCollision(){
        this.ground = false;
    }

     public boolean getColliding(){
        return this.ground;
     }
}
