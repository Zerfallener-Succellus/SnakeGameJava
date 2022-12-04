import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GameScene extends Scene{
    
    Rect background, foreground;
    Snake snake;
    

    



    KL keyListener;
    
   public int pointsfinal;

   public String pontosfinais;

    public String getPontosfinais() {
    return pontosfinais;
}



public void setPontosfinais(String pontosfinais) {
    this.pontosfinais = pontosfinais;
}



    public int getPointsfinal() {
	return pointsfinal;
}



public void setPointsfinal(int pointsfinal) {
	this.pointsfinal = pointsfinal;
}






	public Food food = new Food(background, snake, pointsfinal, pointsfinal, null);

    
   

    public GameScene(KL keyListener){
        
        foreground = new Rect(24,48,Constants.TILE_WIDTH * 31, Constants.TILE_WIDTH*22,Direction.NONE,Direction.LAST);
        background = new Rect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT,Direction.NONE,Direction.LAST);
       
        /*Isso cira um grid de 31 colunas e 22 rows todos com largura e altura de 24 :D*/
        snake = new Snake(0.1,3,48,48 + 24,24,24,foreground);
        this.keyListener = keyListener;
        food = new Food(foreground, snake, 12,12,Color.BLACK);
        food.spawn();
        
        
        
    }

   


@Override
    public void update(double dt) {
        if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            snake.changeDirecton(Direction.UP,Direction.LAST);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            snake.changeDirecton(Direction.DOWN,Direction.LAST);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            snake.changeDirecton(Direction.RIGHT,Direction.LAST);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            snake.changeDirecton(Direction.LEFT,Direction.LAST);
        }

      if (!food.isSpawned){
        food.spawn();
        
    }

      food.update(dt);
        snake.update(dt);
        

    }

    




    @Override
    public void draw(Graphics g) {
        Stroke stroke = new BasicStroke(2f);
        Graphics2D g2 = (Graphics2D)g;


        g2.setColor(new Color(178,189,8,255));
        g2.fill(new Rectangle2D.Double(background.x,background.y,background.width,background.height));

        
        Font myFont = new Font("Monospaced",Font.CENTER_BASELINE,20);
		g2.setFont(myFont);
        

        
		g2.setFont(myFont);
        g2.setColor(Color.BLACK);
        g2.drawString("POINTS CLI:"+Food.getInstance().getServerpoints(), 240, 596);
        

        
        
        


        g2.setStroke(stroke);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(7f));
        g2.draw(new Rectangle2D.Double(foreground.x,foreground.y,foreground.width,foreground.height));
        
        snake.draw(g2);
        food.draw(g2);
}
    
    
    
    private static GameScene instancia;
    private GameScene(){

    }
    
    public static synchronized GameScene getInstance(){
    if(instancia == null){
        instancia = new GameScene();
    }
    return instancia;
    }
    

}