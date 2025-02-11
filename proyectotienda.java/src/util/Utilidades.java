package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import controladores.Inicio;
import dtos.Usuario;

public class Utilidades {
	
		public static String encriptarContraseña(String password) {
			try {
				// Creamos una instancia de MessageDigest con el algoritmo SHA-256
				MessageDigest digest = MessageDigest.getInstance("SHA-256");

				// Convertimos la contraseña a bytes y generamos el hash
				byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

				// Convertimos los bytes a una cadena hexadecimal
				StringBuilder hexString = new StringBuilder();
				for (byte b : encodedhash) {
					String hex = String.format("%02x", b);
					hexString.append(hex);
				}

				// Retornamos el hash en formato de String
				return hexString.toString();

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return null;
			}
		}
		public static void ficheroLog(String mensaje,Usuario usu) {
			try {
		  String rutaCompletaLog = "C:\\Users\\ivan\\Desktop\\workspaceProyectoDWS\\proyectotienda.java\\logs".concat("\\").concat("log-").concat(usu.getCorreo()).concat(".txt");
				BufferedWriter escribe = new BufferedWriter(new FileWriter(rutaCompletaLog, true));

				escribe.write(mensaje.concat("\n"));

				escribe.close();
			} catch (IOException e1) {

				System.out.println("Hubo un error en el fichero log  Error:001");
			}
		}

	}

