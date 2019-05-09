import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener{

    private Timer timer;
    private Music music;
    private NyanCat player;
    private ArrayList<Platform> platforms;
    private ArrayList<RainbowTile> rainbow;
    private ArrayList<Multiplier> multipliers;
    private ArrayList<Star> stars;
    private int jumpForce, verticalHeight, score, multiplier, multiplierAccumulator;
    public boolean stop;
    private Controls controls;
    public static final int MAX_SIZE = 2000;

    public Game(){
        super();
        this.setPreferredSize(new Dimension(800,600));
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.setBackground(new Color(15,77,143));

        this.reload();
    }

    public void refresh(){
        this.multiplierAccumulator++;
        this.moveForward();
        this.checkCollisions();
        if (!player.getColliding()){
            this.applyGravity();
        }
        this.player.jump();
        this.refreshVerticalPositions();
//        System.out.println(score);
        this.generatePlatforms();
        this.generateMultipliers();
        this.generateStars();

        if (this.verticalHeight + this.player.getX() < 0){
            this.gameOver();
        }

        for (int i = this.platforms.size() - 1; i > 0; i--) {
            if (this.player.getY() + this.player.getHeight() < this.platforms.get(i).getY() - this.verticalHeight || this.platforms.get(i).getY() + Game.MAX_SIZE - this.verticalHeight <= this.player.getY() + this.player.getHeight()){
                this.destroy(this.platforms.get(i));
            }
        }

        for (int i = this.multipliers.size() - 1; i > 0; i--) {
            if (this.player.getY() + this.player.getHeight() < this.multipliers.get(i).getY() - this.verticalHeight || this.multipliers.get(i).getY() + Game.MAX_SIZE - this.verticalHeight <= this.player.getY() + this.player.getHeight()){
                this.destroy(this.multipliers.get(i));
            }
        }

        for (int i = this.stars.size() - 1; i > 0; i--) {
            if (this.player.getY() + this.player.getHeight() < this.stars.get(i).getY() - this.verticalHeight || this.stars.get(i).getY() + Game.MAX_SIZE - this.verticalHeight <= this.player.getY() + this.player.getHeight()){
                this.destroy(this.stars.get(i));
            }
        }

        if (verticalHeight > Game.MAX_SIZE){
            int move = Game.MAX_SIZE - this.verticalHeight;

            for (int i = this.stars.size() - 1; i >= 0; i--){
                this.stars.get(i).veticalTranslate(move);
            }

            for (int i = this.platforms.size() - 1; i >= 0; i--){
                this.platforms.get(i).veticalTranslate(move);
            }



            for (int i = this.rainbow.size() - 1; i >= 0; i--){
                this.rainbow.get(i).veticalTranslate(move);
            }

            for (int i = this.multipliers.size() - 1; i >= 0; i--){
                this.multipliers.get(i).veticalTranslate(move);
            }


            this.verticalHeight += move;

        }

//        System.out.println(multiplierAccumulator);
        if (this.multiplierAccumulator > 600){
            this.resetMultiplierAccumulator();
            this.multiplier = 100;
        }
        this.addScore(multiplier / 100);
    }


    public void resetMultiplierAccumulator(){
        this.multiplierAccumulator = 0;
    }
    private void generatePlatforms(){
        Random rnd = new Random();
        if (rnd.nextInt(100) < 10){
//            int num = 1 + rnd.nextInt(2);
//
//            for (int i = 0; i < num; i++) {
//                generatePlatform();
//            }
            generatePlatform();
        }
    }

    private void generatePlatform(){
        this.generatePlatform(false);
    }

    private void generatePlatform(boolean start){
        Random rnd = new Random();
        Platform plt;
        boolean add = false;

        while (!add){
            int x;
            if (start) {
                x = rnd.nextInt(1000);
            } else {
                x = 1000;
            }
            int y = rnd.nextInt(Game.MAX_SIZE) - this.verticalHeight;
            int w = rnd.nextInt(600) + 100;

            int p = rnd.nextInt(100);
            if ( p < 70) {
                plt = new Platform(x, y, w, this);
            } else if (p < 85){
                plt = new BreakablePlatform(x, y, w, this);
            } else if (p < 95){
                plt = new GoldenPlataform(x, y, w, this);
            } else {
                plt = new JumpablePlatform(x, y, w, this);

            }

            add = true;

            for (int i = this.platforms.size() - 1; i >= 0; i--){


                if (this.platforms.get(i).intersects(plt) || this.player.intersects(plt) || Math.abs(this.platforms.get(i).getY() - plt.getY()) < 16) {
                    add = false;
                }

//                System.out.println(Math.abs(this.platforms.get(i).getY() - plt.getY()) < 64);

            }

//            System.out.println(add);
            if (add) {
                this.platforms.add(plt);
            }
        }
    }

    private void generateMultiplier(){
        this.generateMultiplier(false);
    }

    private void generateMultiplier(boolean start){
        Random rnd = new Random();
        Multiplier multi;
        boolean add = false;

        while (!add){
            int x;
            if (start) {
                x = rnd.nextInt(1000);
            } else {
                x = 1000;
            }
            int y = rnd.nextInt(Game.MAX_SIZE) - this.verticalHeight;

            int p = rnd.nextInt(100);
            if ( p < 60) {
                multi = new Sausage(x, y,this);
            } else if (p < 90){
                multi = new Donut(x, y,this);
            } else {
                multi = new Milk(x,y,this);
            }

            add = true;

            for (int i = this.platforms.size() - 1; i >= 0; i--){
                if (this.platforms.get(i).intersects(multi) || this.player.intersects(multi)) {
                    add = false;
                }
            }

            for (int i = this.multipliers.size() - 1; i >= 0; i--){
                if (this.multipliers.get(i).intersects(multi) || this.player.intersects(multi)) {
                    add = false;
                }
            }

            if (add) {
                this.multipliers.add(multi);
            }
        }
    }

    private void generateMultipliers(){
        Random rnd = new Random();
        if (rnd.nextInt(100) < 15){
//
            generateMultiplier();
        }
    }

    private void generateStars(){
        Random rnd = new Random();
        if (rnd.nextInt(100) < 30){
            this.generateStar();
        }
    }

    private void generateStar(){
        Random rnd = new Random();
        this.stars.add(new Star(rnd.nextInt(this.getWidth() + 1), rnd.nextInt(this.getWidth() + 1), this));
    }
    private void moveForward(){
        for (int i = this.platforms.size() - 1; i >= 0; i--){
            this.platforms.get(i).horizontalTranslate(4);
            if (this.platforms.get(i).getX() + this.platforms.get(i).getWidth() < 0){
                this.destroy(this.platforms.get(i));
            }
        }
        for (int i = this.rainbow.size() - 1; i >= 0; i--){
            this.rainbow.get(i).horizontalTranslate(4);
            if (this.rainbow.get(i).getX() + this.rainbow.get(i).getWidth() < 0){
                this.destroy(this.rainbow.get(i));
            }
        }

        for (int i = this.multipliers.size() - 1; i >= 0; i--){
            this.multipliers.get(i).horizontalTranslate(4);
            if (this.multipliers.get(i).getX() + this.multipliers.get(i).getWidth() < 0){
                this.destroy(this.multipliers.get(i));
            }
        }

        for (int i = this.stars.size() - 1; i >= 0; i--){
            this.stars.get(i).horizontalTranslate(4);
            if (this.stars.get(i).getX() + this.stars.get(i).getWidth() < 0){
                this.destroy(this.stars.get(i));
            }
        }

    }

    public void checkCollisions(){
        this.player.resetCollision();
        for (int i = this.platforms.size() - 1; i >= 0; i--){
            if (this.platforms.get(i) instanceof BreakablePlatform){
                this.player.checkCollision((BreakablePlatform) this.platforms.get(i));
            }else {
                this.player.checkCollision(this.platforms.get(i));
            }
        }
        for (int i = this.multipliers.size() - 1; i >= 0; i--){

            this.player.checkCollision(this.multipliers.get(i));
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = this.stars.size() - 1; i >= 0; i--){
            this.stars.get(i).draw(g);
        }

        for (int i = this.platforms.size() - 1; i >= 0; i--){
            this.platforms.get(i).draw(g);
        }

        for (int i = this.multipliers.size() - 1; i >= 0; i--){
            this.multipliers.get(i).draw(g);
        }

        for (int i = this.rainbow.size() - 1; i >= 0; i--){
            this.rainbow.get(i).draw(g);
        }

        this.player.draw(g);

        g.setColor(Color.WHITE);
        g.drawString("Score: " + this.score,15, 20);
        g.drawString("X: " + this.multiplier / 100,15, 35);

    }

    public void applyGravity(){

        for (int i = this.platforms.size() - 1; i >= 0; i--){
            this.platforms.get(i).veticalTranslate(-2);
        }

        for (int i = this.multipliers.size() - 1; i >= 0; i--){
            this.multipliers.get(i).veticalTranslate(-2);
        }

        for (int i = this.rainbow.size() - 1; i >= 0; i--){
            this.rainbow.get(i).veticalTranslate(-2);
        }

        for (int i = this.stars.size() - 1; i >= 0; i--){
            this.stars.get(i).veticalTranslate(-2);
        }

        this.player.addToAccumulator(2);
        verticalHeight -=2;
    }

    public void applyJumpForce(int jumpForce){
        this.jumpForce += jumpForce;
    }

    public void refreshVerticalPositions(){
        int move = (int) Math.ceil(this.jumpForce * .1);


        for (int i = this.platforms.size() - 1; i >= 0; i--){
            this.platforms.get(i).veticalTranslate(move);
        }

        for (int i = this.multipliers.size() - 1; i >= 0; i--){
            this.multipliers.get(i).veticalTranslate(move);
        }

        for (int i = this.stars.size() - 1; i >= 0; i--){
            this.stars.get(i).veticalTranslate(move);
        }

        for (int i = this.rainbow.size() - 1; i >= 0; i--){
            this.rainbow.get(i).veticalTranslate(move);
        }

        this.jumpForce -= move;
        verticalHeight += move;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.refresh();
        this.repaint();
    }

    public void destroy(Platform platform){
        this.platforms.remove(platform);
    }

    public void destroy(RainbowTile rainbow){
        this.rainbow.remove(rainbow);
    }

    public void destroy(Multiplier multi){
        this.multipliers.remove(multi);
    }

    public void destroy(Star star){
        this.stars.remove(star);
    }
    public void newGame(){
        this.reload();
    }

    private void reload(){
        if (this.controls != null){
            this.removeKeyListener(this.controls);
        }
        this.music = new Music("music/Nyan Cat.wav");
        this.player = new NyanCat(75 , (int) (this.getPreferredSize().getHeight() / 2), this);
        this.jumpForce = 0;
        this.verticalHeight = Game.MAX_SIZE / 2 + 2;
        this.stop = false;
        this.multiplierAccumulator = 0;
        this.platforms = new ArrayList<>();
        this.rainbow = new ArrayList<>();
        this.multipliers = new ArrayList<>();
        this.stars = new ArrayList<>();
        this.score = 0;
        this.multiplier = 100;

        Random rnd = new Random();

        for (int i = 0; i < 50; i++) {
            this.generatePlatform(true);
            this.generateMultiplier(true);
        }


        this.timer = new Timer(1000/60, this);
        this.timer.setRepeats(true);
        this.timer.start();

        this.controls = new Controls(this.player);
        this.addKeyListener(new Controls(this.player));
    }

    public void addScore(int score){
        this.score += score;
    }

    private void gameOver(){
        this.stop = true;
        this.music.stop();
        this.timer.stop();
    }

    public void addMultiplier(int multiplier){
        this.multiplier += multiplier;
    }

    public void addRainbow(RainbowTile rainbow){
        this.rainbow.add(rainbow);
    }
}
