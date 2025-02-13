package tienda.bonsaissur.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import tienda.bonsaissur.dtos.Usuario;
import tienda.bonsaissur.services.Services;

@Controller

public class LoginController {

	private final Services loginService;

	public LoginController(Services loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/login")
	public ModelAndView login(@RequestParam String correo, @RequestParam String contrasena, HttpSession session) {
		System.out.println("Entre  Login Web");
		System.out.println(correo);
		ModelAndView mav = new ModelAndView("index");
		String respuesta = loginService.login(correo, contrasena);
		mav.addObject("usuario",Services.UsuarioLogeado );
		System.out.println(Services.UsuarioLogeado.toString());
		if (respuesta.equals("Login exitoso")) {
			session.setAttribute("usuario", Services.UsuarioLogeado); // Guarda el usuario en la sesión
			return mav; // Redirige a la vista principal
		} else {
			// model.addAttribute("error", "Correo o contraseña incorrectos");
			mav.addObject("mensaje", "Correo o contraseña incorrectos");
			return mav;
		}
	}

}
