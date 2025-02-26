package api.Utilidades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Clase de utilidades de la web
 */
public class Util {
	/**
	 * Metodo encargado de la creacion del nombre del fichero
	 * @return
	 */
	public static String creacionNombreFichero() {
		try {
		String fecha;
		/// Para poner un formato a una fecha con DateTime
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMMyy");
		LocalDate fechaActual = LocalDate.now();
		fecha = fechaActual.format(formato);
		return fecha;
		}catch (Exception e) {
			ficheroLog("Ocurrio un error en la creacion de nombre de fichero:"+e.getMessage());
		}
		return "";
	}

	/**
	 * Metodo encargado del fichero log
	 * 
	 * @param mensaje
	 * @param usu
	 */
	public static void ficheroLog(String mensaje) {
		try {
			String rutaCompletaLog = "C:\\Users\\ivan\\Desktop\\workspaceProyectoDWS\\ApiTiendaBonsais\\logs-Api"
					.concat("\\").concat("log-").concat(creacionNombreFichero()).concat(".txt");
			BufferedWriter escribe = new BufferedWriter(new FileWriter(rutaCompletaLog, true));

			escribe.write(mensaje.concat("\n"));

			escribe.close();
		} catch (IOException e1) {

			System.out.println("Hubo un error en el fichero log  Error:001");
		}
	}
}
