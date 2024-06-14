package AV31;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaItem implements Serializable {
    private Item item;
    private Categoria categoria;
    private int quantidade;

    // Getters and Setters...

    public boolean cadastrar(CategoriaItem categoriaItem) {
        List<CategoriaItem> categoriaItems = listar();
        categoriaItems.add(categoriaItem);
        return salvarCategoriaItems(categoriaItems);
    }

    public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public boolean editar(CategoriaItem categoriaItem) {
        List<CategoriaItem> categoriaItems = listar();
        for (int i = 0; i < categoriaItems.size(); i++) {
            if (categoriaItems.get(i).getItem().getCodigo() == categoriaItem.getItem().getCodigo() &&
                categoriaItems.get(i).getCategoria().getCodigo() == categoriaItem.getCategoria().getCodigo()) {
                categoriaItems.set(i, categoriaItem);
                return salvarCategoriaItems(categoriaItems);
            }
        }
        return false;
    }

    public CategoriaItem consultar(int codigoItem, int codigoCategoria) {
        List<CategoriaItem> categoriaItems = listar();
        for (CategoriaItem categoriaItem : categoriaItems) {
            if (categoriaItem.getItem().getCodigo() == codigoItem &&
                categoriaItem.getCategoria().getCodigo() == codigoCategoria) {
                return categoriaItem;
            }
        }
        return null;
    }

    public List<CategoriaItem> listar() {
        List<CategoriaItem> categoriaItems = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("categoriaItems.dat"))) {
            categoriaItems = (List<CategoriaItem>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exception
        }
        return categoriaItems;
    }

    private boolean salvarCategoriaItems(List<CategoriaItem> categoriaItems) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("categoriaItems.dat"))) {
            oos.writeObject(categoriaItems);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
