package udp.udp1;

import java.io.*;
import java.net.*;

public class Servidor {
	public static void main(String[] args) throws IOException {
		//se establece el puerto del servidor
		final int puerto = 12345;
		//se establece el buffer de mensajes que se aceptaran
		byte[] bufer = new byte[1024];
		//se crea un socket en el puerto especificado
		DatagramSocket socket = new DatagramSocket(puerto);
		//construyo datagrama a recibir (peticion)
		DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);
		//instrucion para el recibo del datagrama
		socket.receive(recibo) ;
		//nº de bytesdel mensaje recibido
		int bytesRec = recibo.getLength();
		//String del mensaje recibido
		String mensaje= new String(recibo.getData());
		
		//Se imprime por consola la información
		System.out.println("Número de Bytes recibidos: "+ bytesRec);
		System.out.println("Contenido del Paquete: " + mensaje.trim());;
		System.out.println ("Puerto origen del mensaje: " + recibo. getPort());
		System.out.println("IP de origen:" + recibo.getAddress().getHostAddress() );
		System. out.println ("Puerto destino del mensaje:" + socket.getLocalPort());
		
		//se cierra la conexión del socket
		socket.close();
	}
}
