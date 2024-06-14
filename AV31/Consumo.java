package AV31;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Consumo implements Serializable {
    private Item item;
    private Reserva reserva;
    private int quantidadeSolicitada;
    private Date dataConsumo;

    // Getters and Setters...

    public boolean cadastrar(Consumo consumo) {
        List<Consumo> consumos = listar();
        consumos.add(consumo);
        return salvarConsumos(consumos);
    }

    public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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

	public Date getDataConsumo() {
		return dataConsumo;
	}

	public void setDataConsumo(Date dataConsumo) {
		this.dataConsumo = dataConsumo;
	}

	public boolean editar(Consumo consumo) {
        List<Consumo> consumos = listar();
        for (int i = 0; i < consumos.size(); i++) {
            if (consumos.get(i).getReserva().getCodigo() == consumo.getReserva().getCodigo() &&
                consumos.get(i).getItem().getCodigo() == consumo.getItem().getCodigo()) {
                consumos.set(i, consumo);
                return salvarConsumos(consumos);
            }
        }
        return false;
    }

    public Consumo consultar(int codigoReserva, int codigoItem) {
        List<Consumo> consumos = listar();
        for (Consumo consumo : consumos) {
            if (consumo.getReserva().getCodigo() == codigoReserva &&
                consumo.getItem().getCodigo() == codigoItem) {
                return consumo;
            }
        }
        return null;
    }

    public List<Consumo> listar() {
        List<Consumo> consumos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("consumos.dat"))) {
            consumos = (List<Consumo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exception
        }
        return consumos;
    }

    private boolean salvarConsumos(List<Consumo> consumos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("consumos.dat"))) {
            oos.writeObject(consumos);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
