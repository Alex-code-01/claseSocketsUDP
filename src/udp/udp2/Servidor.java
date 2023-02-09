package udp.udp2;

import java.io.*;
import java.net.*;

public class Servidor {
	public static void main(String[] args) throws IOException {
		//Puerto por el que se escucha el servidor: 9876
		DatagramSocket serverSocket = new DatagramSocket(9876);
		byte[] recibidos = new byte[1024];
		byte[] enviados = new byte[1024];
		String cadena;
		
		while(true) {
			System.out.println("Esperando datagrama...");
			//Se recibe el datagrama
			recibidos = new byte[1024];
			DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
			serverSocket.receive(paqRecibido);
			cadena = new String(paqRecibido.getData());
			
			//Se obtiene la direccion de origen del datagrama
			InetAddress IPOrigen = paqRecibido.getAddress();
			int puerto = paqRecibido.getPort();
			System.out.println("\tOrigen: "+IPOrigen+":"+puerto);
			System.out.println("\tMensaje recibido: "+cadena.trim());
			
			//Se convierte la cadena recibida a mayúscula
			String mayuscula = cadena.trim().toUpperCase();
			enviados = mayuscula.getBytes();
			
			//Se envia el datagrama al cliente
			DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
			serverSocket.send(paqEnviado);
			
			//Si un cliente introduce el mensaje "*" se sale del bucle while
			if(cadena.trim().equals("*")) break;						
		}
		//Se cierra la conexión del socket
		serverSocket.close();
		System.out.println("Socket cerrado...");

	}

}
