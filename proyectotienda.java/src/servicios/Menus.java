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
	public int mostrarMenuLogin() {
	    int opc = -1; // Inicializar con un valor inválido
	    boolean entradaValida = false;

	    while (!entradaValida) {
	        try {
	        	System.out.println("Seleccione una opción: ");
	            System.out.println("0. Para Salir");
	            System.out.println("1. Para Login");
	            System.out.println("2. ¿Olvidaste contraseña?");
	            

	            String input = sc.nextLine(); // Leer toda la línea para evitar problemas con el buffer
	            opc = Integer.parseInt(input); // Intentar convertir la entrada en un número

	            if (opc >= 0 && opc <= 2) { // Validar que esté dentro del rango permitido
	                entradaValida = true; // Salir del bucle si la entrada es correcta
	            } else {
	                System.out.println("⚠ Error: Opción fuera de rango. Inténtelo de nuevo.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("⚠ Error: Ingrese solo números enteros. Inténtelo de nuevo.");
	        }
	    }
	    
	    return opc;
	}

	
}
