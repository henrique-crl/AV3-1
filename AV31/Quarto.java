package AV31;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Quarto implements Serializable {
    private int codigo;
    private Categoria categoria;
    private String status;

    // Getters and Setters...


    public Quarto(int codigo2, String categoria2, String status2) {
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria, String string) {
		this.categoria = categoria;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public boolean cadastrar(Quarto quarto) {
	    List<Quarto> quartos = listar();
	    quartos.add(quarto);

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("quarto.txt"))) {
	        for (Quarto q : quartos) {
	            writer.write(q.getCodigo() + "," + q.getCategoria() + "," + q.getStatus());
	            writer.newLine();
	        }
	        return true;
	    } catch (IOException e) {
	        System.err.println("Erro ao cadastrar: " + e.getMessage());
	        return false;
	    }
	}
    
	 public boolean editar(Quarto quarto) {
	        List<Quarto> quartos = listar();
	        boolean encontrado = false;
	        for (int i = 0; i < quartos.size(); i++) {
	            if (quartos.get(i).getCodigo() == quarto.getCodigo()) {
	                quartos.set(i, quarto);
	                encontrado = true;
	                break;
	            }
	        }
	        if (!encontrado) {
	            return false;
	        }

	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("quarto.txt"))) {
	            for (Quarto q : quartos) {
	                writer.write(q.getCodigo() + "," + q.getCategoria() + "," + q.getStatus());
	                writer.newLine();
	            }
	            return true;
	        } catch (IOException e) {
	            System.err.println("Erro ao editar: " + e.getMessage());
	            return false;
	        }
	    }

    public Quarto consultar(int codigo) {
        List<Quarto> quartos = listar();
        for (Quarto quarto : quartos) {
            if (quarto.getCodigo() == codigo) {
                return quarto;
            }
        }
        return null;
    }

    public List<Quarto> listar() {
        List<Quarto> quartos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("quarto.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Quarto quarto = new Quarto(codigo, line, line);
                    quarto.setCodigo(Integer.parseInt(parts[0]));
                    quarto.setCategoria(categoria, parts[1]);
                    quarto.setStatus(parts[2]);
                    quartos.add(quarto);
                } else {
                    System.err.println("Linha inválida: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao listar: " + e.getMessage());
        }
        return quartos;
    }

    

	private boolean salvarQuartos(List<Quarto> quartos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("quartos.dat"))) {
            oos.writeObject(quartos);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
