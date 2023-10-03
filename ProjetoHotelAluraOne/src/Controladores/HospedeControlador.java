package Controladores;


	import java.sql.Connection;
	import DAO.HospedeDAO;
import Factory.ConexionBase;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
	import Modelo.hospedes;
	


	public class HospedeControlador {
		
	private HospedeDAO hospedeDAO;
	
	
	public HospedeControlador() {
			
			Connection con = new ConexionBase().conectarBase();
			this.hospedeDAO = new HospedeDAO(con);
		}
		
		public void guardar(hospedes hospede) {
			this.hospedeDAO.guardar(hospede);
		}
		
		
		public List<hospedes> mostrarHosped() {
			
			return this.hospedeDAO.mostrar();
			
		}
		
	public List<hospedes> buscarHosped(String id) {
			
			return this.hospedeDAO.buscarid(id);
		
	}
	
	public void atualizarH(String nome, String sobrenome, LocalDate dataNasciemnto, String nacionalidade, String telefone, 
			Integer idReserva, Integer id) {
	
			this.hospedeDAO.atualizarH(nome, sobrenome, dataNasciemnto, nacionalidade, telefone, idReserva, id);
	}
	
	public void eliminar(Integer idReserva) {
		
		this.hospedeDAO.eliminar(idReserva);
		
	}
	
	
	}