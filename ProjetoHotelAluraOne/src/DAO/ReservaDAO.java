package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import Modelo.reservas;
import java.sql.Date;


public class ReservaDAO {
	
	
	private Connection con;
	
	public ReservaDAO(Connection con) {
		
		super();
		
		this.con = con;
	}
	
	
	public void salvar(reservas reservas) {
		try {
			
			String sql = "INSERT INTO reservas (dataEntrada, dataSaida, valor, formaPagamento)" + " VALUES (?, ?, ?, ?)";
			
			try (PreparedStatement pstm = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
				
				pstm.setObject(1, reservas.getDataEntrada());
				pstm.setObject(2, reservas.getDataSaida());
				pstm.setString(3, reservas.getValor());
				pstm.setString(4, reservas.getFormaPagamento());
				
				
				pstm.executeUpdate();
				
				
				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reservas.setId(rst.getInt(1));
					}
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Animal" + e.getMessage(), e);
		}
	}
	
	public List<reservas> mostrar(){
		
		List<reservas> reservas = new ArrayList<reservas>();
		try {
			String sql = "SELECT id, dataEntrada, dataSaia, valor, formaPagaemento FROM reservas";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				
				pstm.execute();
				
				transformarResultado(reservas, pstm);
			}
			
			return reservas;
			
		} catch (SQLException e) {
			
			throw new RuntimeException(); 
			
		}
		
	}
	
	
	
	public List<reservas> buscarId(String id) {
		
		List<reservas> reservas = new ArrayList<reservas>();
		try {
			String sql = "SELECT id, dataEntrada, dataSaia, valor, formaPagaemento FROM reservas WHERE id = ?";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				
				pstm.setString(1, id);
				
				pstm.execute();
				
				transformarResultado(reservas, pstm);
			}
			
			return reservas;
			
		} catch (SQLException e) {
			
			throw new RuntimeException(); 
			
		}
		
	}
	
	public void atualizar(Date dataEntrada, Date dataSaida, String valor, String formaPagamento, Integer id) {
		
		try(PreparedStatement stm = con.prepareStatement("UPDATE reservas SET"
				+ "dataEntrada=?, dataSaida=?, valor=?, formaPagamento=? WHERE id=?")) {
					
					stm.setObject(1, java.sql.Date.valueOf(dataEntrada));
					stm.setObject(2, java.sql.Date.valueOf(dataSaida));
					stm.setString(3, valor);
					stm.setString(4, formaPagamento);
					stm.setInt(5, id);
					
					stm.execute();
					
				}catch(SQLException e) {
					
					throw new RuntimeException("animal" + e.getMessage(), e);		
			
		}
	}
	
	
	
	public void deletar (Integer id) {
		
		try(
				Statement state = con.createStatement()){;
				
				state.execute("SET FOREIGN_KEY_CHECKS = 0 ");
				
				PrepareStatement stm = con.prepareStatement("DELETE FROM reservas WHERE id = ?");
			
			stm.setInt(1, id);
			stm.execute();
			
			state.execute("SET FOREIGN_KEY_CHECKS = 1 ");
			
		}catch(SQLException e) {
			
			throw new RuntimeException("animal" + e.getMessage(), e);
		}
	}
	
	
	
	private void transformarResultado(List<reservas> reservas, PreparedStatement pstm) throws SQLException {
		
		try(ResultSet rst = pstm.getResultSet()){
			
			while(rst.next()) {
				
				int id = rst.getInt("id");
				LocalDate dataEntrada = rst.getDate("data_Entrada").toLocalDate().plusDays(1);
				LocalDate dataSaida = rst.getDate("data_saida").toLocalDate().plusDays(1);
				
				String valor = rst.getString("valor");
				String formaPagamento = rst.getString("forma_de_Pagamento");
				
				reservas produto = new reservas (id, dataEntrada, dataSaida, valor, formaPagamento);
				reservas.add(produto);
			}
		}
	}


	public void guardar(reservas reserva) {
		// TODO Auto-generated method stub
		
	}
}
	
	

	
