package Controladores;
	
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import javax.sql.ConnectionEvent;
import DAO.ReservaDAO;
import Factory.ConexionBase;
import Modelo.reservas;


	public class ReservasControlador {
		
		private ReservaDAO reservaDAO;
		
		
		public ReservasControlador(){
			
			Connection con = new ConexionBase().conectarBase();
			this.reservaDAO = new ReservaDAO(con);
		}
		
		public void guardar(reservas reserva) {
			
			this.reservaDAO.guardar(reserva);
		}
		
	
		public List<reservas> Mostrar() {
			return this.reservaDAO.mostrar();
		}
		
		public List<reservas> buscar(String id) {
			return this.reservaDAO.buscarId(id);
		}
		
		public void atualizarReserva(Date dataEntrada, Date dataSaida, String valor, String formaPagamento, Integer id) {
			this.reservaDAO.atualizar(dataEntrada, dataSaida, valor, formaPagamento, id);
		}
			
		public void deletar(Integer id) {
			this.reservaDAO.deletar(id);
		}
}
