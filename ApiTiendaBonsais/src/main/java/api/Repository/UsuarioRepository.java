package api.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.Daos.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	// Encuentra un usuario por correo y contraseña

	Optional<Usuario> findByCorreoAndContrasena(String correo, String contrasena);
	Optional<Usuario> findByCorreo(String correo);
	
	

}
