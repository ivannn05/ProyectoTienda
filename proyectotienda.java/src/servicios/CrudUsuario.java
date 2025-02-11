package servicios;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import controladores.Inicio;
import dtos.Usuario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import util.Utilidades;
@Service
public class CrudUsuario {
	Scanner sc = new Scanner(System.in);
	Utilidades util = new Utilidades();
	@Autowired
   

    // Método para cambiar la contraseña de un usuario
    public Usuario cambiarContrasena(String correo) {
        System.out.println("Cambio de contraseña solicitado para: " + correo);
        
        String nuevaContrasena, repetirContrasena;
		do {
			System.out.println("Ingrese su nueva contraseña:");
			nuevaContrasena = sc.next();
			System.out.println("Confirme su nueva contraseña:");
			repetirContrasena = sc.next();

			if (!nuevaContrasena.equals(repetirContrasena)) {
				System.out.println("Las contraseñas no coinciden. Intente nuevamente.");
			}
		} while (!nuevaContrasena.equals(repetirContrasena));

        // Buscar usuario en la lista
        Usuario usuario = Inicio.listaUsuarios.stream()
                .filter(usu -> usu.getCorreo().equalsIgnoreCase(correo))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return null;
        }

       

        // Encriptar la contraseña antes de guardarla
        usuario.setContrasena(Utilidades.encriptarContraseña(nuevaContrasena));

        // Generar Token único
        String token = UUID.randomUUID().toString();
        usuario.setToken(token);

        // Calcular la fecha de expiración del token (1 hora desde ahora)
        usuario.setFechaToken(new Timestamp(System.currentTimeMillis() + 3600000));

        // Actualizar la lista de usuarios
        Inicio.listaUsuarios.replaceAll(usu -> usu.getCorreo().equalsIgnoreCase(correo) ? usuario : usu);

        System.out.println("Contraseña cambiada con éxito.");

        // Enviar correo de confirmación (No enviamos la nueva contraseña por seguridad)
        try {
            enviarCorreo(usuario.getCorreo(), "Cambio de contraseña exitoso", 
                    "Su contraseña ha sido cambiada correctamente. Si no fue usted, contacte con soporte.\n"+token);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Hubo un problema en el envío del correo.");
        }

