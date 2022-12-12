
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server extends Thread {

    private DatagramSocket datagramSocket;
    private byte[] buffer = new byte[256];

    public Server(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public static void main(String[] args) throws SocketException {

        Window window = Window.getWindow();
        Thread thread = new Thread(window);
        thread.start();

        DatagramSocket datagramSocket = new DatagramSocket(1239);
        Server server = new Server(datagramSocket);
        server.start();
    }

    @Override
    public void run() {

        while (true) {
            try {

                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                InetAddress inetAddress = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

                String messageToSend = String.valueOf(Food.getInstance().getPoints());
                buffer = messageToSend.getBytes();
                datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
                datagramSocket.send(datagramPacket);
                String messageFromServer = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                Food.getInstance().setClientpoints(messageFromServer); //YOU
                Food.getInstance().setClientpointsdois(messageFromClient); // ENEMY

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

        }

        }
    }


