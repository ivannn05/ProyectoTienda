package controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dtos.Usuario;
import servicios.Menus;
import util.Utilidades;
import servicios.CrudUsuario;

public class Inicio {
	public static List<Usuario> listaUsuarios = new ArrayList<Usuario>();

	public static void main(String[] args) {
		// FicheroInterfaz fi = new FicheroImplementacion();
		Menus mi = new Menus();
		CrudUsuario cu = new CrudUsuario();
		MetodosHTTP mHttp = new MetodosHTTP();
		// fi.cargaDeFichero();
		Scanner sc = new Scanner(System.in);

		boolean cerraMenu = false;
		while (!cerraMenu) {
			int opc;
			int opc1;
			opc = mi.mostrarMenuInicial();

			try {
				switch (opc) {
				case 0:
					System.out.println("Se cerrara el menu");
					// fi.ficheroLog("Se cerrara el menu");
					cerraMenu = true;
					break;
				case 1:
					System.out.println("Selecciono Registro De Usuario");
					mHttp.Post();
					System.out.println("########################################");
					break;

				case 2:
					System.out.println("Selecciono Actualizar Usuario");
					System.out.println("Escriba el correo de la persona paraa actualizar");

					String correo = sc.next();
					mHttp.Put(correo);
					System.out.println("########################################");
					break;
				case 3:
					System.out.println("Selecciono Eliminar Usuario");

					mHttp.Delete();
					System.out.println("########################################");
					break;

				case 4:
					System.out.println("Selecciono Login de  Usuario");
					boolean cerraMenu1 = false;
					while (!cerraMenu1) {
						opc1 = mi.mostrarMenuLogin();

						switch (opc1) {
						case 0:
							System.out.println("Se cerrara el menu");
							// fi.ficheroLog("Se cerrara el menu");
							cerraMenu1 = true;
							break;
						case 1:
							System.out.println("Selecciono Login");
							mHttp.Login();
							break;
						case 2:
							System.out.println("Selecciono Olvido Contraseña");
							System.out.println("\tEscriba el correo ");
							String correo1 = sc.next();
							mHttp.UpdateContrasena(correo1);
							break;

						default:
							break;
						}

						System.out.println("########################################");
					}
					break;

				default:
					System.out.println("Esta opcion no existe");
					// fi.ficheroLog("Esta opcion no existe");
					break;
				}
			} catch (Exception e) {
				System.out.println("Ocurrio un error en la aplicacion");
				Utilidades.ficheroLog(null, null);
				// fi.ficheroLog("Ocurrio un error en la aplicacion");
			}
		}
	}

}

/*
 * //fi.ficheroLog("Selecciono venta de vehículo");
 * System.out.println("Lista de datos:"); for (int i = 0; i <
 * listaUsuarios.size(); i++) { System.out.println(
 * listaUsuarios.get(i).toString().concat(listaUsuarios.get(i).toString()).
 * concat("\n")); }
 */