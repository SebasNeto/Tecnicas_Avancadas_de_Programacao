package entidade;

/**
 * Classe que representa um funcionário.
 */
public class Funcionario {

    private int id;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String email;
    private int cargo;
    private double salario;

    /**
     * Obtém o ID do funcionário.
     *
     * @return O ID do funcionário.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do funcionário.
     *
     * @param id O ID do funcionário.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome do funcionário.
     *
     * @return O nome do funcionário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do funcionário.
     *
     * @param nome O nome do funcionário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o sobrenome do funcionário.
     *
     * @return O sobrenome do funcionário.
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     * Define o sobrenome do funcionário.
     *
     * @param sobrenome O sobrenome do funcionário.
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    /**
     * Obtém a data de nascimento do funcionário.
     *
     * @return A data de nascimento do funcionário.
     */
    public String getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Define a data de nascimento do funcionário.
     *
     * @param dataNascimento A data de nascimento do funcionário.
     */
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * Obtém o email do funcionário.
     *
     * @return O email do funcionário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do funcionário.
     *
     * @param email O email do funcionário.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o cargo do funcionário.
     *
     * @return O cargo do funcionário.
     */
    public int getCargo() {
        return cargo;
    }

    /**
     * Define o cargo do funcionário.
     *
     * @param cargo O cargo do funcionário.
     */
    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    /**
     * Obtém o salário do funcionário.
     *
     * @return O salário do funcionário.
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Define o salário do funcionário.
     *
     * @param salario O salário do funcionário.
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * Retorna uma representação em String do funcionário.
     *
     * @return A representação em String do funcionário.
     */
    @Override
    public String toString() {
        if (sobrenome != null) {
            return nome + " " + sobrenome;
        } else {
            return nome;
        }
    }

}

