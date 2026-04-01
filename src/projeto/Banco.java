package projeto;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Conta> contas = new ArrayList<>();
    
    public void criarConta(Conta c){
        if(buscaConta(c.getNumero()) != null){
            System.out.println("Já existe uma conta com esse número!!!");
            return;
        }
        
        contas.add(c);
        System.out.println("Conta criada com sucesso!!!");
    }

    public Conta buscaConta(int n){
        return contas.stream()
                    .filter(c -> c.getNumero() == n)
                    .findFirst()
                    .orElse(null);
    }

    public void listarContas(){
        contas.stream()
                .forEach(System.out::println);
    }

    public int getQtdContas(){
        return contas.size();
    }
}
