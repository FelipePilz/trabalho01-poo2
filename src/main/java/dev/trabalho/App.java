package dev.trabalho;

import dev.trabalho.dao.cliente.Cliente;
import dev.trabalho.dao.cliente.ClienteDAO;
import dev.trabalho.dao.contrato.Contrato;
import dev.trabalho.dao.contrato.ContratoDAO;
import dev.trabalho.dao.imovel.Imovel;
import dev.trabalho.dao.imovel.ImovelDAO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {

    public static ClienteDAO clienteDAO = new ClienteDAO();
    public static ImovelDAO imovelDAO = new ImovelDAO();
    public static ContratoDAO contratoDAO = new ContratoDAO();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Sistema de Gestão da Imobiliária ===");
            System.out.println("1. Cadastrar");
            System.out.println("2. Consultar");
            System.out.println("3. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuCadastrar();
                    break;

                case 2:
                    menuConsultar();
                    break;

                case 3:
                    menuRelatorios();
                    break;

                case 0:
                    running = false;
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }

    private static void menuCadastrar() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Menu de Cadastro ===");
            System.out.println("1. Cadastrar imóvel");
            System.out.println("2. Cadastrar cliente");
            System.out.println("3. Cadastrar contrato");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");


            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarImovel();
                    break;

                case 2:
                    cadastrarCliente();
                    break;

                case 3:
                    cadastrarContrato();
                    break;

                case 0:
                    running = false;
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuConsultar() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Menu de Consulta ===");
            System.out.println("1. Consultar imóveis");
            System.out.println("2. Consultar clientes");
            System.out.println("3. Consultar contratos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    consultarImoveis();
                    break;

                case 2:
                    consultarClientes();
                    break;

                case 3:
                    consultarContratos();
                    break;

                case 0:
                    running = false;
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void consultarImoveis() {
        try {
            List<Imovel> imoveis = imovelDAO.find();
            System.out.println("=== Imóveis ===");
            for (Imovel i : imoveis) {
                System.out.println(i);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void consultarClientes() {
        try {
            List<Cliente> clientes = clienteDAO.find();
            System.out.println("=== Clientes ===");
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void consultarContratos() {
        try {
            List<Contrato> contratos = contratoDAO.find();
            System.out.println("=== Contratos ===");
            for (Contrato c : contratos) {
                System.out.println(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void menuRelatorios() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Menu de Relatórios ===");
            System.out.println("1. Listar imóveis disponíveis");
            System.out.println("2. Listar contratos ativos");
            System.out.println("3. Cliente com mais contratos");
            System.out.println("4. Contratos expirando nos próximos 30 dias");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarImoveisDisponiveis();
                    break;

                case 2:
                    listarContratosAtivos();
                    break;

                case 3:
                    listarClienteComMaisContratos();
                    break;

                case 4:
                    listarContratosExpirandoNosProximos30Dias();
                    break;

                case 0:
                    running = false;
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarImovel() {
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Tem garagem? (true/false): ");
        boolean garagem = scanner.nextBoolean();

        try {
            imovelDAO.create(new Imovel(endereco, descricao, garagem));
            System.out.println("Imóvel cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Endereço: ");
        String enderecoCliente = scanner.nextLine();

        try {
            clienteDAO.create(new Cliente(nome, cpf, telefone, email, enderecoCliente));
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cadastrarContrato() {
        System.out.print("ID do imóvel: ");
        int idImovel = scanner.nextInt();
        System.out.print("ID do cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Data de início (yyyy-MM-dd): ");
        Date dataInicio = Date.valueOf(scanner.nextLine());
        System.out.print("Data de fim (yyyy-MM-dd): ");
        Date dataFim = Date.valueOf(scanner.nextLine());
        System.out.print("Valor do contrato: ");
        BigDecimal valorContrato = scanner.nextBigDecimal();
        System.out.print("Ativo? (true/false): ");
        boolean ativo = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Observações: ");
        String observacoes = scanner.nextLine();

        try {
            contratoDAO.create(new Contrato(idImovel, idCliente, dataInicio, dataFim, valorContrato, ativo, observacoes));
            System.out.println("Contrato cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarImoveisDisponiveis() {
        try {
            List<Imovel> disponiveis = imovelDAO.findDisponiveis();

            System.out.println("=== Imóveis disponíveis ===");
            for (Imovel i : disponiveis) {
                System.out.println(i);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarContratosAtivos() {
        try {
            List<Contrato> contratosAtivos = contratoDAO.findAtivos();
            System.out.println("=== Contratos ativos ===");
            for (Contrato c : contratosAtivos) {
                System.out.println(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarClienteComMaisContratos() {
        try {
            Cliente topCliente = clienteDAO.findClienteComMaisContratos();
            System.out.println("=== Cliente com mais contratos ===");
            System.out.println(topCliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarContratosExpirandoNosProximos30Dias() {
        try {
            List<Contrato> proximosExpirar = contratoDAO.findContratosExpirandoProximos30Dias();
            System.out.println("=== Contratos expirando nos próximos 30 dias ===");
            for (Contrato c : proximosExpirar) {
                System.out.println(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
