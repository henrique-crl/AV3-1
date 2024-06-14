package AV31;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reserva implements Serializable {
    private int codigo;
    private Hospede hospede;
    private Quarto quarto;
    private Funcionario funcionarioReserva;
    private Funcionario funcionarioFechamento;
    private Date dataEntradaReserva;
    private Date dataSaidaReserva;
    private Date dataCheckin;
    private Date dataCheckout;
    private double valorReserva;
    private double valorPago;

    // Getters and Setters...

    public boolean cadastrar(Reserva reserva) {
        List<Reserva> reservas = listar();
        reservas.add(reserva);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reserva.txt"))) {
            for (Reserva r : reservas) {
                writer.write(r.getCodigo() + "," + r.getValorReserva());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }


    public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public Funcionario getFuncionarioReserva() {
		return funcionarioReserva;
	}

	public void setFuncionarioReserva(Funcionario funcionarioReserva) {
		this.funcionarioReserva = funcionarioReserva;
	}

	public Funcionario getFuncionarioFechamento() {
		return funcionarioFechamento;
	}

	public void setFuncionarioFechamento(Funcionario funcionarioFechamento) {
		this.funcionarioFechamento = funcionarioFechamento;
	}

	public Date getDataEntradaReserva() {
		return dataEntradaReserva;
	}

	public void setDataEntradaReserva(Date dataEntradaReserva) {
		this.dataEntradaReserva = dataEntradaReserva;
	}

	public Date getDataSaidaReserva() {
		return dataSaidaReserva;
	}

	public void setDataSaidaReserva(Date dataSaidaReserva) {
		this.dataSaidaReserva = dataSaidaReserva;
	}

	public Date getDataCheckin() {
		return dataCheckin;
	}

	public void setDataCheckin(Date dataCheckin) {
		this.dataCheckin = dataCheckin;
	}

	public Date getDataCheckout() {
		return dataCheckout;
	}

	public void setDataCheckout(Date dataCheckout) {
		this.dataCheckout = dataCheckout;
	}

	public double getValorReserva() {
		return valorReserva;
	}

	public void setValorReserva(double valorReserva) {
		this.valorReserva = valorReserva;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	 public boolean editar(Reserva reserva) {
	        List<Reserva> reservas = listar();
	        for (int i = 0; i < reservas.size(); i++) {
	            if (reservas.get(i).getCodigo() == reserva.getCodigo()) {
	                reservas.set(i, reserva);
	                break;
	            }
	        }

	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reserva.txt"))) {
	            for (Reserva r : reservas) {
	                writer.write(r.getCodigo() + "," + r.getValorReserva());
	                writer.newLine();
	            }
	            return true;
	        } catch (IOException e) {
	            System.err.println("Erro ao editar: " + e.getMessage());
	            return false;
	        }
	    }

    public Reserva consultar(int codigo) {
        List<Reserva> reservas = listar();
        for (Reserva reserva : reservas) {
            if (reserva.getCodigo() == codigo) {
                return reserva;
            }
        }
        return null;
    }

    public List<Reserva> listar() {
        List<Reserva> reservas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("reserva.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int codigo = Integer.parseInt(parts[0]);
                double valorReserva = Double.parseDouble(parts[1]);
                Reserva reserva = new Reserva();
                reserva.setCodigo(codigo);
                reserva.setValorReserva(valorReserva);
                reservas.add(reserva);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro de leitura/escrita: " + e.getMessage());
        }
        return reservas;
    }

    private boolean salvarReservas(List<Reserva> reservas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reserva.txt"))) {
            for (Reserva reserva : reservas) {
                writer.write(reserva.getCodigo() + "," + reserva.getValorReserva());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar reservas: " + e.getMessage());
            return false;
        }
    }


    public void pagarReserva(int codigo) {
        List<Reserva> reservas = listar();
        for (Reserva reserva : reservas) {
            if (reserva.getCodigo() == codigo) {
                reserva.setValorPago(reserva.getValorReserva());
                salvarReservas(reservas);
                return;
            }
        }
    }
}
