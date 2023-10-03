package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Factory.ConexionBase;

public class usuarios {
	
	private String nome;
	private String senha; 
	
	public usuarios(String nome, String senha) {
		
		this.nome = nome;
		this.senha = senha;
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public static boolean validarUsuario(String nome, String senha) {
		
		ConexionBase con = new ConexionBase();
		
		
		Connection cone = null;
		PreparedStatement state = null;
		ResultSet result = null;
		
		try {
			cone = con.conectarBase();
			state = cone.prepareStatement("SELECT * FROM usuarios WHERE nome? AND senha = ? ");
			state.setString(1, nome);
			state.setString(2, senha);
			result = state.executeQuery();
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if(result !=null)
					result.close();
				if(state !=null)
					state.close();
				if(cone !=null)
					cone.close();
			}catch (SQLException e2) {
				e2.printStackTrace();
				
			}
			}
		}
		
		
	}
	
