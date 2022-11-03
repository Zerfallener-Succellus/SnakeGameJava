public class Main {
    public static void main(String[] args){
Window window = new Window(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.SCREEN_TITLE);

Thread thread = new Thread(window);
thread.start();
    }

}