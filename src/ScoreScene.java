import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/*Dentro dessa classe pode se fazer qualquer update ou "draw" que queira que apareça na "MenuScene"*/
public class ScoreScene extends Scene {

    public KL keyListener;
    public ML mouseListener;
    public BufferedImage play, playPressed, exit, exitPressed;

    public Rect playRect, exitRect;

    public BufferedImage playCurrentImage, exitCurrentImage;

    public ScoreScene(KL keyListener, ML mouseListener) {

        this.keyListener = keyListener;
        this.mouseListener = mouseListener;

        try {
            BufferedImage spritesheet = ImageIO.read(new File("assets/menuButtons.png"));
            play = spritesheet.getSubimage(166, 206, 54, 56);
            playPressed = spritesheet.getSubimage(222, 206, 54, 56);
            exit = spritesheet.getSubimage(351, 206, 54, 56);
            exitPressed = spritesheet.getSubimage(289, 206, 54, 56);

        } catch (Exception e) {
            e.printStackTrace();
        }

        playCurrentImage = play;
        exitCurrentImage = exit;

        playRect = new Rect(40, 515, 92, 88, Direction.RIGHT, Direction.LAST);
        exitRect = new Rect(660, 515, 92, 88, Direction.RIGHT, Direction.LAST);
    }

    @Override
    public void update(double dt) {

        if (keyListener.isKeyPressed(KeyEvent.VK_ENTER)) {
            Window.getWindow().changeState(1);
            Food.getInstance().setPoints(0);
            Snake.getInstance().setLost("no");
        } else if (keyListener.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            Window.getWindow().close();
        }

        /*
         * Código para o progama indentificar que se está passando o mouse sobre o enter
         * e mudar a sprite
         */
        if (mouseListener.getX() >= playRect.x && mouseListener.getX() <= playRect.x + playRect.width &&
                mouseListener.getY() >= playRect.y && mouseListener.getY() <= playRect.y + playRect.height) {
            playCurrentImage = playPressed;
            if (mouseListener.isPressed()) {
                Window.getWindow().changeState(1);
                
            Food.getInstance().setPoints(0);
            Snake.getInstance().setLost("no");
            }
        } else {
            playCurrentImage = play;

        }

        /*
         * Código para o progama indentificar que se está passando o mouse sobre o esc e
         * mudar a sprite
         */
        if (mouseListener.getX() >= exitRect.x && mouseListener.getX() <= exitRect.x + exitRect.width &&
                mouseListener.getY() >= exitRect.y && mouseListener.getY() <= exitRect.y + exitRect.height) {
            exitCurrentImage = exitPressed;
            if (mouseListener.isPressed()) {
                Window.getWindow().close();
            }

        } else {
            exitCurrentImage = exit;
        }

    }

    @Override
    public void draw(Graphics g) {

        int pontosServer = Integer.valueOf(Food.getInstance().getClientpoints());
        int pontosCliente = Integer.valueOf(Food.getInstance().getClientpointsdois());

        if (pontosServer > pontosCliente) {
            g.setColor(new Color(178, 189, 8, 255));
            g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
            g.drawImage(playCurrentImage, (int) playRect.x, (int) playRect.y, (int) playRect.width,
                    (int) playRect.height,
                    null);
            g.drawImage(exitCurrentImage, (int) exitRect.x, (int) exitRect.y, (int) exitRect.width,
                    (int) exitRect.height,
                    null);

            FontManager fontManager = new FontManager();
           
            Font myFont = new Font("Monospaced", Font.CENTER_BASELINE, 60);
            g.setFont(myFont);
            g.setColor(Color.BLACK);
            g.drawString("SERVIDOR VENCEU", 60, 296);
            g.drawString("YOUR SCORE:" + String.valueOf(Food.getInstance().getPoints()), 60, 150);
        } else if (pontosServer < pontosCliente) {
            g.setColor(new Color(178, 189, 8, 255));
            g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
            g.drawImage(playCurrentImage, (int) playRect.x, (int) playRect.y, (int) playRect.width,
                    (int) playRect.height,
                    null);
            g.drawImage(exitCurrentImage, (int) exitRect.x, (int) exitRect.y, (int) exitRect.width,
                    (int) exitRect.height,
                    null);

            FontManager fontManager = new FontManager();
           
            Font myFont = new Font("Monospaced", Font.CENTER_BASELINE, 60);
            g.setFont(myFont);
            g.setColor(Color.BLACK);
            g.drawString("CLIENTE VENCEU", 60, 296);
            g.drawString("YOUR SCORE:" + String.valueOf(Food.getInstance().getPoints()), 160, 150);
        } else if (pontosCliente == pontosServer) {
            g.setColor(new Color(178, 189, 8, 255));
            g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
            g.drawImage(playCurrentImage, (int) playRect.x, (int) playRect.y, (int) playRect.width,
                    (int) playRect.height,
                    null);
            g.drawImage(exitCurrentImage, (int) exitRect.x, (int) exitRect.y, (int) exitRect.width,
                    (int) exitRect.height,
                    null);

            FontManager fontManager = new FontManager();
           
            Font myFont = new Font("Monospaced", Font.CENTER_BASELINE, 60);
            g.setFont(myFont);
            g.setColor(Color.BLACK);
            g.drawString("HOUVE UM EMPATE", 60, 296);
            g.drawString("YOUR SCORE:" + String.valueOf(Food.getInstance().getPoints()), 160, 150);

        }
    }
}
