package sistema.telas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import sistema.navegacao.BancoDeDados;
import sistema.navegacao.Navegador;


/**
 * A classe RelatorioCargos representa a tela de exibição do relatório de documentos por interessado.
 */ 

public class RelatorioDocumentosInteressado extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
    private JLabel labelTitulo, labelDescricao;
    
    /**
     * Cria uma nova instância da classe RelatorioDocumentosInteressado.
     */
    

    public RelatorioDocumentosInteressado() {
        criarComponentes();
        Navegador.habilitarMenu();
    }
    
    private String obterNomeInteressado(int idInteressado, Connection conexao) throws SQLException {
        Statement instrucaoSQL = conexao.createStatement();
        String query = "SELECT nome, sobrenome FROM funcionarios WHERE id = " + idInteressado;
        ResultSet resultado = instrucaoSQL.executeQuery(query);
        
        if (resultado.next()) {
            String nome = resultado.getString("nome");
            String sobrenome = resultado.getString("sobrenome");
            System.out.println(nome + " " + sobrenome);
            return nome + " " + sobrenome;
        }
        
        return "Interessado Desconhecido";
    }


    private void criarComponentes() {
        setLayout(null);
        labelTitulo = new JLabel("Relatórios", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));
        labelDescricao = new JLabel("Esse gráfico representa a quantidade de documentos por interessado", JLabel.CENTER);

        DefaultPieDataset dadosGrafico = criarDadosGrafico();

        JFreeChart someChart = ChartFactory.createPieChart3D("", dadosGrafico, false, true, false);
        PiePlot plot = (PiePlot) someChart.getPlot();
        plot.setLabelBackgroundPaint(Color.WHITE);
        plot.setBackgroundPaint(null);
        plot.setOutlinePaint(null);
        someChart.setBackgroundPaint(null);

        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0}: {1} ({2})");

        plot.setLabelGenerator(gen);

        ChartPanel chartPanel = new ChartPanel(someChart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(660, 340);
            }
        };

        labelTitulo.setBounds(20, 20, 660, 40);
        labelDescricao.setBounds(20, 50, 660, 40);
        chartPanel.setBounds(20, 100, 660, 340);

        add(labelTitulo);
        add(labelDescricao);
        add(chartPanel);
        setVisible(true);
    }

    private DefaultPieDataset criarDadosGrafico() {
        DefaultPieDataset dados = new DefaultPieDataset();
        
        Connection conexao;
        Statement instrucaoSQL;
        ResultSet resultados;
        
        try {
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT f.nome, d.tipo, COUNT(*) AS quantidade FROM documentos d INNER JOIN funcionarios f ON d.interessado_id = f.id GROUP BY f.nome, d.tipo ORDER BY quantidade DESC";

            resultados = instrucaoSQL.executeQuery(query);
           
            
            while (resultados.next()) {
            	String nomeInteressado = resultados.getString("nome");
                String tipoDocumento = resultados.getString("tipo");
                dados.setValue(nomeInteressado + " (" + tipoDocumento + ")", resultados.getInt("quantidade"));
            }
            
            return dados;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao criar o relatório.\n\n" + ex.getMessage());
            Navegador.inicio();
        }
        
        return null;
    }


}