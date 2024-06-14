package AV31;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Hospede extends Pessoa implements Serializable {
    private String enderecoCompleto;

    public Hospede(String cpf, String nome, String email, String enderecoCompleto) {
        super(cpf, nome, email);
        this.enderecoCompleto = enderecoCompleto;
    }

    

    public String getEnderecoCompleto() {
		return enderecoCompleto;
	}



	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}



	public boolean cadastrar(Hospede hospede) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("hospede.txt", true))) {
            writer.write(hospede.getCpf() + "," + hospede.getNome() + "," + hospede.getEmail() + "," + hospede.getEnderecoCompleto());
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }

	public boolean editar(Hospede hospede) {
        List<Hospede> hospedes = listar();
        boolean encontrado = false;
        for (int i = 0; i < hospedes.size(); i++) {
            if (hospedes.get(i).getCpf().equals(hospede.getCpf())) {
                hospedes.set(i, hospede);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("hospede.txt"))) {
            for (Hospede h : hospedes) {
                writer.write(h.getCpf() + "," + h.getNome() + "," + h.getEmail() + "," + h.getEnderecoCompleto());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao editar: " + e.getMessage());
            return false;
        }
    }
	 public List<Hospede> listar() {
	        List<Hospede> hospedes = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader("hospede.txt"))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length == 4) {
	                    Hospede hospede = new Hospede(line, line, line, line);
	                    hospede.setCpf(parts[0]);
	                    hospede.setNome(parts[1]);
	                    hospede.setEmail(parts[2]);
	                    hospede.setEnderecoCompleto(parts[3]);
	                    hospedes.add(hospede);
	                } else {
	                    System.err.println("Linha inválida: " + line);
	                }
	            }
	        } catch (IOException e) {
	            System.err.println("Erro ao listar: " + e.getMessage());
	        }
	        return hospedes;
	    }
    public Hospede consultar(String cpf) {
        List<Hospede> hospedes = listar();
        for (Hospede h : hospedes) {
            if (h.getCpf().equals(cpf)) {
                return h;
            }
        }
        return null;
    }
}
