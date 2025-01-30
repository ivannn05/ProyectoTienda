package servicios;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Scanner;

import controladores.Inicio;
import dtos.Usuario;
import util.Utilidades;

public class CrudUsuario {
	Scanner sc = new Scanner(System.in);
	Utilidades util= new Utilidades();
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
			usuario.setCorreo(sc.next());
			System.out.println("Escriba su direccion");
			usuario.setDireccion(sc.next());
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

	public Usuario eliminarUsu() {
	    Usuario usuario = new Usuario();
	    System.out.println("Total de usuarios cargados:" + Inicio.listaUsuarios.size());
	    String correo, contrasena;
	    System.out.println("Escriba el correo del usuario a eliminar ");
	    correo = sc.next();
	    System.out.println("Escriba la contraseña del usuario a eliminar ");
	    contrasena = sc.next();
String contrasenaEncripatada=util.encriptarContraseña(contrasena);
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
				System.out.println("1. Nombre");
				System.out.println("2. Apellidos");
				System.out.println("3. Correo");
				System.out.println("4. Dirección");
				System.out.println("5. Contraseña");
				System.out.println("6. Rol");
				System.out.println("7. Salir");
				System.out.print("Opción: ");
				int opcion = sc.nextInt();

				switch (opcion) {
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
				case 7:
					continuar = false;
					System.out.println("Actualización de usuario finalizada.");
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
