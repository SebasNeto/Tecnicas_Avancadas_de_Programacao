package sistema.navegacao;


/**
 * Classe que armazena as informações de conexão com o banco de dados.
 */

public class BancoDeDados {
	
    /**
     * String de conexão com o banco de dados.
     */
	
	public static String stringDeConexao = "jdbc:mysql://localhost:3306/meu_banco_de_dados?allowPublicKeyRetrieval=true&useSSL=false";
	
    /**
     * Usuário do banco de dados.
     */
	
	public static String usuario = "";
	
	 /**
     * Senha do banco de dados.
     */
	
	public static String senha = "";

}