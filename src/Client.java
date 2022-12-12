
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Client extends Thread {

    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    private byte[] buffer;

    public Client(DatagramSocket datagramSocket, InetAddress inetAddress) {
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {

        Window window = Window.getWindow();
        Thread thread = new Thread(window);

        thread.start();

        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName("localhost");
        Client client = new Client(datagramSocket, inetAddress);

        client.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String messageToSend = String.valueOf(Food.getInstance().getPoints());
                buffer = messageToSend.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1239);
                String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

                datagramSocket.send(datagramPacket);
                datagramSocket.receive(datagramPacket);

                String messageFromServer = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                Food.getInstance().setClientpoints(messageFromClient); // You
                Food.getInstance().setClientpointsdois(messageFromServer); // Enemy

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            }
        }

    }


