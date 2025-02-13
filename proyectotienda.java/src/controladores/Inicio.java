package controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dtos.Usuario;
import servicios.Menus;

public class Inicio {
	public static List<Usuario> listaUsuarios = new ArrayList<Usuario>();
	public static Usuario UsuarioLogeado;

	public static void main(String[] args) {
		Menus mi = new Menus();
		MetodosHTTP mHttp = new MetodosHTTP();
		Scanner sc = new Scanner(System.in);
		boolean cerraMenu = false;
		boolean usuarioLogueado = false; // Controla si el usuario ya inició sesión
		String valor = "";

		while (!cerraMenu) {
			if (!usuarioLogueado) { // Solo muestra el login si el usuario no está logueado
				System.out.println("Bienvenido a Login de Usuario");

				boolean cerraMenu1 = false;
				while (!cerraMenu1) {
					int opc1 = mi.mostrarMenuLogin();

					switch (opc1) {
					case 0:
						System.out.println("Se cerrará el menú login");
						cerraMenu1 = true;
						cerraMenu = true;
						break;
					case 1:
						System.out.println("Seleccionó Login");
						valor = mHttp.Login();
						if (valor.equals("Login exitoso")) {
							usuarioLogueado = true; // Marcar que el usuario ya ingresó
							cerraMenu1 = true; // Salir del login
						}
						break;
					case 2:
						System.out.println("Seleccionó Olvidó Contraseña");
						System.out.print("\tEscriba el correo: ");
						String correo1 = sc.next();
						mHttp.UpdateContrasena(correo1);
						break;
					default:
						System.out.println("Opción no válida.");
						break;
					}
				}
			}

			// Si el usuario está logueado, mostrar el menú según su rol
			while (usuarioLogueado) {
				String rol = UsuarioLogeado.getRol(); // Obtener el rol 
				System.out.println(UsuarioLogeado.toString());
				int opc = -1;

				// Mostrar menú según el rol
				if (rol.equals("Administrador")) {
					System.out.println("\nMenú Administrador:");
					System.out.println("1. Registrar Usuario");
					System.out.println("2. Actualizar Usuario");
					System.out.println("3. Eliminar Usuario");
					System.out.println("0. Cerrar Sesión");
					System.out.print("Seleccione una opción: ");
					opc = sc.nextInt();
				} else if (rol.equals("usuario")) {
					System.out.println("\nMenú Usuario:");
					System.out.println("1. Registrar Usuario");
					System.out.println("0. Cerrar Sesión");
					System.out.print("Seleccione una opción: ");
					opc = sc.nextInt();
				} else {
					System.out.println("Rol no reconocido. Cerrando sesión...");
					usuarioLogueado = false;
					break;
				}

				// Ejecución de opciones según el rol
				switch (opc) {
				case 0:
					System.out.println("Cerrando sesión...");
					usuarioLogueado = false;
					break;
				case 1:
					System.out.println("Seleccionó Registro de Usuario");
					mHttp.Post();
					System.out.println("###########################");
					break;
				case 2:
					if (rol.equals("administrador")) {
						System.out.println("Seleccionó Actualizar Usuario");
						mHttp.Put(UsuarioLogeado.getCorreo());
						System.out.println("###########################");
					} else {
						System.out.println("No tiene permisos para esta opción.");
					}
					break;
				case 3:
					if (rol.equals("administrador")) {
						System.out.println("Seleccionó Eliminar Usuario");
						mHttp.Delete();
						System.out.println("###########################");
					} else {
						System.out.println("No tiene permisos para esta opción.");
					}
					break;
				default:
					System.out.println("Opción no válida.");
					break;
				}
			}
		}
	}
}
