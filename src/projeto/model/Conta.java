package projeto.model;

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

    public boolean depositar(double valor){
        if(valor <= 0){
            return false;
        }
        saldo += valor;
        return true;
    }

    public boolean sacar(double valor){
        if(valor <= 0){
            return false;
        }else if(saldo < valor){
            return false;
        }
        saldo -= valor;
        return true;
    }

    @Override
    public String toString(){
        return String.format("Conta: %d | Titular: %s | Saldo: R$%.2f", numero, titular.getNome(), saldo);
    }
}