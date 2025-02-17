package tienda.bonsaissur.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import tienda.bonsaissur.dtos.Usuario;
import tienda.bonsaissur.util.util;

@Service
public class Services {

	public static Usuario UsuarioLogeado = new Usuario();
	public static List<Usuario> listaUsu = new ArrayList();
	public String API_URL_LOGIN = "http://localhost:8081/api/usuarios/login";

	public String login(String correo, String contrasena) {
		String res = "";
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

				Usuario usu = new Usuario();
				usu.setContrasena(contrasena);
				 System.out.println(contrasena);
				usu.setCorreo(correo);
				boolean comprobacion = false;
				for (Usuario Aux : listaUsu) {

					if (Aux.getCorreo().equals(usu.getCorreo()) && Aux.getContrasena().equals(usu.getContrasena())) {
						UsuarioLogeado = Aux;
						comprobacion = true;
						if (comprobacion == false) {
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
					System.out.println("Respuesta Login : " + response.body());
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

	public String Post(String nombre, String apellidos, String correo, String direccion, String telefono,
			String contrasena, String rol) {

		try {
			HttpClient client = HttpClient.newHttpClient();
			ObjectMapper objectMapper = new ObjectMapper();
			String API_URL = "http://localhost:8081/api/usuarios/crearUsu";

			// Crear un usuario
			Usuario usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellidos(apellidos);
			usuario.setCorreo(correo);
			usuario.setDireccion(direccion);
			usuario.setTelefono(telefono);
			usuario.setContrasena(util.encriptarContraseña(contrasena));
			Timestamp fechaRegistro = Timestamp.from(Instant.now());
			usuario.setFechaRegistro(fechaRegistro);
			usuario.setRol(rol);

			// Convertir el objeto a JSON
			String json = objectMapper.writeValueAsString(usuario);

			// Construir la solicitud POST
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL))
					.header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(json)).build();

			// Enviar la solicitud
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			// Imprimir la respuesta
			System.out.println("Respuesta del servidor: " + response.body());
			return "Registro exitoso";

		} catch (Exception e) {
			e.printStackTrace();
			return "Registro erroneo";
		}
	}

	public String Delete(String correo, String contrasena) {
		String respuesta = "";
		try {

			HttpClient client = HttpClient.newHttpClient();
			ObjectMapper objectMapper = new ObjectMapper();
			String API_URL_DELETE = "http://localhost:8081/api/usuarios/eliminar"; // Suponiendo que tu API tiene un
																					// endpoint para eliminar
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

				// 2. Usar el método eliminarUsu para buscar el usuario
				Usuario usuario = new Usuario();
				usuario.setContrasena(contrasena);
				usuario.setCorreo(correo);

				if (usuario != null && !usuario.getCorreo().isEmpty()) {
					// 3. Si el usuario existe, crear un objeto con correo y contraseña para
					// eliminarlo
					Map<String, String> userToDelete = new HashMap<>();
					userToDelete.put("correo", usuario.getCorreo());
					userToDelete.put("contrasena", usuario.getContrasena());

					// Convertir el mapa a JSON
					String json = objectMapper.writeValueAsString(userToDelete);

					// 4. Enviar la solicitud DELETE con el JSON en el cuerpo
					// No necesitas pasar correo en la URL
					HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL_DELETE))
							.header("Content-Type", "application/json")
							.method("DELETE", HttpRequest.BodyPublishers.ofString(json)) // Usar DELETE con cuerpo
							.build();

					HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

					if (response.statusCode() == 200) {
						System.out.println("Usuario " + usuario.getCorreo() + " eliminado correctamente.");
						// Actualizar la lista local para reflejar el cambio
						listaUsu.remove(usuario);
						respuesta = "Usuario Eliminado";
					} else {
						System.out.println("Error al eliminar usuario: " + response.body());
						respuesta = "Error al eliminar";
					}
				} else {
					System.out.println("Usuario con correo " + usuario.getCorreo() + " no encontrado.");
					respuesta = "Usuario no encontrado";
				}
			} else {
				System.out.println("Error al obtener la lista de usuarios: " + responseUsu.statusCode());
			}
		} catch (Exception e) {
			System.out.println("Ocurrio un error en Eliminar");
		}
		System.out.println(respuesta);
		return respuesta;
	}

	public String Put(String nombre, String apellidos, String correo, String direccion, String telefono) {
		String resp="";
		try {
			HttpClient client = HttpClient.newHttpClient();
			ObjectMapper objectMapper = new ObjectMapper();
			String API_URL = "http://localhost:8081/api/usuarios/actualizar";
			String API_URL_SELECT = "http://localhost:8081/api/usuarios/todos";

			// 1. Obtener la lista actualizada de usuarios desde la API
			HttpRequest requestUsu = HttpRequest.newBuilder().uri(URI.create(API_URL_SELECT))
					.header("Accept", "application/json").GET().build();

			HttpResponse<String> responseUsu = client.send(requestUsu, HttpResponse.BodyHandlers.ofString());

			if (responseUsu.statusCode() == 200) {
				// Convertir la respuesta JSON a lista de usuarios
				List<Usuario> usuarios = objectMapper.readValue(responseUsu.body(), new TypeReference<List<Usuario>>() {
				});
				listaUsu = usuarios; // Actualizar la lista local

				System.out.println("Total de usuarios cargados:" + listaUsu.size());
				// 2. Buscar el usuario con el correo proporcionado
				Usuario usuario = usuarios.stream().filter(u -> correo.equalsIgnoreCase(u.getCorreo())).findFirst()
						.orElse(null);

				if (usuario != null) {
					Usuario usu = UsuarioLogeado;
					// Actualizar los datos del usuario
					usu.setNombre(nombre); 
					usu.setApellidos(apellidos);
					usu.setCorreo(correo);
					usu.setDireccion(direccion);
					usu.setTelefono(telefono);

					// 3. Enviar la solicitud PUT con el usuario actualizado
					String json = objectMapper.writeValueAsString(usu);

					HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL))
							.header("Content-Type", "application/json").PUT(HttpRequest.BodyPublishers.ofString(json))
							.build();

					HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

					System.out.println("Respuesta del servidor: " + response.body());
					resp="Usuario actualizado";
				} else {
					System.out.println("Usuario con correo " + correo + " no encontrado.");
					resp="Usuario no encontrado";
				}
			} else {
				System.out.println("Error al obtener la lista de usuarios: " + responseUsu.statusCode());
				resp="Ocurrio un error ";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}
