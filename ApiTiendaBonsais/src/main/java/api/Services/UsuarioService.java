package api.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.Daos.Usuario;
import api.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	// Obtener todos los usuarios
	 public List<Usuario> getAllUsuarios() {
	        return usuarioRepository.findAll();
	    }

	// Inicio de sesión de un usuario por correo y contraseña
	 public String loginUsuario(String correo, String contrasena) {
		    // Buscar al usuario por correo
		    Usuario usuario = usuarioRepository.findByCorreo(correo).orElse(null);

		    // Verificar si el usuario no existe
		    if (usuario == null) {
		        return "Correo no registrado";
		    }

		    // Comparar las contraseñas en texto plano
		    if (!contrasena.equals(usuario.getContrasena())) {
		        return "Contraseña incorrecta";
		    }

		    // Si la contraseña es correcta
		    return "Login exitoso";
	    }


	// Crear un nuevo usuario
	public Usuario createUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	// Actualizar un usuario existente por ID
	public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
	    // Buscar el usuario por ID, y lanzar excepción si no se encuentra
	    Usuario usuario = usuarioRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

	    // Actualizar los campos del usuario con los detalles proporcionados
	    usuario.setNombre(usuarioDetails.getNombre());
	    usuario.setApellidos(usuarioDetails.getApellidos());
	    usuario.setCorreo(usuarioDetails.getCorreo());
	    usuario.setDireccion(usuarioDetails.getDireccion());
	    usuario.setContrasena(usuarioDetails.getContrasena());
	    usuario.setFechaRegistro(usuarioDetails.getFechaRegistro());

	    // Guardar los cambios en la base de datos
	    return usuarioRepository.save(usuario);
	}


	// Eliminar un usuario por correo y contraseña
	@Transactional
	public Usuario deleteUsuario(String correo, String contrasena) {
		Optional<Usuario> usuario = usuarioRepository.findByCorreoAndContrasena(correo, contrasena);
		if (usuario.isPresent()) {
			usuarioRepository.delete(usuario.get());
			return usuario.get();
		}
		return null;
	}

}
