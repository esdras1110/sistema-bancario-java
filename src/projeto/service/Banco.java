package projeto.service;

import projeto.model.Conta;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Conta> contas = new ArrayList<>();
    
    public boolean criarConta(Conta c){
        if(buscaConta(c.getNumero()) != null)
            return false;
        
        
        contas.add(c);
        return true;
    }

    public Conta buscaConta(int n){
        return contas.stream()
                    .filter(c -> c.getNumero() == n)
                    .findFirst()
                    .orElse(null);
    }

    public List<Conta> getContas(){
        return contas;
    }

    public int getQtdContas(){
        return contas.size();
    }

    public boolean transferir(int nContaAtual, int nContaDestino, double valor){
        
        if(nContaDestino == nContaAtual)
            return false;
        
        if(valor <= 0)
            return false;
        
        Conta contaDestino = buscaConta(nContaDestino);
        Conta contaAtual = buscaConta(nContaAtual);
        
        if(contaDestino == null)
            return false;

        if(contaAtual.getSaldo() < valor)
            return false;

        contaDestino.depositar(valor);
        contaAtual.sacar(valor);
        return true;
    }
}
