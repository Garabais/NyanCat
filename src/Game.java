import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener{

    private Timer timer;
    private Music music;
    private NyanCat player;
    private ArrayList<Platform> platforms;
    private int jumpForce;

    public Game(){
        super();
        this.setPreferredSize(new Dimension(800,600));
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.music = new Music("music/Nyan Cat.wav");
        this.player = new NyanCat((int) (this.getPreferredSize().getWidth() / 2), (int) (this.getPreferredSize().getHeight() / 2), this);
        this.jumpForce = 0;
        this.platforms = new ArrayList<>();
        this.platforms.add(new Platform(0,400,1000,10,this));
        this.platforms.add(new Platform(0,200,1000,10,this));



        this.timer = new Timer(1000/60, this);
        this.timer.setRepeats(true);
        this.timer.start();

        this.addKeyListener(new Controls(this.player));
    }

    public void refresh(){
        checkCollisions();
        if (!player.getColliding()){
            this.appyGravity();
        }
        this.player.jump();
        this.refreshVerticalPositions();
    }

    public void checkCollisions(){
        this.player.resetCollision();
        for (int i = this.platforms.size() - 1; i >= 0; i--){
            this.player.checkCollision(this.platforms.get(i));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.player.draw(g);

        for (int i = this.platforms.size() - 1; i >= 0; i--){
            this.platforms.get(i).draw(g);
        }
    }

    public void appyGravity(){
        for (int i = this.platforms.size() - 1; i >= 0; i--){
            this.platforms.get(i).veticalTranslate(-2);
        }
    }

    public void applyJumpForce(int jumpForce){
        this.jumpForce += jumpForce;
    }

    public void refreshVerticalPositions(){
        int move = (int) Math.ceil(this.jumpForce * .1);
        for (int i = this.platforms.size() - 1; i >= 0; i--){
            this.platforms.get(i).veticalTranslate(move);
        }
        this.jumpForce -= move;
        this.jumpForce -= move;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.refresh();
        this.repaint();
    }
}
