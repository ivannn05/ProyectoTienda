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
		opc=sc.nextInt();
		return opc;
		
	}
	
}
