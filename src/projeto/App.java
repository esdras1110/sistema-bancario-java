package projeto;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();

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
            opc = lerInt(sc, mens);

            switch (opc) {
                case 1:
                    criarConta(sc, banco);
                    break;
                case 2:
                    listarContas(banco);
                    break;
                case 3:
                    acessarConta(sc, banco);
                    break;
                case 0:
                    System.out.println("Saindo do Programa");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opc != 0);

        sc.close();
    }

    public static void criarConta(Scanner sc, Banco banco){
        System.out.println("\n\n\n\n========== CRIAR CONTA ==========");
                    int numero_conta = lerInt(sc,"Digite o número da conta: ");
                    System.out.print("Digite o nome do titular: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    double saldo = lerDouble(sc, "Digite o saldo inicial: ");

                    Cliente cliente = new Cliente(nome, cpf);
                    banco.criarConta(new Conta(numero_conta, cliente, saldo));

                    System.out.println("=================================");
    }

    public static void listarContas(Banco banco){
        if(banco.getQtdContas() > 0){
            System.out.println("\n\n\n\n======== CONTAS CADASTRADAS ========");
            banco.listarContas();
            System.out.println("===================================");
        }else{
            System.out.println("Nenhuma conta cadastrada!!!");
        }
    }

    public static void acessarConta(Scanner sc, Banco banco){
        System.out.println("\n\n\n\n========= ACESSAR CONTA =========");
                    int n_conta = lerInt(sc,"Digite o número da conta: ");
                    Conta conta = banco.buscaConta(n_conta);
                    if(conta == null){
                        System.out.println("Conta não encontrada!!!");
                        return ;
                    }

                    acessarConta(sc, banco, conta);
    }

    public static void acessarConta(Scanner sc, Banco banco, Conta conta){
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
            opc = lerInt(sc,"Escolha uma opção: ");

            switch (opc) {
                case 1:
                    depositar(sc, conta);
                    break;
                case 2:
                    sacar(sc, conta);
                    break;
                case 3:
                    transferencia(sc, banco, conta);
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

    public static void depositar(Scanner sc, Conta conta){
        System.out.println("\n\n\n\n========== DEPÓSITO ==========");
        double valor = lerDouble(sc, "Digite o valor para depósito: ");

        conta.depositar(valor);
        System.out.println("==============================");
    }

    public static void sacar(Scanner sc, Conta conta){
        System.out.println("\n\n\n\n========== SAQUE ==========");

        double valor = lerDouble(sc,"Digite o valor para saque: ");

        conta.sacar(valor);
        System.out.println("==============================");
    }

    public static void transferencia(Scanner sc, Banco banco, Conta conta){
        System.out.println("\n\n\n\n========== TRANSFERÊNCIA ==========");
        int n_conta_destino = lerInt(sc,"Digite o número da conta destino: ");
        double valor = lerDouble(sc, "Digite o valor da transferência: ");

        conta.transferir(banco, n_conta_destino, valor);
        System.out.println("==================================");
    }

    public static void dadosConta(Conta conta){
        System.out.println("\n\n\n\n========== DADOS DA CONTA ==========");
        System.out.println(conta.toString());
        System.out.println("===================================");
    }

    public static int lerInt(Scanner sc, String mensagem){
        do{
            try {
                System.out.print(mensagem);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número inteiro.");
            }
        }while(true);
    }

    public static double lerDouble(Scanner sc, String mensagem){
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
