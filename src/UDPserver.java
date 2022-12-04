import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPserver {

	public static void main(String args[]) throws Exception { 

		DatagramSocket serverSocket = new DatagramSocket(9876); 

		byte[] receiveData = new byte[1024]; 
		byte[] sendData  = new byte[1024]; 

		while(true) { 

			DatagramPacket receivePacket = 
					new DatagramPacket(receiveData, receiveData.length); 
			serverSocket.receive(receivePacket); 

			String stringCliente = new String(receivePacket.getData()); 

			InetAddress IPAddress = receivePacket.getAddress(); 

			int port = receivePacket.getPort(); 

			
			int pontosCliente= Integer.valueOf(stringCliente);
			
			String stringServidor= String.valueOf(Food.getInstance().getPoints());
			
			int pontosServidor = Integer.valueOf(stringServidor);
			
			sendData = stringServidor.getBytes(); 

			DatagramPacket sendPacket = 
					new DatagramPacket(sendData, sendData.length, IPAddress, port); 

			serverSocket.send(sendPacket); 

		}
	}
}
