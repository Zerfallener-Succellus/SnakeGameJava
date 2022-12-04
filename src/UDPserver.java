import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPserver {
	int pontosCliCli;
	public int getPontosCliCli() {
		return pontosCliCli;
	}

	public void setPontosCliCli(int pontosCliCli) {
		this.pontosCliCli = pontosCliCli;
	}

	int pontosSerSer;
	public int getPontosSerSer() {
		return pontosSerSer;
	}

	public void setPontosSerSer(int pontosSerSer) {
		this.pontosSerSer = pontosSerSer;
	}

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
			
			UDPclient.getInstance().setPontosCliCli(pontosCliente); 
	        UDPclient.getInstance().setPontosSerSer(pontosServidor);

		}
	}

	private static UDPserver instancia;

private UDPserver(){}

public static synchronized UDPserver getInstance(){
if(instancia == null){
	instancia = new UDPserver();
}
return instancia;
}
}
