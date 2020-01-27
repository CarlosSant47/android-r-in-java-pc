package controlador;

import modelo.Context;

import java.io.Console;
import java.util.Scanner;

public class ConMenu {
    private boolean loopMenu = true;
    private Scanner scanner;
    private Context context;

    public ConMenu(Context context)
    {
        this.context = context;
        this.scanner = new Scanner(System.in);
        this.onViewMenu();
    }

    private void onViewMenu()
    {
        while (loopMenu)
        {
            System.out.println(context.getString("welcomeMeg"));
            for(String itemMenu: context.getStringArray("menuItems"))
            {
                System.out.println(itemMenu);
            }
            System.out.println(context.getString("selectedOption"));
            String input = scanner.next();
        }
    }

}
