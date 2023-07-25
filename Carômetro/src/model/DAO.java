package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	
	private Connection con;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/dbcarametro";
	private String user = "root";
	private String password = "Teste1234.";
	
	public Connection conectar() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			return con;
		} catch (Exception e) {
			System.out.print(e);
			return null;
		}
	}

}
