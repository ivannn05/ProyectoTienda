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
import tienda.bonsaissur.dtos.Usuario;
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
		Usuario usu = loginService.login(correo, util.encriptarContraseña(contrasena));
		System.out.println("Rol de Persona:" + Services.UsuarioLogeado.getRol());
		if (usu.getNombre()!=null) {
			if (usu.getRol().equals("Administrador")) {
				session.setAttribute("Usuario", usu); // Guarda el usuario en la sesión
				return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/login.jsp")).build();
			} else {
				
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
			
			// Redirige a /index // Redirige a la vista principal
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/index.jsp")).build();
		} else {
			// Redirige a /login con error
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/login.jsp")).build();

		}
	}
	@PostMapping("/correoRecuperar")
	public ResponseEntity<Void> recuperarContrasena(@RequestParam String correoRecuperar,HttpSession session){
		String respuesta = loginService.recuperarContrasena( correoRecuperar);
		
		if (respuesta.equals("Correo existente")) {
			
			// Redirige a /index // Redirige a la vista principal
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/mirarCorreo.jsp")).build();
		} else {
			// Redirige a /login con error
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/login.jsp")).build();

		}
	}
	@PostMapping("/escribirContrasena")
	public ResponseEntity<Void> escribirContrasena(@RequestParam String contrasena,HttpSession session){
		String respuesta = loginService.recuperarContrasena( contrasena);
		
		if (respuesta.equals("Correo existente")) {
		
			// Redirige a /index // Redirige a la vista principal
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/nuevaContrasena.jsp")).build();
		} else {
			// Redirige a /login con error
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/bonsaissur/login.jsp")).build();

		}
	}

}






















