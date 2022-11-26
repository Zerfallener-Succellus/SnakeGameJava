public class Rect {
    public double x, y, width, height;
    public Direction direction = Direction.RIGHT;
    public Direction lastDirection;
    public Direction newDirection;

    public Rect(double x, double y, double width, double height, Direction direction, Direction lastDirection) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        this.lastDirection = lastDirection;
    }

   


}

