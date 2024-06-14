package AV31;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Servico implements Serializable {
    private int codigo;
    private String descricao;
    private double valor;

    // Getters and Setters...

    public boolean cadastrar(Servico servico) {
        List<Servico> servicos = listar();
        servicos.add(servico);
        return salvarServicos(servicos);
    }

    public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean editar(Servico servico) {
        List<Servico> servicos = listar();
        for (int i = 0; i < servicos.size(); i++) {
            if (servicos.get(i).getCodigo() == servico.getCodigo()) {
                servicos.set(i, servico);
                return salvarServicos(servicos);
            }
        }
        return false;
    }

    public Servico consultar(int codigo) {
        List<Servico> servicos = listar();
        for (Servico servico : servicos) {
            if (servico.getCodigo() == codigo) {
                return servico;
            }
        }
        return null;
    }

    public List<Servico> listar() {
        List<Servico> servicos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("servicos.dat"))) {
            servicos = (List<Servico>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exception
        }
        return servicos;
    }

    private boolean salvarServicos(List<Servico> servicos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("servicos.dat"))) {
            oos.writeObject(servicos);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
