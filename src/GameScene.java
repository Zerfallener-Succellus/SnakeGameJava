import java.awt.*;

public class GameScene extends Scene{

    @Override
    public void update(Double dt) {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

    }
}
