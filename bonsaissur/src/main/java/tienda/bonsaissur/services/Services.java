package tienda.bonsaissur.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import tienda.bonsaissur.dtos.Usuario;



@Service
public class Services {

	public static Usuario UsuarioLogeado= new Usuario();
	public static  List<Usuario> listaUsu= new ArrayList();
    public  String API_URL_LOGIN = "http://localhost:8081/api/usuarios/login";
    
    public String login(String correo, String contrasena) {
		String res="";
		try {
			HttpClient client = HttpClient.newHttpClient();
			ObjectMapper objectMapper = new ObjectMapper();
			String API_URL_LOGIN = "http://localhost:8081/api/usuarios/login"; // Endpoint para el login
			String API_URL_SELECT = "http://localhost:8081/api/usuarios/todos"; // Endpoint para obtener todos los
			// usuarios

// 1. Obtener la lista actualizada de usuarios desde la API
			HttpRequest requestUsu = HttpRequest.newBuilder().uri(URI.create(API_URL_SELECT))
					.header("Accept", "application/json").GET().build();

			HttpResponse<String> responseUsu = client.send(requestUsu, HttpResponse.BodyHandlers.ofString());

			if (responseUsu.statusCode() == 200) {
// Convertir la respuesta JSON a lista de usuarios
				List<Usuario> usuarios = objectMapper.readValue(responseUsu.body(), new TypeReference<List<Usuario>>() {
				});
				 listaUsu = usuarios; // Actualizar la lista local
				
				 Usuario usu= new Usuario();
				usu.setContrasena(contrasena);
				usu.setCorreo(correo);
				boolean comprobacion=false;
				for (Usuario Aux : listaUsu) {
					if (Aux.getCorreo().equals(usu.getCorreo()) && Aux.getContrasena().equals(usu.getContrasena())) {
						UsuarioLogeado = Aux;
						comprobacion=true;
						if (comprobacion==false) {
							System.out.println("No se encontro en la lista");
						}
					}
				}
				
				// Crear un objeto Map con los datos de login
				Map<String, String> loginData = new HashMap<>();
				loginData.put("correo", usu.getCorreo());
				loginData.put("contrasena", usu.getContrasena());

				// Convertir el objeto a JSON
				String json = objectMapper.writeValueAsString(loginData);

				// Construir la solicitud POST para el login
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL_LOGIN))
						.header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(json))
						// Enviar los datos de login en el cuerpo
						.build();

				// Enviar la solicitud
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

				// Imprimir la respuesta
				if (response.statusCode() == 200) {
					System.out.println("Login exitoso: " + response.body());
				} else {
					System.out.println("Error en el login: " + response.body());
				}
				res = response.body();

			}
		} catch (Exception e) {
			System.out.println("Ocurrio un error en Login");
			res = "Ocurrio un error en Login";
		}
		return res;
    }
}
