import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.*;

public class Window extends JFrame implements Runnable {

    public boolean isRunning = true;

    public Window(int width, int heigth, String title){
        setSize(width, heigth);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        isRunning = true;
    }

    public void update(double dt){
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        getGraphics().drawImage(dbImage, 0,0,this);

    }

    public void draw(Graphics g) {
        /* Fundo e cor do fundo */
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(new Color(149,179,0));
        g2.fillRect(0,0,getWidth(),getHeight());

    }


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
