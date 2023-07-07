package entidade;

import java.util.Date;


/**
 * Classe que representa um documento.
 */

public class Documentos {
    private int id;
    private String referencia;
    private Date dataArquivamento;
    private Funcionario interessado;
    private String tipo;
    private String descricao;
    private String armazenamento;
    private String localizacao;

    // getters
    
    /**
     * Obtém o ID do documento.
     * 
     * @return O ID do documento.
     */
    
    public int getId() {
        return id;
    }
    
    /**
     * Obtém a referência do documento.
     * 
     * @return A referência do documento.
     */

    public String getReferencia() {
        return referencia;
    }
    
    /**
     * Obtém a data de arquivamento do documento.
     * 
     * @return A data de arquivamento do documento.
     */

    public Date getDataArquivamento() {
        return dataArquivamento;
    }
    
    /**
     * Obtém o funcionário interessado no documento.
     * 
     * @return O funcionário interessado no documento.
     */

    public Funcionario getInteressado() {
        return interessado;
    }
    
    /**
     * Obtém o tipo do documento.
     * 
     * @return O tipo do documento.
     */

    public String getTipo() {
        return tipo;
    }
    
    /**
     * Obtém a descrição do documento.
     * 
     * @return A descrição do documento.
     */

    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Obtém o armazenamento do documento.
     * 
     * @return O armazenamento do documento.
     */

    public String getArmazenamento() {
        return armazenamento;
    }
    
    /**
     * Obtém a localização do documento.
     * 
     * @return A localização do documento.
     */

    public String getLocalizacao() {
        return localizacao;
    }

    // setters
    
    /**
     * Define o ID do documento.
     * 
     * @param id O ID do documento.
     */
    
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Define a referência do documento.
     * 
     * @param referencia A referência do documento.
     */

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
    /**
     * Define a data de arquivamento do documento.
     * 
     * @param dataArquivamento A data de arquivamento do documento.
     */

    public void setDataArquivamento(Date dataArquivamento) {
        this.dataArquivamento = dataArquivamento;
    }
    
    /**
     * Define o funcionário interessado no documento.
     * 
     * @param interessado O funcionário interessado no documento.
     */

    public void setInteressado(Funcionario interessado) {
        this.interessado = interessado;
    }
    
    /**
     * Define o tipo do documento.
     * 
     * @param tipo O tipo do documento.
     */

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Define a descrição do documento.
     * 
     * @param descricao A descrição do documento.
     */

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    /**
     * Define o armazenamento do documento.
     * 
     * @param armazenamento O armazenamento do documento.
     */

    public void setArmazenamento(String armazenamento) {
        this.armazenamento = armazenamento;
    }
    
    /**
     * Define a localização do documento.
     * 
     * @param localizacao A localização do documento.
     */

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    
    /**
     * Retorna uma representação em String do documento.
     * 
     * @return A representação em String do documento.
     */
    @Override

    public String toString() {
        String str = "";
        if (referencia != null) {
            str += referencia + "";
        }
        if (descricao != null) {
            str += descricao;
        }
        return str;
    }

}
