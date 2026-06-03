import java.time.LocalDate;
import java.util.Scanner;

public class App {

    public static Cupom lerCupom() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Informe o código do cupom: ");
        long codigo = sc.nextLong();
        System.out.print("Percentual de desconto: ");
        double desconto = sc.nextDouble();
        System.out.print("Data de validade (AAAA-MM-DD): ");
        LocalDate dataValidade = LocalDate.parse(sc.next());
        return new Cupom(codigo, desconto, dataValidade);
    }

    private static int menu(Scanner scanner) {
        System.out.println("\t\t*** IFSULDEMINAS - CAMPUS MACHADO ***");
        System.out.println("\t\t*** Estrutura de Dados I ***");
        System.out.println("\t\t*** HASH ENCADEADO - Separate Chaining ***");
        System.out.println("1-Inserir Cupom");
        System.out.println("3-Utilizar Cupom");
        System.out.println("0-Sair");
        System.out.print("Selecione uma opcao: ");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomHashMap<Cupom> meuHashMap = new CustomHashMap<>();
        int op;

        do {
            System.out.println(meuHashMap);
            System.out.println("Pressione Enter para continuar...");
            scanner.nextLine();
            op = menu(scanner);

            switch (op) {
                case 1:
                    Cupom novoCupom = lerCupom();
                    meuHashMap.put(novoCupom.getCodigo(), novoCupom);
                    break;

                case 3:
                    System.out.print("Cupom para uso: ");
                    long codigo = scanner.nextLong();
                    scanner.nextLine();

                    Cupom cupomEncontrado = meuHashMap.get(codigo);

                    if (cupomEncontrado != null) {
                        System.out.println("Desconto encontrado: " + cupomEncontrado.getDesconto() + "%");

                        if (cupomEncontrado.getDataValidade().isBefore(LocalDate.now())) {
                            System.out.println("Cupom vencido!");
                        } else {
                            System.out.print("Valor da compra: ");
                            double valorCompra = scanner.nextDouble();
                            scanner.nextLine();

                            double valorDesconto = valorCompra * (cupomEncontrado.getDesconto() / 100);
                            double valorFinal = valorCompra - valorDesconto;

                            System.out.printf("Valor original: R$ %.2f%n", valorCompra);
                            System.out.printf("Desconto obtido: R$ %.2f%n", valorDesconto);
                            System.out.printf("Valor final: R$ %.2f%n", valorFinal);
                        }
                    } else {
                        System.out.println("Cupom não encontrado.");
                    }
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
                    break;
            }

        } while (op != 0);

        scanner.close();
    }
}