import javax.swing.JFrame;

public class Window extends JFrame implements Runnable {

    public Window(int width, int heigth, String title){
        setSize(width, heigth);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void run() {

    }
}
