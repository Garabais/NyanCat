import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

public class NyanCat extends GameElement {

    public static final Image texture1 = new ImageIcon("Textures/Nyan1.png").getImage();
    public static final Image texture2 = new ImageIcon("Textures/Nyan2.png").getImage();
    public static final Image texture3 = new ImageIcon("Textures/Nyan3.png").getImage();
    public static final Image texture4 = new ImageIcon("Textures/Nyan4.png").getImage();
    public static final Image texture5 = new ImageIcon("Textures/Nyan5.png").getImage();
    public static final Image texture6 = new ImageIcon("Textures/Nyan6.png").getImage();

    public static final Image textureF1 = new ImageIcon("Textures/NyanF1.png").getImage();
    public static final Image textureF2 = new ImageIcon("Textures/NyanF2.png").getImage();
    public static final Image textureF3 = new ImageIcon("Textures/NyanF3.png").getImage();
    public static final Image textureF4 = new ImageIcon("Textures/NyanF4.png").getImage();


    private int jumpCount, tileCounter, fallAccumulator;
    private boolean ground, jumpable;

    int c , state;

    public NyanCat(int x, int y, Game game){
        super(x, y, 66, 40, game);
        this.jumpCount = 2;
        this.ground = false;
        this.jumpable = false;
        this.tileCounter = 1;
        this.state = 0;
        this.c = 0;
        this.fallAccumulator = 0;
    }

    @Override
    public void draw(Graphics g) {
//        System.out.println(fallAccumulator);
        if (c++ % 6 == 0) {
            state++;
        }

        if (this.fallAccumulator < 250) {
            switch (state % 6) {
                case 0:
                    g.drawImage(NyanCat.texture1, this.x, this.y, this.width, this.height, this.game);
                    break;
                case 1:
                    g.drawImage(NyanCat.texture2, this.x, this.y, this.width, this.height, this.game);
                    break;
                case 2:
                    g.drawImage(NyanCat.texture3, this.x, this.y, this.width, this.height, this.game);
                    break;
                case 3:
                    g.drawImage(NyanCat.texture4, this.x, this.y, this.width, this.height, this.game);
                    break;
                case 4:
                    g.drawImage(NyanCat.texture5, this.x, this.y, this.width, this.height, this.game);
                    break;
                case 5:
                    g.drawImage(NyanCat.texture6, this.x, this.y, this.width, this.height, this.game);
                    break;
            }
        } else {
            switch (state % 4) {
                case 0:
                    g.drawImage(NyanCat.textureF1, this.x, this.y, this.width, this.height, this.game);
                    break;
                case 1:
                    g.drawImage(NyanCat.textureF2, this.x, this.y, this.width, this.height, this.game);
                    break;
                case 2:
                    g.drawImage(NyanCat.textureF3, this.x, this.y, this.width, this.height, this.game);
                    break;
                case 3:
                    g.drawImage(NyanCat.textureF4, this.x, this.y, this.width, this.height, this.game);
                    break;
            }
        }

        if (this.c % 2 == 0){
            this.game.addRainbow(new RainbowTile(this.x + 8, this.y + (this.tileCounter++ % 2 == 0 ? 3 : 1), this.game));
        }
    }


    public void setJumpable(){
        if (this.fallAccumulator < 250){
            this.jumpable = true;
        }
//        System.out.println("j");
        if (this.game.stop){
//            System.out.println("j");
            this.game.newGame();
        }
    }
    public void jump(){
        if (this.jumpable){
            if (this.jumpCount > 0){
                this.jumpCount--;
                this.game.applyJumpForce(200);
//                this.resetAccumulator();
            }
            this.jumpable = false;
        }

    }

    public void checkCollision(Platform platform){
        if (this.intersects(platform) && platform.getPastY() >= this.y + this.height){
            this.ground = true;
            this.jumpCount = 2;
            platform.touch();
            this.resetAccumulator();
        }
    }

    public void checkCollision(Multiplier multi){
        if (this.intersects(multi)){
            multi.addToMultiplier();
            multi.selfDestroy();
            this.game.resetMultiplierAccumulator();
        }
    }

    public void resetCollision(){
        this.ground = false;
    }

     public boolean getColliding(){
        return this.ground;
     }

     public void addToAccumulator(int num){

        this.fallAccumulator += num;
     }

     public void resetAccumulator(){
        this.fallAccumulator = 0;
     }
}
