package api.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import api.Daos.Usuario;
import api.Services.UsuarioService;
import send.email.template.EmailTemplate;

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
    // Ruta DELETE para eliminar
    @DeleteMapping("/eliminar")
    public ResponseEntity<String> deleteUsuario(@RequestBody Usuario usuario) {
        System.out.println("Intentando eliminar usuario con correo: " + usuario.getCorreo());

        // Verifica que el objeto usuario no sea nulo y que tenga correo y contraseña
        if (usuario == null || usuario.getCorreo() == null || usuario.getContrasena() == null) {
            return new ResponseEntity<>("Correo y contraseña son requeridos", HttpStatus.BAD_REQUEST);
        }

        Usuario usuarioEliminado = usuarioService.deleteUsuario(usuario.getCorreo(), usuario.getContrasena());

        if (usuarioEliminado != null) {
            return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado o credenciales incorrectas", HttpStatus.NOT_FOUND);
        }
    }




    // Ruta POST para iniciar sesión
   
    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody Usuario usuario) {
        // Llamar al servicio para intentar el inicio de sesión
        String resultado = usuarioService.loginUsuario(usuario.getCorreo(), usuario.getContrasena());
        System.out.println("Entre en login");

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
  
    @PostMapping("/crearUsu")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
    	Usuario nuevoUsuario = usuarioService.createUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
        try {
            // Llamar al servicio para actualizar el usuario
            Usuario usuarioActualizado = usuarioService.updateUsuario(usuario.getId(),usuario);
            
            // Si el usuario fue actualizado correctamente, devuelve el usuario actualizado con un estado HTTP 200 (OK)
            if (usuarioActualizado != null) {
                return ResponseEntity.ok(usuarioActualizado);
            } else {
                // Si el usuario no fue encontrado, devuelve un estado HTTP 404 (No encontrado)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            // Manejo de excepciones: devuelve un estado HTTP 500 (Error interno del servidor)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
 	
    
}
