package AV31;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {
    private int codigo;
    private String descricao;
    private double valor;

    // Getters and Setters...

    

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
	
	public boolean cadastrar(Item item) {
        List<Item> itens = listar();
        itens.add(item);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("item.txt"))) {
            for (Item i : itens) {
                writer.write(i.getCodigo() + "," + i.getDescricao() + "," + i.getValor());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(Item item) {
        List<Item> itens = listar();
        for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i).getCodigo() == item.getCodigo()) {
                itens.set(i, item);
                break;
            }
        }
        return salvarItens(itens);
    }

    public Item consultar(int codigo) {
        List<Item> itens = listar();
        for (Item item : itens) {
            if (item.getCodigo() == codigo) {
                return item;
            }
        }
        return null;
    }

    private boolean salvarItens(List<Item> itens) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("item.txt"))) {
            for (Item i : itens) {
                writer.write(i.getCodigo() + "," + i.getDescricao() + "," + i.getValor());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar itens: " + e.getMessage());
            return false;
        }
    }

    public List<Item> listar() {
        List<Item> itens = new ArrayList<>();
        File arquivo = new File("item.txt");
        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    int codigo = Integer.parseInt(parts[0]);
                    String descricao = parts[1];
                    double valor = Double.parseDouble(parts[2]);
                    Item item = new Item();
                    item.setCodigo(codigo);
                    item.setDescricao(descricao);
                    item.setValor(valor);
                    itens.add(item);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return itens;
    }
}
