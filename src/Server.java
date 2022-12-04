import java.awt.Graphics;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;



public class Server extends Scene {

    

    

    private DatagramSocket datagramSocket;
    private byte[] buffer = new byte [256];
    
    public Server(DatagramSocket datagramSocket){
        this.datagramSocket = datagramSocket;
    }
    
    

    public void receiveThenSend(){
        while (true){
            try{

                

                
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                InetAddress inetAddress = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("PONTOS DO CLIENTE 1: "+messageFromClient);
                String olhaosponto = messageFromClient;
                Food.getInstance().setServerpoints(olhaosponto);
                datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
                datagramSocket.send(datagramPacket);
                
            } catch(IOException e) {
                e.printStackTrace();
                break;
            }
            
        }

    }
    

    public static void main(String[] args) throws SocketException {
       
        Window window = Window.getWindow();
        Thread thread = new Thread(window);
        thread.start();

        
        DatagramSocket datagramSocket = new DatagramSocket(1234);
        Server server = new Server(datagramSocket);
        server.receiveThenSend();
    }

    @Override
    public void update(double dt) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        
    }

}

