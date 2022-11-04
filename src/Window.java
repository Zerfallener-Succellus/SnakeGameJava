import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.*;

public class Window extends JFrame implements Runnable {

    public boolean isRunning = true;

    public static int currentState;
    public static Scene currentScene;

    public static KL keyListener = new KL();

    public Window(int width, int heigth, String title){
        setSize(width, heigth);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(Window.keyListener);

        isRunning = true;
        Window.changeState(0);
    }

    /*Mudar de "Scene"*/
    public static void changeState(int newState){
        Window.currentState = newState;
        /*Esse switch serve para mostrar qual cena "buildar" baseado em que state está*/
        switch(Window.currentState){
            case 0:
                Window.currentScene = new MenuScene(Window.keyListener);
                break;
            case 1:
                Window.currentScene = new GameScene();
                break;
            default:
                System.out.println("Unknow Scene.");
                Window.currentScene = null;
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
         double lastFrameTime = 0.0;
         try{
             while (isRunning){
                 double time = Time.getTime();
                 double deltaTime = time - lastFrameTime;
                 lastFrameTime = time;

                 update(deltaTime);

             }

         }catch(Exception e){
             e.printStackTrace();
         }
    }
}
