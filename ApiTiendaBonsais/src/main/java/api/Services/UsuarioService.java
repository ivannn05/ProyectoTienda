package api.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import api.Daos.Usuario;
import api.Repository.UsuarioRepository;
import api.Utilidades.Util;
import jakarta.transaction.Transactional;
@CrossOrigin(origins = "http://localhost:4200") // Permitir solicitudes desde Angular
@Service
/**
 * Clase servicio donde contiene los metodos de servicio de la web
 */
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	/**
	 *  Metodo encargado de obtener todos los usuarios
	 * @return
	 */
	 public List<Usuario> getAllUsuarios() {
		 try {
	        return usuarioRepository.findAll();
		 }catch (Exception e) {
			Util.ficheroLog("Ocurrio un error en getAllUsuarios:"+e.getMessage() );
		}
		return null;
	    }
/**
 * Metodo encargado de Inicio de sesión de un usuario por correo y contraseña
 * @param correo
 * @param contrasena
 * @return
 */	 
	 public String loginUsuario(String correo, String contrasena) {
		 try {
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
		 }catch (Exception e) {
				Util.ficheroLog("Ocurrio un error en getAllUsuarios:"+e.getMessage() );
			}
		return "";
	    }


	/**
	 *  Metoso encargado de Crear un nuevo usuario
	 * @param usuario
	 * @return
	 */
	public Usuario createUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	/**
	 * Metodo encargado de encontrar un usuario por correo
	 * @param correo
	 * @return
	 */
	public Usuario encontrarPorCorreo(String correo) {
		try {
		Util.ficheroLog("Entro en servicio encontrar por correo");
		Usuario usuario;
		usuario = usuarioRepository.findByCorreo(correo)  .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		return usuario;}catch (Exception e) {
			Util.ficheroLog("Ocurrio un error en encontrar por correo"+e.getMessage());
		}
		return null;
		
	}
/**
 * Metodo encargado de Actualizar un usuario existente por ID
 * @param id
 * @param usuarioDetails
 * @return
 */
		public Usuario actualizarUsuario(Long id, Usuario usuarioDetails) {
		
	    // Buscar el usuario por ID, y lanzar excepción si no se encuentra
		Usuario usuario;
		usuario = usuarioRepository.findById(id)  .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		try {
			Util.ficheroLog("Entro en servicio actualizar");
			      

			// Actualizar los campos del usuario con los detalles proporcionados
			usuario.setNombre(usuarioDetails.getNombre());
			usuario.setApellidos(usuarioDetails.getApellidos());
			usuario.setCorreo(usuarioDetails.getCorreo());
			usuario.setDireccion(usuarioDetails.getDireccion());
			usuario.setContrasena(usuarioDetails.getContrasena());
			usuario.setFechaRegistro(usuarioDetails.getFechaRegistro());
			usuario.setRol(usuarioDetails.getRol());
			usuario.setToken(usuarioDetails.getToken());
			usuario.setFechaToken(usuarioDetails.getFechaToken());
			usuario.setTelefono(usuarioDetails.getTelefono());
			usuario.setFotoUsu(usuarioDetails.getFotoUsu());

			// Guardar los cambios en la base de datos
		} catch (RuntimeException e) {
			Util.ficheroLog("Ocurrio un error en servicio actualizar usuario:"+e.getMessage());
		}
	    return usuarioRepository.save(usuario);
	}


/**
 *  Metodo encargado de Eliminar un usuario por correo y contraseña
 * @param correo
 * @return
 */
	@Transactional
	public Usuario eliminarUsuario(String correo) {
		try {
			Util.ficheroLog("Entro en servicio eliminar");
			Optional<Usuario> usuario = usuarioRepository.findByCorreo(correo);
			if (usuario.isPresent()) {
				usuarioRepository.delete(usuario.get());
				return usuario.get();
			}
			return null;
		} catch (Exception e) {
			Util.ficheroLog("Ocurrio un error en servicio eliminar  usuario:"+e.getMessage());
		}
		return null;
	}
	


}
