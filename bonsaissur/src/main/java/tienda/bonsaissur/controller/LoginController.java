package tienda.bonsaissur.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import tienda.bonsaissur.services.Services;
import tienda.bonsaissur.util.util;

@Controller

public class LoginController {

	private final Services loginService;

	public LoginController(Services loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestParam String correo, @RequestParam String contrasena,
			HttpSession session) {
		String respuesta = loginService.login(correo, util.encriptarContraseña(contrasena));
		System.out.println("Rol de Persona:" + Services.UsuarioLogeado.getRol());
		if (respuesta.equals("Login exitoso")) {
			if (Services.UsuarioLogeado.getRol().equals("Administrador")) {
				session.setAttribute("Administrador", Services.UsuarioLogeado); // Guarda el usuario en la sesión
				return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/login.jsp")).build();
			} else {
				session.setAttribute("Usuario", Services.UsuarioLogeado); // Guarda el usuario en la sesión
				return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/index.jsp")).build();
			}
		} else {
			// Redirige a /login 
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/login.jsp")).build();

		}
	}

	@PostMapping("/registro")
	public ResponseEntity<Void> registro(@RequestParam String nombre, @RequestParam String apellidos,
			@RequestParam String correo, @RequestParam String contrasena, @RequestParam String direccion,
			@RequestParam String telefono, @RequestParam String rol, HttpSession session) {
		String respuesta = loginService.Post(nombre, apellidos, correo, direccion, telefono,
				util.encriptarContraseña(contrasena), rol);

		if (respuesta.equals("Registro exitoso")) {
			session.setAttribute("usuario", Services.UsuarioLogeado); // Guarda el usuario en la sesión
			// Redirige a /index 
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/index.jsp")).build();

		} else {
			// Redirige a /login con error
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/loginUsu.jsp")).build();

		}
	}

	@PostMapping("/eliminar")
	public ResponseEntity<Void> eliminar(@RequestParam String correo, @RequestParam String contrasena,
			HttpSession session) {
		String respuesta = loginService.Delete(correo, util.encriptarContraseña(contrasena));

		if (respuesta.equals("Usuario Eliminado")) {
			session.setAttribute("usuario", Services.UsuarioLogeado); // Guarda el usuario en la sesión
			// Redirige a /index // Redirige a la vista principal
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/index.jsp")).build();
		} else {
			// Redirige a /login con error
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/login.jsp")).build();

		}
	}

	@PostMapping("/actualizar")
	public ResponseEntity<Void> actualizar(@RequestParam String nombre, @RequestParam String apellidos,
			@RequestParam String correo, @RequestParam String direccion, @RequestParam String telefono,
			HttpSession session) {
		String respuesta = loginService.Put(nombre, apellidos, correo, direccion, telefono);

		if (respuesta.equals("Usuario actualizado")) {
			session.setAttribute("usuario", Services.UsuarioLogeado); // Guarda el usuario en la sesión
			// Redirige a /index // Redirige a la vista principal
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/index.jsp")).build();
		} else {
			// Redirige a /login con error
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/login.jsp")).build();

		}
	}

}
