package udp.udp2;

import java.io.*;
import java.net.*;

public class Cliente {
	public static void main(String[] args) throws IOException {

		//Se establece el flujo para la entrada estandar
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				
		DatagramSocket clientSocket = new DatagramSocket();
		byte[] enviados = new byte[1024];
		byte[] recibidos = new byte[1024];
		
		//Datos del servidor al que enviar el mensaje
		InetAddress IPServidor = InetAddress.getLocalHost();		
		int puerto = 9876;
		
		//Se introduce por teclado el mensaje a enviar
		System.out.print("Introduce mensaje: ");
		String cadena = in.readLine();
		enviados = cadena.getBytes();
		
		//Se envía el datagrama al servidor
		System.out.println("Eviando "+ enviados.length + "bytes al servidor.");		
		DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
		clientSocket.send(envio);
		
		//Se recibe el datagrama enviado por el servidor
		DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);		
		System.out.println("Esperando datagrama...");
		clientSocket.receive(recibo);
		String mayuscula = new String(recibo.getData());
		
		//Se obtiene la información del datagrama enviado por el servidor
		InetAddress IPOrigen = recibo.getAddress();
		int puertoOrigen = recibo.getPort();
		System.out.println("\tProcedente de: "+IPOrigen+":"+puertoOrigen);
		System.out.println("\tDatos: "+mayuscula.trim());
		
		//Se cierra la conexión del socket
		clientSocket.close();
	}
}
