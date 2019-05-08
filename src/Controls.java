import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controls implements KeyListener {

    NyanCat player;

    private int jump;

    private boolean active;

    Controls(NyanCat player) {
        this(KeyEvent.VK_SPACE, player);
    }

    Controls(char jump, NyanCat player) {
        this(KeyEvent.getExtendedKeyCodeForChar(jump), player);
    }

    private Controls(int jump, NyanCat player) {
        this.player = player;
        this.jump = jump;
        this.active = false;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!this.active && e.getExtendedKeyCode() == this.jump){
            this.active = true;
            player.setJumpable();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getExtendedKeyCode() == this.jump){
            this.active = false;
        }
    }
}
