package Modelo;


import java.sql.Date;
import java.time.LocalDate;



	public class reservas {
		
		private Integer id;
		private LocalDate dataEntrada;
		private LocalDate dataSaida;
		private String valor;
		private String formaPagamento;
		
	
	
	public reservas(LocalDate dataEntrada, LocalDate dataSaida, String valor, String formaPagamento) {
		
		super();
		
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
	}
	
	public reservas(Integer id, LocalDate dataEntrada, LocalDate dataSaida, String valor, String formaPagamento) {
		
		super();
		
		this.id = id;		
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
	}


	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public LocalDate getDataEntrada() {
		return dataEntrada;
	}



	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}



	public LocalDate getDataSaida() {
		return dataSaida;
	}



	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}



	public String getValor() {
		return valor;
	}



	public void setValor(String valor) {
		this.valor = valor;
	}



	public String getFormaPagamento() {
		return formaPagamento;
	}



	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	
	}
		
		
		
		