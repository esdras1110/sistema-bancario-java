package projeto.ui;

import java.util.List;
import java.util.Scanner;
import projeto.model.Cliente;
import projeto.model.Conta;
import projeto.service.Banco;

public class Menu {
    private Scanner sc;
    private Banco banco;

    public Menu(Scanner sc, Banco banco){
        this.sc = sc;
        this.banco = banco;
    }

    public void menuPrincipal(){
        int opc = -1;
        String mens = "\n\n\n\n=================================\n"+
                        "         SISTEMA BANCÁRIO\n"+
                        "=================================\n"+
                        "1 - Criar conta\n"+
                        "2 - Listar contas\n"+
                        "3 - Acessar conta\n"+
                        "0 - Sair\n"+
                        "=================================\n"+
                        "Escolha uma opção: ";
        do {
            opc = lerInt(mens);

            switch (opc) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    listarContas();
                    break;
                case 3:
                    acessarConta();
                    break;
                case 0:
                    System.out.println("Saindo do Programa");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opc != 0);

    }

    private void criarConta(){
        System.out.println("\n\n\n\n========== CRIAR CONTA ==========");
                    int numero_conta = lerInt("Digite o número da conta: ");
                    System.out.print("Digite o nome do titular: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    double saldo = lerDouble("Digite o saldo inicial: ");

                    Cliente cliente = new Cliente(nome, cpf);
                    boolean situacao = banco.criarConta(new Conta(numero_conta, cliente, saldo));

                    if(situacao){
                        System.out.println("Conta criada com sucesso!!!");
                    }else{
                        System.out.println("Erro na criação da conta!!!");
                    }

                    System.out.println("=================================");
    }
    
    private void listarContas(){
        if(banco.getQtdContas() > 0){
            System.out.println("\n\n\n\n======== CONTAS CADASTRADAS ========");
            List<Conta> contas = banco.getContas();
            contas.forEach(System.out::println);
            System.out.println("===================================");
        }else{
            System.out.println("Nenhuma conta cadastrada!!!");
        }
    }

    private void acessarConta(){
        System.out.println("\n\n\n\n========= ACESSAR CONTA =========");
                    int n_conta = lerInt("Digite o número da conta: ");
                    Conta conta = banco.buscaConta(n_conta);
                    if(conta == null){
                        System.out.println("Conta não encontrada!!!");
                        return ;
                    }

                    menuConta(conta);
    }

    private void menuConta(Conta conta){
        int opc = -1;
        do{
            System.out.println("\n\n\n\n================================");
            System.out.printf("       CONTA %d - %s          \n", conta.getNumero(), conta.getTitular().getNome());
            System.out.println("================================");
            System.out.printf(
                "Saldo Atual: R$%.2f\n"+
                "1 - Depositar\n"+
                "2 - Sacar\n" + 
                "3 - Transferir\n" + 
                "4 - Ver dados da conta\n" + 
                "0 - Voltar ao menu principal\n"
            ,conta.getSaldo());
            System.out.println("================================");
            opc = lerInt("Escolha uma opção: ");

            switch (opc) {
                case 1:
                    depositar(conta);
                    break;
                case 2:
                    sacar(conta);
                    break;
                case 3:
                    transferencia(conta);
                    break;
                case 4:
                    dadosConta(conta);
                    break;
                case 0:
                    System.out.println("\nSaindo da Conta!!!");
                    break;
            
                default:
                    System.out.println("Opção inválida");
                    break;
            }
                
        }while(opc != 0);
    }

    private void depositar(Conta conta){
        System.out.println("\n\n\n\n========== DEPÓSITO ==========");
        double valor = lerDouble("Digite o valor para depósito: ");

        boolean situacao = conta.depositar(valor);

        if(situacao){
            System.out.println("Deposito realizado com sucesso!!!");
        }else{
            System.out.println("Erro no deposito!!!");
        }
        System.out.println("==============================");
    }

    private void sacar(Conta conta){
        System.out.println("\n\n\n\n========== SAQUE ==========");

        double valor = lerDouble("Digite o valor para saque: ");

        boolean situacao = conta.sacar(valor);

        if(situacao){
            System.out.println("Saque realizado com sucesso!!!");
        }else{
            System.out.println("Erro no saque!!!");
        }
        System.out.println("==============================");
    }

    private void transferencia(Conta conta){
        System.out.println("\n\n\n\n========== TRANSFERÊNCIA ==========");
        int nContaDestino = lerInt("Digite o número da conta destino: ");
        double valor = lerDouble("Digite o valor da transferência: ");

        boolean situacao = banco.transferir(conta.getNumero(), nContaDestino, valor);

        if(situacao){
            System.out.println("Transferência realizada com sucesso!!!");
        }else{
            System.out.println("Erro na tranferência!!!");
        }
        System.out.println("==================================");
    }

    private void dadosConta(Conta conta){
        System.out.println("\n\n\n\n========== DADOS DA CONTA ==========");
        System.out.println(conta);
        System.out.println("===================================");
    }

    private int lerInt(String mensagem){
        do{
            try {
                System.out.print(mensagem);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número inteiro.");
            }
        }while(true);
    }

    private double lerDouble(String mensagem){
        do{
            try {
                System.out.print(mensagem);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um valor numérico.");
            }
        }while(true);
    }
}
