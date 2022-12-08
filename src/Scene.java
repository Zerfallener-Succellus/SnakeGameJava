import java.awt.Graphics;

/*Essa classe serve para diferenciar as telas "Cenas"*/

public abstract class Scene {
    public abstract void update(double dt);

    public abstract void draw(Graphics g);
}
