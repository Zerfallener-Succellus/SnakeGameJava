import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Snake {
    public Rect[] body = new Rect[100];
    public double bodyWidth, bodyHeight;

    public int size;
    public int tail = 0;
    public int head = 0;

    public Snake (int size,double startX,double startY, double bodyWidth, double bodyHeight){
        this.size = size;
        this.bodyWidth = bodyWidth;
        this.bodyHeight = bodyHeight;



        for (int i=0; i < size; i++) {
            Rect bodyPiece = new Rect( startX + i * bodyWidth, startY, bodyWidth, bodyHeight);
            body[i] = bodyPiece;
            head++;
        }
        head--;
    }

    public void draw(Graphics2D g2) {
        for (int i = tail; i != head; i= (i + 1) % body.length){
            Rect piece = body[i];
            double subWidth = (piece.width - 6.0) / 2.0;
            double subHeight = (piece.height - 6.0) / 2.0;

            g2.setColor(Color.BLACK);



            g2.fill(new Rectangle2D.Double(piece.x + 6.0, piece.y + 4.0,subWidth - 4.0,subHeight- 4.0));
            g2.fill(new Rectangle2D.Double(piece.x + 3.0 + subWidth, piece.y + 4.0,subWidth- 4.0,subHeight- 4.0));
            g2.fill(new Rectangle2D.Double(piece.x + 9.0 + subWidth, piece.y + 4.0,subWidth- 4.0,subHeight- 4.0));

            g2.fill(new Rectangle2D.Double(piece.x + 0.0, piece.y + 2.0 + subHeight,subWidth - 4.0,subHeight- 4.0));
            g2.fill(new Rectangle2D.Double(piece.x + 6.0, piece.y + 2.0+ subHeight,subWidth - 4.0,subHeight- 4.0));
            g2.fill(new Rectangle2D.Double(piece.x + 3.0 + subWidth, piece.y + 2.0+ subHeight,subWidth- 4.0,subHeight- 4.0));


            g2.setColor(Color.WHITE);

            g2.fill(new Rectangle2D.Double(piece.x + 0.0, piece.y + 4.0,subWidth - 4.0,subHeight- 4.0));

            g2.fill(new Rectangle2D.Double(piece.x + 9.0 + subWidth, piece.y + 2.0+ subHeight,subWidth- 4.0,subHeight- 4.0));


            /*g2.fill(new Rectangle2D.Double(piece.x + 12.0 + subWidth, piece.y + 4.0 ,subWidth - 2.0,subHeight - 2.0));*/
        }
    }


}
