
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.net.DatagramPacket;
	import java.net.DatagramSocket;
	import java.net.InetAddress;

	
	
public class UDPclient{
	public static void main(String args[]) throws Exception { 

	BufferedReader inFromUser = 
			new BufferedReader(new InputStreamReader(System.in)); 

	DatagramSocket clientSocket = new DatagramSocket();

	InetAddress IPAddress = InetAddress.getByName("localhost"); 
	
	
	

	byte[] sendData = new byte[1024]; 
	byte[] receiveData = new byte[1024]; 
	
	String stringCliente = String.valueOf(Food.getInstance().getPoints()); 
	sendData = stringCliente.getBytes();   

	DatagramPacket sendPacket = 
			new DatagramPacket(sendData, sendData.length, IPAddress, 9876); 

	clientSocket.send(sendPacket); 

	DatagramPacket receivePacket = 
			new DatagramPacket(receiveData, receiveData.length); 

	clientSocket.receive(receivePacket); 

	String stringServidor = 
			new String(receivePacket.getData()); 
	
	int pontosServer = Integer.valueOf(stringServidor);
	int pontosClientes= Integer.valueOf(stringCliente);
	
	if(pontosServer>pontosClientes) {
		System.out.println("Jogador 1 ganhou!");
	}
	else if(pontosServer==pontosClientes) {
		System.out.println("Jogadores empataram");
	}
	
	else {
		System.out.println("Jogador2 Ganhou");
	}
} 

}
