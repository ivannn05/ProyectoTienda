package controladores;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dtos.Usuario;
import servicios.CrudUsuario;

public class MetodosHTTP {

	CrudUsuario cu = new CrudUsuario();

	public void Post() {

		try {
			HttpClient client = HttpClient.newHttpClient();
			ObjectMapper objectMapper = new ObjectMapper();
			String API_URL = "http://localhost:8081/api/usuarios/crearUsu";

			// Crear un usuario
			Usuario usuario = new Usuario();
			usuario = cu.AniadirUsu();

			// Convertir el objeto a JSON
			String json = objectMapper.writeValueAsString(usuario);

			// Construir la solicitud POST
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL))
					.header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(json)).build();

			// Enviar la solicitud
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			// Imprimir la respuesta
			System.out.println("Respuesta del servidor: " + response.body());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void UpdateContrasena(String correo) {
		try {
			HttpClient client = HttpClient.newHttpClient();
			ObjectMapper objectMapper = new ObjectMapper();
			String API_URL_UPDATE_PASSWORD = "http://localhost:8081/api/usuarios/actualizar";

			// Obtener la lista actualizada de usuarios
			HttpRequest requestUsu = HttpRequest.newBuilder()
					.uri(URI.create("http://localhost:8081/api/usuarios/todos")).header("Accept", "application/json")
					.GET().build();

			HttpResponse<String> responseUsu = client.send(requestUsu, HttpResponse.BodyHandlers.ofString());

			if (responseUsu.statusCode() == 200) {
				List<Usuario> usuarios = objectMapper.readValue(responseUsu.body(), new TypeReference<List<Usuario>>() {
				});
				Inicio.listaUsuarios = usuarios;

				// Buscar usuario por correo
				Usuario usuario = usuarios.stream().filter(u -> correo.equalsIgnoreCase(u.getCorreo())).findFirst()
						.orElse(null);

				if (usuario != null) {
					// Pedir nueva contraseña al usuario
					usuario = cu.cambiarContrasena( correo); // Se encarga de actualizar la contraseña

					// Enviar la solicitud PUT para actualizar la contraseña
					String json = objectMapper.writeValueAsString(usuario);
					
					HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL_UPDATE_PASSWORD))
							.header("Content-Type", "application/json").PUT(HttpRequest.BodyPublishers.ofString(json))
							.build();
					System.out.println(request.toString());
					HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

					System.out.println("Respuesta del servidor: " + response.body());
				} else {
					System.out.println("Usuario con correo " + correo + " no encontrado.");
				}
			} else {
				System.out.println("Error al obtener la lista de usuarios: " + responseUsu.statusCode());
			}
		} catch (Exception e) {
			System.out.println("Ocurrió un error en CambiarContrasena");
			e.printStackTrace();
		}
	}

	public void Put(String correo) {
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
				Inicio.listaUsuarios = usuarios; // Actualizar la lista local

				// 2. Buscar el usuario con el correo proporcionado
				Usuario usuario = usuarios.stream().filter(u -> correo.equalsIgnoreCase(u.getCorreo())).findFirst()
						.orElse(null);

				if (usuario != null) {
					usuario = cu.actualizarUsu(usuario.getCorreo()); // Actualizar los datos del usuario

					// 3. Enviar la solicitud PUT con el usuario actualizado
					String json = objectMapper.writeValueAsString(usuario);
					HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL))
							.header("Content-Type", "application/json").PUT(HttpRequest.BodyPublishers.ofString(json))
							.build();

					HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

					System.out.println("Respuesta del servidor: " + response.body());
				} else {
					System.out.println("Usuario con correo " + correo + " no encontrado.");
				}
			} else {
				System.out.println("Error al obtener la lista de usuarios: " + responseUsu.statusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Login() {
		try {
			HttpClient client = HttpClient.newHttpClient();
			ObjectMapper objectMapper = new ObjectMapper();
			String API_URL_LOGIN = "http://localhost:8081/api/usuarios/login"; // Endpoint para el login

			Usuario usu = cu.loginUsu();
			// Crear un objeto Map con los datos de login
			Map<String, String> loginData = new HashMap<>();
			loginData.put("correo", usu.getCorreo());
			loginData.put("contrasena", usu.getContrasena());

			// Convertir el objeto a JSON
			String json = objectMapper.writeValueAsString(loginData);

			// Construir la solicitud POST para el login
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL_LOGIN))
					.header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(json)) // Enviar
																												// los
																												// datos
																												// de
																												// login
																												// en el
																												// cuerpo
					.build();

			// Enviar la solicitud
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			// Imprimir la respuesta
			if (response.statusCode() == 200) {
				System.out.println("Login exitoso: " + response.body());
			} else {
				System.out.println("Error en el login: " + response.body());
			}

		} catch (Exception e) {
			System.out.println("Ocurrio un error en Login");
		}
	}

	public void Delete() {
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
				Inicio.listaUsuarios = usuarios; // Actualizar la lista local

				// 2. Usar el método eliminarUsu para buscar el usuario
				Usuario usuario = cu.eliminarUsu(); // Buscar el usuario por correo usando el método que proporcionaste

				if (usuario != null && !usuario.getCorreo().isEmpty()) {
					// 3. Si el usuario existe, crear un objeto con correo y contraseña para
					// eliminarlo
					Map<String, String> userToDelete = new HashMap<>();
					userToDelete.put("correo", usuario.getCorreo());
					userToDelete.put("contrasena", usuario.getContrasena());

					// Convertir el mapa a JSON
					String json = objectMapper.writeValueAsString(userToDelete);

					// 4. Enviar la solicitud DELETE con el JSON en el cuerpo
					HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL_DELETE)) // No necesitas pasar
																									// correo en la URL
							.header("Content-Type", "application/json")
							.method("DELETE", HttpRequest.BodyPublishers.ofString(json)) // Usar DELETE con cuerpo
							.build();

					HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

					if (response.statusCode() == 200) {
						System.out.println("Usuario " + usuario.getCorreo() + " eliminado correctamente.");
						// Actualizar la lista local para reflejar el cambio
						Inicio.listaUsuarios.remove(usuario);
					} else {
						System.out.println("Error al eliminar usuario: " + response.body());
					}
				} else {
					System.out.println("Usuario con correo " + usuario.getCorreo() + " no encontrado.");
				}
			} else {
				System.out.println("Error al obtener la lista de usuarios: " + responseUsu.statusCode());
			}
		} catch (Exception e) {
			System.out.println("Ocurrio un error en Eliminar");
		}
	}

}
