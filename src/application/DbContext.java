package application;

import java.sql.*;
import javax.swing.JOptionPane;

public class DbContext {
	public Connection _dbCon;
	private String _dbName = "lustrashoesdb";
	private String _dbUserName = "root";
	private String _dbPassword = "";

	public Connection ConnectionOpen() {
		String urlString = "jdbc:mysql://localhost:3306/" + _dbName;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			_dbCon = DriverManager.getConnection(urlString, _dbUserName, _dbPassword);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			// TODO: handle exception
		}
		return _dbCon;
	}

	public void CloseConnection() {
		try {
			_dbCon.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			// TODO: handle exception
		}
	}

}
