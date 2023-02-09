package multicast.multicast1;

import java.io.IOException;
import java.net.*;

public class ClienteMC {
	public static void main(String[] args) throws IOException {
		//Se crea el socket multicast
		int puerto = 12345;
		MulticastSocket ms = new MulticastSocket(puerto);
		InetAddress grupo = InetAddress.getByName("225.0.0.1");
		//El cliente se une al grupo multicast
		ms.joinGroup(grupo);
		String msg = "";		
		byte[] buf = new byte[1000];
		
		while(!msg.trim().equals("*")) {
			//Se recibe el paquete del servidor multicast
			DatagramPacket paquete = new DatagramPacket(buf, buf.length);
			ms.receive(paquete);
			msg = new String(paquete.getData(), 0, paquete.getLength());
			System.out.println("Recibo: "+msg.trim());
		}
		//Se abandona el grupo
		ms.leaveGroup(grupo);
		//Se cierra el socket
		ms.close();
		System.out.println("Socket cerrado...");
	}
}
