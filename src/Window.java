import javax.swing.JFrame;

public class Window extends JFrame {

    public Window(){
        super("Nyan Cat");
        this.add(new Game());
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }
}
