package modelo;

import java.util.Scanner;

public class SystemFunction {

    public static int inputInteger(Scanner in)
    {
        while(!in.hasNextInt()) {
            System.out.println("Por favor Introduzca introduzca un valor numerico");
            in.next();
        }
        return in.nextInt();

    }
}
