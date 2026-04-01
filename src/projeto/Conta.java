package projeto;
public class Conta{
    private int numero;
    private Cliente titular;
    private double saldo;

    public Conta(int numero, Cliente titular, double saldo){
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }
    public Cliente getTitular() {
        return titular;
    }
    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor){
        if(valor <= 0){
            System.out.println("Valor inválido");
            return ;
        }
        System.out.println("Depósito realizado com sucesso!");
        saldo += valor;
    }

    public void sacar(double valor){
        if(valor <= 0){
            System.out.println("Valor Inválido");
            return ;
        }else if(saldo < valor){
            System.out.println("Saldo Insuficiente");
            return ;
        }
        System.out.println("Saque realizado com sucesso!");
        saldo -= valor;
    }

    public void transferir(Banco banco, int n_conta_destino, double valor){
        
        if(n_conta_destino == numero){
            System.out.println("Não é possível transferir para a própria conta!!!");
            return;
        }
        
        if(valor <= 0){
            System.out.println("Valor Inválido");
            return ;
        }
        
        Conta conta = banco.buscaConta(n_conta_destino);
        
        if(conta == null){
            System.out.println("Conta não encontrada!!!");
            return ;
        }

        if(saldo < valor){
            System.out.println("Saldo Insuficiente!!!");
            return ;
        }

        System.out.println("Transferência realizada com sucesso!");
        conta.depositar(valor);
        saldo -= valor;
    }

    @Override
    public String toString(){
        return String.format("Conta: %d | Titular: %s | Saldo: R$%.2f", numero, titular.getNome(), saldo);
    }
}