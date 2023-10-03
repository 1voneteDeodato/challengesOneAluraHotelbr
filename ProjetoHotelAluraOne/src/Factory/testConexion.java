package Factory;


import java.sql.Connection;
import java.sql.SQLException;



public class testConexion {
	
	
	
	public static void main(String[] args) throws SQLException {
		
		ConexionBase con = new ConexionBase();
		
		Connection cone = con.conectarBase();
		
		
		System.out.println("Conectar o banco");
		
		cone.close();
		
		System.out.println("Encerrar o banco");

}
}