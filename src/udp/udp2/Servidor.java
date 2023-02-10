package udp.udp2;

import java.io.*;
import java.net.*;

public class Servidor {
	public static void main(String[] args) throws IOException {
		// Se crea el socket del servidor en el puerto 9876
		DatagramSocket serverSocket = new DatagramSocket(9876);
		
		// Se entablece el buffer de entrada y salida
		byte[] recibidos = new byte[1024];
		byte[] enviados = new byte[1024];
		
		String cadena;		
		// Bucle infinito
		while(true) {
			System.out.println("Esperando datagrama...");			
			recibidos = new byte[1024];
			// Se recibe el datagrama
			DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
			serverSocket.receive(paqRecibido);
			
			// Se obtienen los datos del datagrama recibido (mensaje, ip_origen, puerto_origen)
			cadena = new String(paqRecibido.getData());						
			InetAddress IPOrigen = paqRecibido.getAddress();
			int puerto = paqRecibido.getPort();
			
			System.out.println("\tOrigen: "+IPOrigen+":"+puerto);
			System.out.println("\tMensaje recibido: "+cadena.trim());
			
			// Se convierte la cadena recibida a mayúscula
			String mayuscula = cadena.trim().toUpperCase();
			enviados = mayuscula.getBytes();
			
			// Se envia al cliente confirmación del recibo del datagrama mediante otro datagrama
			DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
			serverSocket.send(paqEnviado);
			
			// Si un cliente introduce el mensaje "*" se sale del bucle while
			if(cadena.trim().equals("*")) break;						
		}
		// Se cierra la conexión del socket
		serverSocket.close();
		System.out.println("Socket cerrado...");
	}
}
