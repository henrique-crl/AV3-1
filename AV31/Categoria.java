package AV31;
import java.io.*;

import java.util.*;

public class Categoria {
    private int codigo;
    private String descricao;
    private double valor;

    public Categoria() {}

    public Categoria(int codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
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

    public boolean cadastrar(Categoria categoria) {
        List<Categoria> categorias = listar();
        categorias.add(categoria);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("categoria.txt"))) {
            for (Categoria cat : categorias) {
                writer.write(cat.getCodigo() + "," + cat.getDescricao() + "," + cat.getValor());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }

    public Categoria consultar(int codigo) {
        List<Categoria> categorias = listar();
        for (Categoria cat : categorias) {
            if (cat.getCodigo() == codigo) {
                return cat;
            }
        }
        return null;
    }

    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("categoria.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int codigo = Integer.parseInt(parts[0]);
                    String descricao = parts[1];
                    double valor = Double.parseDouble(parts[2]);
                    categorias.add(new Categoria(codigo, descricao, valor));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao listar categorias: " + e.getMessage());
        }
        return categorias;
    }

    public boolean editar(Categoria categoria) {
        List<Categoria> categorias = listar();
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getCodigo() == categoria.getCodigo()) {
                categorias.set(i, categoria);
                break;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("categoria.txt"))) {
            for (Categoria cat : categorias) {
                writer.write(cat.getCodigo() + "," + cat.getDescricao() + "," + cat.getValor());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao editar: " + e.getMessage());
            return false;
        }
    }

    private boolean salvarCategorias(List<Categoria> categorias) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("categoria.txt"))) {
            for (Categoria cat : categorias) {
                writer.write(cat.getCodigo() + "," + cat.getDescricao() + "," + cat.getValor());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar categorias: " + e.getMessage());
            return false;
        }
    }
}
