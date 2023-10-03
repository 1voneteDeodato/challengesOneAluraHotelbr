package Factory;


import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;






public class ConexionBase {
	
	public DataSource dataSou;
	
	
	public ConexionBase() {
		
		ComboPooledDataSource comboPool = new ComboPooledDataSource();
		
		comboPool.set.JdbcUrl("Direção da Base de Dados");
		
		comboPool.setUser("root");
		
		comboPool.setPassword("root");
		
		this.dataSou = comboPool;
		
	}
	
	
	public Connection conectarBase() {
		
		try {
			
			return this.dataSou.getConnection();
			
		}catch (SQLException e) {
			
			System.out.println("erro");
			
			throw new RuntimeException(e);
			
		}
		
		
	}
}
