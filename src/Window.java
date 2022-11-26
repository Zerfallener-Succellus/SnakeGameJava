import javax.swing.JFrame;
import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.awt.event.MouseListener;
import java.awt.image.*;

public class Window extends JFrame implements Runnable {

    public static Window window = null;
    public boolean isRunning = true;

    public int currentState;
    public Scene currentScene;

    public KL keyListener = new KL();
    public ML mouseListener = new ML();

    public Window(int width, int height, String title){
        setSize(width, height);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(keyListener); /*qqr coisa referencia o windown aqui window.*/
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);

        isRunning = true;
        changeState(0);
    }


    public static Window getWindow() {
        if (Window.window == null) {
            Window.window = new Window(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.SCREEN_TITLE);
        }

        return Window.window;
    }


    public void close(){
     isRunning = false;
    }


    /*Mudar de "Scene"*/
    public void changeState(int newState){
        currentState = newState;
        /*Esse switch serve para mostrar qual cena "buildar" baseado em que state está*/
        switch(currentState){
            case 0:
                currentScene = new MenuScene(keyListener, mouseListener);
                break;
            case 1:
                currentScene = new GameScene(keyListener);
                break;
                case 2:
                currentScene = new ScoreScene(keyListener, mouseListener);
                break;
            default:
                System.out.println("Unknow Scene.");
                currentScene = null;
                break;
        }
    }

    /* Janela */
    public void update(double dt){
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        getGraphics().drawImage(dbImage, 0,0,this);

        currentScene.update(dt);

    }

    public void draw(Graphics g) {
        /* Fundo e cor do fundo */
        Graphics2D g2 = (Graphics2D)g;
        currentScene.draw(g);

    }

/*Game Loop*/
    @Override
    public void run() {
        Instant lastFrameTime = Instant.now();
        try {
            while (isRunning) {
                Instant time = Instant.now();
                double deltaTime = Duration.between(lastFrameTime, time).toNanos() * 10E-10;
                lastFrameTime = Instant.now();

                double deltaWanted = 0.0167;
                update(deltaWanted);
                long msToSleep = (long)((deltaWanted - deltaTime) * 1000);
                if (msToSleep > 0) {
                    Thread.sleep(msToSleep);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        this.dispose();
    }
}
