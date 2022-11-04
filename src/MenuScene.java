import java.awt.*;

/*Dentro dessa classe pode se fazer qualquer update ou "draw" que queira que apareça na "MenuScene"*/
public class MenuScene extends Scene {

    @Override
    public void update(Double dt) {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

    }
}
