package udp.udp1;

import java.io.*;
import java.net.*;

public class Cliente {
	public static void main(String[] args) throws IOException {
		// Se establece el puerto al que se envia el datagrama (puerto del servidor)
		final int puerto = 12345; 
		
		// Se establece la IP a la que se envía el mensaje (en este caso, localhost)
		InetAddress destino = InetAddress.getLocalHost();
		
		// Se establece el buffer del mensaje
		byte[] mensaje = new byte[1024]; 
		// Se crea la cadena de texto a enviar
		String Saludo = "Enviando Saludos! !";
		// Se codifica el mensaje a bytes para enviarlo
		mensaje = Saludo.getBytes();
		
		// Se construye el datagrama a enviar (mensaje, tamaño de mensaje, ip_destino y puerto_destino)
		DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, puerto);
		
		// Se crea el socket cliente en el puerto 34567
		DatagramSocket socket = new DatagramSocket(34567);
		
		// Se envia el datagrama contruido en la variable envio
		socket.send(envio);
		
		// Se cierra la conexión del socket
		socket.close();
	}
}
