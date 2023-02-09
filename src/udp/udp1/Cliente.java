package udp.udp1;

import java.io.*;
import java.net.*;

public class Cliente {
	public static void main(String[] args) throws IOException {
		//se establece el puerto al que envio (puerto del servidor)
		final int puerto = 12345; 
		//se localiza la IP a la que se envía el mensaje (en este caso, localhost)
		InetAddress destino = InetAddress.getLocalHost();
		//matriz de bytes: se establece el buffer del mensaje
		byte[] mensaje = new byte[1024]; 
		//se crea el String a enviar
		String Saludo = "Enviando Saludos! !";
		//se codifica el mensaje a bytes para enviarlo
		mensaje = Saludo.getBytes();
		
		//construyo el datagrama a enviar
		DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, puerto);
		
		//se crea un socket en el puerto 34567
		DatagramSocket socket = new DatagramSocket(34567);
		
		//se envia el datagrama contruido en la variable envio
		socket.send(envio);
		
		//se cierra la conexión del socket
		socket.close();
	}
}
