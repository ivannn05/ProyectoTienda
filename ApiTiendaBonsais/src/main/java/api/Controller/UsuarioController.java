package api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.Daos.Usuario;
import api.Services.UsuarioService;

@RestController // Asegúrate de que esta anotación esté presente
@RequestMapping("/api/usuarios") // Define la ruta base para este controlador
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Ruta GET para obtener todos los usuarios
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/todos") // Se usa /todos para que la URL sea /api/usuarios/todos
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        try {
            // Obtener la lista de usuarios desde el servicio
            List<Usuario> usuarios = usuarioService.getAllUsuarios();

            // Devuelve la lista de usuarios con un estado HTTP 200 (OK)
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            // Manejo de excepciones: devuelve un estado HTTP 500 (Error interno del servidor)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // Ruta POST para iniciar sesión
   
    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody Usuario usuario) {
        // Llamar al servicio para intentar el inicio de sesión
        String resultado = usuarioService.loginUsuario(usuario.getCorreo(), usuario.getContrasena());

        // Evaluar el resultado y devolver el estado correspondiente
        switch (resultado) {
            case "Login exitoso":
                return new ResponseEntity<>(resultado, HttpStatus.OK);
            case "Correo no registrado":
                return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
            case "Contraseña incorrecta":
                return new ResponseEntity<>(resultado, HttpStatus.UNAUTHORIZED);
            default:
                return new ResponseEntity<>("Error inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ruta POST para crear un usuario
  
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
    	Usuario nuevoUsuario = usuarioService.createUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
}
