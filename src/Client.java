import java.awt.Graphics;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Client extends Scene {
    
    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    private byte[] buffer;

    public Client(DatagramSocket datagramSocket, InetAddress inetAddress){
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }

    public void sendThenReceive(){
        while(true){
            try{
                String messageToSend = String.valueOf(Food.getInstance().getPoints());
                buffer = messageToSend.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1239);
                String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                
                datagramSocket.send(datagramPacket);
                datagramSocket.receive(datagramPacket);

               

                String messageFromServer = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("PONTOS CLIENTE 1: " +messageFromServer);
                Food.getInstance().setClientpoints(messageFromServer);
                Food.getInstance().setClientpointsdois(messageFromClient);
                
            } catch(IOException e){
                e.printStackTrace();
                break;
            }
        }
        
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        

        Window window = Window.getWindow();
        Thread thread = new Thread(window);
        
        





        thread.start();
        



        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName("localhost");
        Client client = new Client(datagramSocket, inetAddress);
        System.out.println("Mandando os pacotes do datagrama para o Server.");
        client.sendThenReceive();
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