        return usuario;
    }

    // Método para enviar correos
    public void enviarCorreo(String correoDestinatario, String asunto, String mensaje) throws MessagingException {
    	JavaMailSender mailSender = configurarServidorSMTP();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(correoDestinatario);
        helper.setSubject(asunto);
        helper.setText(mensaje, false); // false = texto plano
        helper.setFrom("bonsaissur@gmail.com");

        mailSender.send(mimeMessage);
        System.out.println("[Correo enviado a " + correoDestinatario + "]");
    }

    // Configuración del servidor SMTP (solo si no inyectas JavaMailSender desde Spring)
    private JavaMailSender configurarServidorSMTP() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("bonsaissur@gmail.com");
        mailSender.setPassword("msprjeksnbhekmjc");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        mailSender.setJavaMailProperties(props);
        return mailSender;
    }

	
	public Usuario AniadirUsu() {

		Usuario usuario;
		try {
			usuario = new Usuario();
			System.out.println("Bienbenido a registro Usuario ");

			System.out.println("Escriba su nombre");
			usuario.setNombre(sc.next());
			System.out.println("Escriba su primer apellido");
			String primerApellido = sc.next();
			System.out.println("Escriba su segundo apellido");
			String segundoApellido = sc.next();
			usuario.setApellidos(primerApellido + " " + segundoApellido);
			System.out.println("Escriba su correo");
			String correo1;
			String correo2;
			correo1 = sc.next();
			do {
				System.out.println("Escriba de nuevo su correo");
				correo2 = sc.next();
				if (!correo1.equals(correo2)) {
					System.out.println("\tLos correos no coinciden ");
				}
			} while (!correo1.equals(correo2));

			usuario.setCorreo(correo2);

			System.out.println("Escriba su direccion");
			usuario.setDireccion(sc.next());
			System.out.println("Escriba su telefono");
			
			usuario.setTelefono(sc.next());
			System.out.println("Escriba su contraseña");
			sc.nextLine();
			usuario.setContrasena(util.encriptarContraseña(sc.nextLine()));
			Timestamp fechaRegistro = Timestamp.from(Instant.now());
			usuario.setFechaRegistro(fechaRegistro);
			String rol = "";
			boolean a = true;
			while (a) {
				System.out.println("Escriba su ROL -> Usuario o Administrador: ");
				rol = sc.next();

				// Verificar si la entrada no está vacía y es válida
				if ((rol.equalsIgnoreCase("Usuario") || rol.equalsIgnoreCase("Administrador"))) {
					a = false;
					break; // Sale del bucle si la entrada es válida
				}
				System.out.println("Entrada no válida. Por favor, escriba 'Usuario' o 'Administrador'.");
			}
			usuario.setRol(rol);
			Inicio.listaUsuarios.add(usuario);
			System.out.println("Usurio registrado con exito");
			return usuario;
		} catch (Exception e) {
			System.out.println("Ocurrio un error en AniadirUsu :" + e.getMessage());
			e.printStackTrace();
		}
		return null;

	}

	public Usuario loginUsu() {
		Usuario usuario = new Usuario();
		String correo, contrasena;
		System.out.println("Escriba el correo del usuario  ");
		correo = sc.next();
		System.out.println("Escriba la contraseña del usuario  ");
		contrasena = sc.next();
		String contrasenaEncripatada = util.encriptarContraseña(contrasena);
		for (Usuario u : Inicio.listaUsuarios) {
			if (u.getCorreo().equalsIgnoreCase(correo) && u.getContrasena().equalsIgnoreCase(contrasenaEncripatada)) {
				usuario = u;
				break;
			}
		}
		return usuario;
	}

	public Usuario eliminarUsu() {
		Usuario usuario = new Usuario();
		System.out.println("Total de usuarios cargados:" + Inicio.listaUsuarios.size());
		String correo, contrasena;
		boolean aux = true;
		do {

			System.out.println("Escriba el correo del usuario a eliminar ");
			correo = sc.next();
			System.out.println("Escriba la contraseña del usuario a eliminar ");
			contrasena = sc.next();
			System.out.println("¿Esta seguro de eliminar? S o N");
			String rsp = sc.next();
			if (rsp.equalsIgnoreCase("S")) {
				aux = false;
			} else {
				break;
			}
		} while (aux == true);

		String contrasenaEncripatada = util.encriptarContraseña(contrasena);
		for (Usuario u : Inicio.listaUsuarios) {
			if (u.getCorreo().equalsIgnoreCase(correo) && u.getContrasena().equalsIgnoreCase(contrasenaEncripatada)) {
				usuario = u;
				break;
			}
		}
		return usuario;
	}

	public Usuario actualizarUsu(String correo) {
		Usuario usuario = new Usuario();

		System.out.println("Total de usuarios cargados:" + Inicio.listaUsuarios.size());

		for (Usuario u : Inicio.listaUsuarios) {
			if (u.getCorreo().equalsIgnoreCase(correo)) {
				usuario = u;
				break;
			}
		}
		System.out.println();

		try {
			System.out.println("Bienvenido a la actualización de usuario");
			boolean continuar = true;

			while (continuar) {
				System.out.println("\tSeleccione el numero para actualizar:");
				System.out.println("0. Salir");
				System.out.println("1. Nombre");
				System.out.println("2. Apellidos");
				System.out.println("3. Correo");
				System.out.println("4. Dirección");
				System.out.println("5. Contraseña");
				System.out.println("6. Telefono");
				System.out.println("7. Rol");
				System.out.print("Opción: ");
				int opcion = sc.nextInt();

				switch (opcion) {
				case 0:
					continuar = false;
					System.out.println("Actualización de usuario finalizada.");
					break;
				case 1:
					System.out.println("Escriba el nuevo nombre:");
					sc.nextLine();
					usuario.setNombre(sc.nextLine());
					break;
				case 2:
					System.out.println("Escriba el nuevo primer apellido:");

					String primerApellido = sc.next();
					System.out.println("Escriba el nuevo segundo apellido:");
					sc.nextLine();
					String segundoApellido = sc.next();
					usuario.setApellidos(primerApellido + " " + segundoApellido);
					break;
				case 3:
					System.out.println("Escriba el nuevo correo:");
					sc.nextLine();
					usuario.setCorreo(sc.nextLine());
					break;
				case 4:
					System.out.println("Escriba la nueva dirección:");
					sc.nextLine();
					usuario.setDireccion(sc.nextLine());
					break;
				case 5:
					System.out.println("Escriba la nueva contraseña:");
					sc.nextLine();

					usuario.setContrasena(util.encriptarContraseña(sc.nextLine()));
					break;
				case 6:
					System.out.println("Escriba su telefono:");
					usuario.setTelefono(sc.nextLine());

					break;
				case 7:
					String rol = "";
					boolean a = true;
					while (a) {
						System.out.println("Escriba su ROL -> Usuario o Administrador: ");
						sc.nextLine();
						rol = sc.next();

						// Verificar si la entrada no está vacía y es válida
						if ((rol.equalsIgnoreCase("Usuario") || rol.equalsIgnoreCase("Administrador"))) {
							a = false;
							break; // Sale del bucle si la entrada es válida
						}
						System.out.println("Entrada no válida. Por favor, escriba 'Usuario' o 'Administrador'.");
					}
					usuario.setRol(rol);
					break;

				default:
					System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
				}
			}

			System.out.println("Usuario actualizado con éxito.");

			return usuario;

		} catch (Exception e) {
			System.out.println("Ocurrió un error en actualizarUsu: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

}
/*
 * private Long id; private String nombre; private String apellidos; String
 * correo; private String direccion; private String contrasena; private
 * Timestamp fechaRegistro; private String rol;
 */
