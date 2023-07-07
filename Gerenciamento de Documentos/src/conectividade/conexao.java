package conectividade;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Classe que demonstra uma conexão com um banco de dados MySQL.
 */


public class conexao {
	public static void main(String[] args){
		String servidor = "jdbc:mysql://localhost:3306/meu_banco_de_dados?allowPublicKeyRetrieval=true&useSSL=false";
		

		String usuario = "root";
		String senha = "sebastiaoufam2021";
		
		try(Connection conexao = DriverManager.getConnection(servidor, usuario, senha);
			Statement instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)){
		
			System.out.println("deu certo");
			
		} catch(SQLException erro){
			System.out.println(erro.getMessage());
			erro.printStackTrace();
			
		}		
	}
}
