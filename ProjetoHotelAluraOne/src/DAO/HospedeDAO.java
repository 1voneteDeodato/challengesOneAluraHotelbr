package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Modelo.hospedes;
import Modelo.reservas;

public class HospedeDAO {
	
	
private Connection con;
	
	public HospedeDAO(Connection con) {
		
		super();
		this.con = con;
	}

	public void guardar(hospedes hospedes) {
		
		try {
			
			String sql = "INSERT INTO hospedes (nome, sobrenome, dataNasciemnto, nacionalidade"
					+ ", telefone, IdReserva) VALUES (?, ?, ?, ?, ?, ?)";
			
			
			try (PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				
				pstm.setString(1, hospedes.getNome());
				pstm.setString(2, hospedes.getSobrenome());
				pstm.setObject(3, hospedes.getDataNasciemnto());
				pstm.setString(4, hospedes.getNacionalidade());
				pstm.setString(5, hospedes.getTelefone());
				pstm.setInt(6, hospedes.getIdReserva());
				
				pstm.execute();
				
				try (ResultSet rst = pstm.getGeneratedKeys()) {
					
					while (rst.next()) {
						
						hospedes.setId(rst.getInt(1));
					}
					
				}
			}
			
		} catch (SQLException e) {
			
			throw new RuntimeException("animal" + e.getMessage(), e);
		}
	}
	
	
public List<hospedes> mostrar(){
		
		List<hospedes> hospedes = new ArrayList<hospedes>();
		try {
			String sql = "SELECT id, nome, sobrenome, dataNasciemnto, nacionalidade,"
					+ "telefone, IdReserva FROM hospedes";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				
				pstm.execute();
				
				transformarResultado(hospedes, pstm);
				
			}
			
			return hospedes;
			
		} catch (SQLException e) {
			
			throw new RuntimeException(); 
			
		}
		
	}

public List<hospedes> buscarid(String id){
	
	List<hospedes> hospedes = new ArrayList<hospedes>();
	try {
		String sql = "SELECT id, nome, sobrenome, dataNasciemnto, nacionalidade,"
				+ "telefone, IdReserva FROM hospedes WHERE id=?";
		
		try(PreparedStatement pstm = con.prepareStatement(sql)){
			
			pstm.setString(1, id);		
			pstm.execute();
			
			transformarResultado(hospedes, pstm);
			
		}
		
		return hospedes;
		
	} catch (SQLException e) {
		
		throw new RuntimeException(); 
		
	}
	
}


	public void atualizarH(String nome, String sobrenome, LocalDate dataNasciemnto, String nacionalidade, String telefone, 
			Integer idReserva, Integer id) {
		
				try(PreparedStatement stm = con.prepareStatement(""
						+ "UPDATE hospedes SET nome = ?, sobrenome = ?, dataNasciemnto = ? nacionalidade = ?, telefone = ?,"
						+ "idReserva = ?, id = ?")){
					
					stm.setString(1, nome);
					stm.setString(2, sobrenome);
					stm.setObject(3, dataNasciemnto);
					stm.setString(4, nacionalidade);
					stm.setString(5, telefone);
					stm.setInt(6, idReserva);
					stm.setInt(7, id);
					
					stm.execute();
					
				}catch(SQLException e) {
					
					throw new RuntimeException(e);
					
				}
	}
	
	
public void eliminar(Integer id) {
	
	try(PreparedStatement stm = con.prepareStatement("DELETE FROM hospedes WHERE id=? ")){
		
		stm.setInt(1, id);
		
		stm.execute();
		
	}catch(SQLException e) {
		
		throw new RuntimeException(e);
		
	}
	
}

	
private void transformarResultado (List<hospedes> hospedes, PreparedStatement pstm) throws SQLException {
	
	try(ResultSet rst = pstm.executeQuery()){
		
		while(rst.next()) {
			
			int id = rst.getInt("id");
			
			String nome = rst.getString("nome");
			String sobrenome = rst.getString("sobrenome");
			LocalDate dataNasciemnto = rst.getDate("data_Nasciemnto").toLocalDate().plusDays(1);
			String nacionalidade = rst.getString("nacionalidade");
			String telefone = rst.getString("telefone");
			int idReserva = rst.getInt("idReserva");
			
			
			hospedes hosped = new hospedes (id, nome, sobrenome, dataNasciemnto, nacionalidade, telefone, idReserva);
			hospedes.add(hosped);
		
		}
	}
}

	
	public List<hospedes> listarHospedes() {
		List<hospedes> hospedes = new ArrayList<>();
		
		try {
			String sql = "SELECT id, nome, sobrenome, dataNasciemnto, nacionalidade, telefone, idReserva FROM hospedes";
			
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
				transformarResultSetEmHospede(hospedes, pstm);
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<hospedes> buscarId(String id) {
		List<hospedes> hospedes = new ArrayList<hospedes>();
		try {

			String sql = "SELECT id, nome, sobrenome, dataNasciemnto, nacionalidade, telefone, idReserva FROM hospedes WHERE id = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();

				transformarResultSetEmHospede(hospedes, pstm);
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void atualizar(String nome, String sobrenome, Date dataNasciemnto, String nacionalidade, String telefone, Integer idReserva, Integer id) {
		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE hospedes SET nome = ?, sobrenome = ?, dataNasciemnto = ?, nacionalidade = ?, telefone = ?, idReserva = ? WHERE id = ?")) {
			stm.setString(1, nome);
			stm.setString(2, sobrenome);
			stm.setDate(3, dataNasciemnto);
			stm.setString(4, nacionalidade);
			stm.setString(5, telefone);
			stm.setInt(6, idReserva);
			stm.setInt(7, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM hospedes WHERE id = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void transformarResultSetEmHospede(List<hospedes> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				hospedes hospedes = new Hospedes(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5), rst.getString(6), rst.getInt(7));
				reservas.add(hospedes);
			}
		}				
	}	
}


