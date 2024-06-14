package AV31;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Scanner para ler entradas do usuário
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Loop infinito para mostrar o menu até o usuário escolher sair
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Categoria");
            System.out.println("2. Quarto");
            System.out.println("3. Item");
            System.out.println("4. Reserva");
            System.out.println("5. Hospede");
            System.out.println("0. Sair");

            // Ler a escolha do usuário
            int choice = Integer.parseInt(scanner.nextLine());
            // Chamar o método correspondente baseado na escolha do usuário
            switch (choice) {
                case 1:
                    handleCategoria();
                    break;
                case 2:
                    handleQuarto();
                    break;
                case 3:
                    handleItem();
                    break;
                case 4:
                    handleReserva();
                    break;
                case 5:
                    handleHospede();
                    break;
                /*case 6:
                    handleConsumoServico();
                    break;
                case 7:
                    handleServico();
                    break;
                case 8:
                    handleCategoriaItem();
                    break;
                case 9:
                    handleFuncionario();
                    break;*/
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
 
private static void handleCategoria() {
    Categoria categoria = new Categoria();
    Categoria categoria1 = new Categoria();
    System.out.println("1. Cadastrar Categoria");
    System.out.println("2. Editar Categoria");
    System.out.println("3. Consultar Categoria");
    System.out.println("4. Listar Categorias");
    int choice = Integer.parseInt(scanner.nextLine());
    switch (choice) {
        case 1:
            System.out.println("Cadastrando nova categoria:");
            System.out.print("Código: ");
            categoria1.setCodigo(Integer.parseInt(scanner.nextLine()));
            System.out.print("Descrição: ");
            categoria1.setDescricao(scanner.nextLine());
            System.out.print("Valor: ");
            categoria1.setValor(Double.parseDouble(scanner.nextLine()));
            if (categoria.cadastrar(categoria1)) {
                System.out.println("Categoria cadastrada com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar categoria.");
            }
            break;
        case 2:
            System.out.print("Código: ");
            int codigo = Integer.parseInt(scanner.nextLine());
            Categoria cat = categoria.consultar(codigo);
            if (cat != null) {
                System.out.print("Nova Descrição: ");
                cat.setDescricao(scanner.nextLine());
                System.out.print("Novo Valor: ");
                cat.setValor(Double.parseDouble(scanner.nextLine()));
                if (categoria.editar(cat)) {
                    System.out.println("Categoria editada com sucesso!");
                } else {
                    System.out.println("Erro ao editar categoria.");
                }
            } else {
                System.out.println("Categoria não encontrada!");
            }
            break;
        case 3:
            System.out.print("Código: ");
            codigo = Integer.parseInt(scanner.nextLine());
            cat = categoria.consultar(codigo);
            if (cat != null) {
                System.out.println("Código: " + cat.getCodigo());
                System.out.println("Descrição: " + cat.getDescricao());
                System.out.println("Valor: " + cat.getValor());
            } else {
                System.out.println("Categoria não encontrada!");
            }
            break;
        case 4:
            List<Categoria> categorias = categoria.listar();
            for (Categoria c : categorias) {
                System.out.println("Código: " + c.getCodigo() + ", Descrição: " + c.getDescricao() + ", Valor: " + c.getValor());
            }
            break;
        default:
            System.out.println("Opção inválida!");
            break;
    }
}

private static void handleQuarto() {
    Quarto quarto = new Quarto(0, null, null);
    System.out.println("1. Cadastrar Quarto");
    System.out.println("2. Editar Quarto");
    System.out.println("3. Consultar Quarto");
    System.out.println("4. Listar Quartos");
    int choice = Integer.parseInt(scanner.nextLine());
    switch (choice) {
        case 1:
            System.out.println("Cadastrando novo quarto:");
            System.out.print("Código: ");
            quarto.setCodigo(Integer.parseInt(scanner.nextLine()));
            System.out.print("Categoria: ");
            quarto.setCategoria(null, scanner.nextLine());
            System.out.print("Status: ");
            quarto.setStatus(scanner.nextLine());
            if (quarto.cadastrar(quarto)) {
                System.out.println("Quarto cadastrado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar quarto.");
            }
            break;
        case 2:
            System.out.print("Código: ");
            int codigo = Integer.parseInt(scanner.nextLine());
            Quarto q = quarto.consultar(codigo);
            if (q != null) {
                System.out.print("Nova Categoria: ");
                q.setCategoria(null, scanner.nextLine());
                System.out.print("Novo Status: ");
                q.setStatus(scanner.nextLine());
                if (quarto.editar(q)) {
                    System.out.println("Quarto editado com sucesso!");
                } else {
                    System.out.println("Erro ao editar quarto.");
                }
            } else {
                System.out.println("Quarto não encontrado!");
            }
            break;
        case 3:
            System.out.print("Código: ");
            codigo = Integer.parseInt(scanner.nextLine());
            q = quarto.consultar(codigo);
            if (q != null) {
                System.out.println("Código: " + q.getCodigo());
                System.out.println("Categoria: " + q.getCategoria());
                System.out.println("Status: " + q.getStatus());
            } else {
                System.out.println("Quarto não encontrado!");
            }
            break;
        case 4:
            List<Quarto> quartos = quarto.listar();
            for (Quarto qrt : quartos) {
                System.out.println("Código: " + qrt.getCodigo() + ", Categoria: " + qrt.getCategoria() + ", Status: " + qrt.getStatus());
            }
            break;
        default:
            System.out.println("Opção inválida!");
            break;
    }
}

    private static void handleItem() {
        Item item = new Item();
        System.out.println("1. Cadastrar Item");
        System.out.println("2. Editar Item");
        System.out.println("3. Consultar Item");
        System.out.println("4. Listar Itens");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                System.out.print("Código: ");
                item.setCodigo(Integer.parseInt(scanner.nextLine()));
                System.out.print("Descrição: ");
                item.setDescricao(scanner.nextLine());
                System.out.print("Valor: ");
                item.setValor(Double.parseDouble(scanner.nextLine()));
                item.cadastrar(item);
                break;
            case 2:
                System.out.print("Código: ");
                int codigo = Integer.parseInt(scanner.nextLine());
                Item itm = item.consultar(codigo);
                if (itm != null) {
                    System.out.print("Nova Descrição: ");
                    itm.setDescricao(scanner.nextLine());
                    System.out.print("Novo Valor: ");
                    itm.setValor(Double.parseDouble(scanner.nextLine()));
                    item.editar(itm);
                } else {
                    System.out.println("Item não encontrado!");
                }
                break;
            case 3:
                System.out.print("Código: ");
                codigo = Integer.parseInt(scanner.nextLine());
                itm = item.consultar(codigo);
                if (itm != null) {
                    System.out.println("Código: " + itm.getCodigo());
                    System.out.println("Descrição: " + itm.getDescricao());
                    System.out.println("Valor: " + itm.getValor());
                } else {
                    System.out.println("Item não encontrado!");
                }
                break;
            case 4:
                List<Item> itens = item.listar();
                for (Item i : itens) {
                    System.out.println("Código: " + i.getCodigo() + ", Descrição: " + i.getDescricao() + ", Valor: " + i.getValor());
                }
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private static void handleReserva() {
        Reserva reserva = new Reserva();
        System.out.println("1. Cadastrar Reserva");
        System.out.println("2. Editar Reserva");
        System.out.println("3. Consultar Reserva");
        System.out.println("4. Listar Reservas");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                System.out.print("Código: ");
                reserva.setCodigo(Integer.parseInt(scanner.nextLine()));
                System.out.print("Valor da Reserva: ");
                reserva.setValorReserva(Double.parseDouble(scanner.nextLine()));
                reserva.cadastrar(reserva);
                break;
            case 2:
                System.out.print("Código: ");
                int codigo = Integer.parseInt(scanner.nextLine());
                Reserva res = reserva.consultar(codigo);
                if (res != null) {
                    System.out.print("Novo Valor da Reserva: ");
                    res.setValorReserva(Double.parseDouble(scanner.nextLine()));
                    reserva.editar(res);
                } else {
                    System.out.println("Reserva não encontrada!");
                }
                break;
            case 3:
                System.out.print("Código: ");
                codigo = Integer.parseInt(scanner.nextLine());
                res = reserva.consultar(codigo);
                if (res != null) {
                    System.out.println("Código: " + res.getCodigo());
                    System.out.println("Valor da Reserva: " + res.getValorReserva());
                } else {
                    System.out.println("Reserva não encontrada!");
                }
                break;
            case 4:
                List<Reserva> reservas = reserva.listar();
                for (Reserva r : reservas) {
                    System.out.println("Código: " + r.getCodigo() + ", Valor da Reserva: " + r.getValorReserva());
                }
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
        }
    private static void handleHospede() {
        Hospede hospede = new Hospede(null, null, null, null);
        System.out.println("1. Cadastrar Hospede");
        System.out.println("2. Editar Hospede");
        System.out.println("3. Consultar Hospede");
        System.out.println("4. Listar Hospedes");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                System.out.println("Cadastrando novo hospede:");
                System.out.print("CPF: ");
                hospede.setCpf(scanner.nextLine());
                System.out.print("Nome: ");
                hospede.setNome(scanner.nextLine());
                System.out.print("Email: ");
                hospede.setEmail(scanner.nextLine());
                System.out.print("Endereço Completo: ");
                hospede.setEnderecoCompleto(scanner.nextLine());
                if (hospede.cadastrar(hospede)) {
                    System.out.println("Hospede cadastrado com sucesso!");
                } else {
                    System.out.println("Erro ao cadastrar hospede.");
                }
                break;
            case 2:
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                Hospede h = hospede.consultar(cpf);
                if (h != null) {
                    System.out.print("Novo Nome: ");
                    h.setNome(scanner.nextLine());
                    System.out.print("Novo Email: ");
                    h.setEmail(scanner.nextLine());
                    System.out.print("Novo Endereço Completo: ");
                    h.setEnderecoCompleto(scanner.nextLine());
                    try {
                        System.out.print("Novo Valor (como número): ");
                        double novoValor = Double.parseDouble(scanner.nextLine());
                        if (hospede.editar(h)) {
                            System.out.println("Hospede editado com sucesso!");
                        } else {
                            System.out.println("Erro ao editar hospede.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido! Por favor, insira um número.");
                    }
                } else {
                    System.out.println("Hospede não encontrado!");
                }
                break;
            case 3:
                System.out.print("CPF: ");
                cpf = scanner.nextLine();
                h = hospede.consultar(cpf);
                if (h != null) {
                    System.out.println("CPF: " + h.getCpf());
                    System.out.println("Nome: " + h.getNome());
                    System.out.println("Email: " + h.getEmail());
                    System.out.println("Endereço Completo: " + h.getEnderecoCompleto());
                } else {
                    System.out.println("Hospede não encontrado!");
                }
                break;
            case 4:
                List<Hospede> hospedes = hospede.listar();
                for (Hospede hosp : hospedes) {
                    System.out.println("CPF: " + hosp.getCpf() + ", Nome: " + hosp.getNome() + ", Email: " + hosp.getEmail() + ", Endereço Completo: " + hosp.getEnderecoCompleto());
                }
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    

    


    
    
    
}