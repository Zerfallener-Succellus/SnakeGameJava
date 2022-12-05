import java.awt.*;

import javax.swing.JLabel;

public class Food {
    public Rect background;
    public Snake snake;
    public int width, height;
    public Color color;
    public Rect rect;
    
    
    private int points;
    private String clientpoints;
    private String clientpointsdois;

    public String getClientpointsdois() {
        return clientpointsdois;
    }

    public void setClientpointsdois(String clientpointsdois) {
        this.clientpointsdois = clientpointsdois;
    }

    public String getClientpoints() {
        return clientpoints;
    }

    public void setClientpoints(String clientpoints) {
        this.clientpoints = clientpoints;
    }




private static Food instancia;
    



    public int xPadding;

    public boolean isSpawned = false;

    

    public Food(Rect background, Snake snake, int width, int height, Color color) {
        this.background = background;
        this.snake = snake;
        this.width = width;
        this.height = height;
        this.color = color;
        this.rect = new Rect(0, 0, width, height,Direction.NONE,Direction.LAST);

        xPadding = (int)((Constants.TILE_WIDTH - this.width) / 2.0);
    }

    public void spawn() {
        do {
            double randX = (int)(Math.random() * (int)(background.width / Constants.TILE_WIDTH)) * Constants.TILE_WIDTH + background.x;
            double randY = (int)(Math.random() * (int)(background.height / Constants.TILE_WIDTH)) * Constants.TILE_WIDTH + background.y;
            this.rect.x = randX;
            this.rect.y = randY;
        } while(snake.intersectingWithRect(this.rect));
        this.isSpawned = true;
    }

    public void update(double dt) {
        if (snake.intersectingWithRect(this.rect)) {
            snake.grow();
            this.rect.x = -100;
            this.rect.y = -100;
            isSpawned = false;
            Food.getInstance().setPoints(points = points + 10);
            
            
            
        }
    }

    

    
    private Food(){

    }
    
    public static synchronized Food getInstance(){
    if(instancia == null){
        instancia = new Food();
    }
    return instancia;
    }
    
    
    public int getPoints() {
        return points;
    }

    
    public void setPoints(int points) {
        this.points = points;
    }

    


    public void draw(Graphics2D g2) {
        
        g2.setColor(color);
        g2.fillRect((int)this.rect.x + xPadding, (int)this.rect.y + xPadding, width, height);
    }

    
    

}