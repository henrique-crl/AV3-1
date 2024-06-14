package AV31;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa implements Serializable {
    private String setor;
    private String cargo;

    public Funcionario(String cpf, String nome, String email, String setor, String Cargo) {
        super(cpf, nome, email);
        this.setor = setor;
        this.cargo = cargo;
    }

    // Getters and Setters...

    public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public boolean cadastrar(Funcionario funcionario) {
        List<Funcionario> funcionarios = listar();
        funcionarios.add(funcionario);
        return salvarFuncionarios(funcionarios);
    }

    public boolean editar(Funcionario funcionario) {
        List<Funcionario> funcionarios = listar();
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getCpf().equals(funcionario.getCpf())) {
                funcionarios.set(i, funcionario);
                return salvarFuncionarios(funcionarios);
            }
        }
        return false;
    }

    public Funcionario consultar(int id) {
        List<Funcionario> funcionarios = listar();
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(id)) {
                return funcionario;
            }
        }
        return null;
    }

    public List<Funcionario> listar() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("funcionarios.dat"))) {
            funcionarios = (List<Funcionario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exception
        }
        return funcionarios;
    }

    private boolean salvarFuncionarios(List<Funcionario> funcionarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("funcionarios.dat"))) {
            oos.writeObject(funcionarios);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
