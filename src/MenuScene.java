import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

/*Dentro dessa classe pode se fazer qualquer update ou "draw" que queira que apareça na "MenuScene"*/
public class MenuScene extends Scene {
    int i = 0;

    public KL keyListener;
    public MenuScene(KL keyListener){
this.keyListener = keyListener;
    }

    @Override
    public void update(double dt) {
if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
    System.out.println("UP ARROW IS PRESSED ;D");
}

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

    }
}
