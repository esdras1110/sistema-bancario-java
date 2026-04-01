package projeto;

import java.util.Scanner;
import projeto.service.Banco;
import projeto.ui.Menu;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();
        Menu menu = new Menu(sc, banco);

        menu.menuPrincipal();

        sc.close();
    }
}
