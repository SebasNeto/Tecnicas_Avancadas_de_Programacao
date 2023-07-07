package entidade;

/**
 * Classe que representa um cargo.
 */

public class Cargo {
	 
   private int id; 
   
   private String nome;
   
   /**
    * Obtém o ID do cargo.
    * 
    * @return O ID do cargo.
    */

   public int getId() {
       return id;
   }
   
   /**
    * Define o ID do cargo.
    * 
    * @param id O ID do cargo.
    */
   
   public void setId(int id) {
       this.id = id;
   }
   
   /**
    * Obtém o nome do cargo.
    * 
    * @return O nome do cargo.
    */

   public String getNome() {
       return nome;
   }
   
   /**
    * Define o nome do cargo.
    * 
    * @param nome O nome do cargo.
    */

   public void setNome(String nome) {
       this.nome = nome;
   }
   
   /**
    * Retorna uma representação em String do cargo.
    * 
    * @return A representação em String do cargo.
    */
   
   @Override
   public String toString() {
       return this.nome;
   }    

}
