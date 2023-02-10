package multicast.multicast1;

import java.io.*;
import java.net.*;

public class ClienteMC {
	public static void main(String[] args) throws IOException {
		// Se declaran los datos del socket multicast
		int puerto = 12345;
		MulticastSocket ms = new MulticastSocket(puerto);
		InetAddress grupo = InetAddress.getByName("225.0.0.1");
		
		// El cliente se une al grupo multicast
		ms.joinGroup(grupo);
		
		String msg = "";	
		// Se establece el tamaño del buffer de entrada de datos
		byte[] buf = new byte[1000];
		
		// Mientras el mensaje sea distinto a *
		while(!msg.trim().equals("*")) {
			// Se recibe el paquete del servidor multicast
			DatagramPacket paquete = new DatagramPacket(buf, buf.length);
			ms.receive(paquete);
			
			// Se extrae el mensaje del datagrama y se imprime por pantalla
			msg = new String(paquete.getData(), 0, paquete.getLength());
			System.out.println("Recibo: "+msg.trim());
		}
		// Se abandona el grupo y se cierra el socket
		ms.leaveGroup(grupo);
		ms.close();
		System.out.println("Socket cerrado...");
	}
}
