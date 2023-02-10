package multicast.multicast1;

import java.io.*;
import java.net.*;

public class ServidorMC {
	public static void main(String[] args) throws IOException {
		// Flujo de entrada estándar
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// Se instancia el socket multicast
		MulticastSocket ms = new MulticastSocket();
		
		// Se establecen los datos del grupo multicast (grupo y puerto)
		int puerto = 12345;
		InetAddress grupo = InetAddress.getByName("225.0.0.1");
		
		String cadena = "";	
		// Mientras el mensaje sea distinto a *
		while(!cadena.trim().equals("*")) {
			// Se solicita el mensaje a enviar al grupo multicast
			System.out.print("Datos a enviar al grupo: ");
			cadena = in.readLine();
			
			// Se envia el paquete al grupo multicast 
			DatagramPacket paquete = new DatagramPacket(cadena.getBytes(), cadena.length(), grupo, puerto);
			ms.send(paquete);

			// Se imprime el mensaje enviado por pantalla
			String msg = new String(paquete.getData());
			System.out.println("Envio: "+msg.trim());						
		}		
		// Se cierra la conexion del socket
		ms.close();
		System.out.println("Socket cerrado...");
	}
}
