package AV31;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsumoServico implements Serializable {
	private int codigo;
    private Servico servico;
    private Reserva reserva;
    private int quantidadeSolicitada;
    private Date dataServico;

    // Getters and Setters...

    public boolean cadastrar(ConsumoServico consumoServico) {
        List<ConsumoServico> consumoServicos = listar();
        consumoServicos.add(consumoServico);
        return salvarConsumoServicos(consumoServicos);
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public int getQuantidadeSolicitada() {
		return quantidadeSolicitada;
	}

	public void setQuantidadeSolicitada(int quantidadeSolicitada) {
		this.quantidadeSolicitada = quantidadeSolicitada;
	}

	public Date getDataServico() {
		return dataServico;
	}

	public void setDataServico(Date dataServico) {
		this.dataServico = dataServico;
	}

	public boolean editar(ConsumoServico consumoServico) {
        List<ConsumoServico> consumoServicos = listar();
        for (int i = 0; i < consumoServicos.size(); i++) {
            if (consumoServicos.get(i).getReserva().getCodigo() == consumoServico.getReserva().getCodigo() &&
                consumoServicos.get(i).getServico().getCodigo() == consumoServico.getServico().getCodigo()) {
                consumoServicos.set(i, consumoServico);
                return salvarConsumoServicos(consumoServicos);
            }
        }
        return false;
    }
	

    public ConsumoServico consultar(int codigoReserva, int codigoServico) {
        List<ConsumoServico> consumoServicos = listar();
        for (ConsumoServico consumoServico : consumoServicos) {
            if (consumoServico.getReserva().getCodigo() == codigoReserva &&
                consumoServico.getServico().getCodigo() == codigoServico) {
                return consumoServico;
            }
        }
        return null;
    }
    
    public ConsumoServico consultar(int codigo) {
        List<ConsumoServico> consumoServicos = listar();
        for (ConsumoServico consumoServico : consumoServicos) {
            if (consumoServico.getCodigo() == codigo) {
                return consumoServico;
            }
        }
        return null;
    }

    public List<ConsumoServico> listar() {
        List<ConsumoServico> consumoServicos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("consumoServicos.dat"))) {
            consumoServicos = (List<ConsumoServico>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exception
        }
        return consumoServicos;
    }

    private boolean salvarConsumoServicos(List<ConsumoServico> consumoServicos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("consumoServicos.dat"))) {
            oos.writeObject(consumoServicos);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
