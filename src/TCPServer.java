import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

		public class TCPServer extends GameScene{

			public TCPServer(KL keyListener) {
				super(keyListener);
				// TODO Auto-generated constructor stub
			}
			
			
			public static void main(String argv[]) throws Exception {
				
				//Cria um objeto GameScene
				GameScene auxiliar = new GameScene();
				int serverPontuacao = auxiliar.getPointsfinal();
				
				int clientPontuacao;
				
					
				

				ServerSocket welcomeSocket = new ServerSocket(6789); 

				while(true) {

					Socket connectionSocket = welcomeSocket.accept(); 

					BufferedReader inFromClient = 
							new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

					DataOutputStream  outToClient = 
							new DataOutputStream(connectionSocket.getOutputStream()); 

					clientPontuacao = inFromClient.read(); 
					
					outToClient.write(serverPontuacao); 
					
					if(serverPontuacao>clientPontuacao) {
						System.out.println("Jogador 1 ganhou!");
					}
					else if(serverPontuacao==clientPontuacao) {
						System.out.println("Empate!");
					}
					else {
						System.out.println("Jogador 2 ganhou!");
					}

					
				} 
			} 

		
	}


