package servicios;

import java.util.Scanner;

public class Menus {

	Scanner sc= new Scanner(System.in);

	
	public int mostrarMenuInicial() {
		int opc;
		System.out.println("Seleccione una aopcion");
		System.out.println("0.Cerrar Menu");
		System.out.println("1.Registro de Usuario");
		System.out.println("2.Actualizar Usuario");
		System.out.println("3.Eliminar Usuario");
		System.out.println("4.Login de Usuario");
		opc=sc.nextInt();
		return opc;
		
	}
	public int mostrarMenuLogin() {
		int opc;
		System.out.println("0.Para Salir");
		System.out.println("1.Para login");
		System.out.println("2.¿Olvidaste contraseña?");
		opc=sc.nextInt();
		return opc;
	}
	
}
