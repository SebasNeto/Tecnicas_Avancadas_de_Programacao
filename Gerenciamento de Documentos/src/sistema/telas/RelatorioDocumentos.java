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
 * A classe RelatorioCargos representa a tela de exibição do relatório de documentos por tipo.
 */
public class RelatorioDocumentos extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
    JLabel labelTitulo, labelDescricao;
    
    /**
     * Cria uma nova instância da classe RelatorioDocumentos.
     */
    
    
    public RelatorioDocumentos() {
        criarComponentes();
        criarEventos();
        Navegador.habilitarMenu();
    }
    
    private void criarComponentes() {
        setLayout(null);
        labelTitulo = new JLabel("Relatórios", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));
        labelDescricao = new JLabel("Esse gráfico representa a quantidade de documentos por tipo", JLabel.CENTER);
        
        DefaultPieDataset dadosGrafico = this.criarDadosGrafico();
        
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
            String query = "SELECT tipo, count(*) AS quantidade FROM documentos GROUP BY tipo ORDER BY tipo ASC";
            resultados = instrucaoSQL.executeQuery(query);
            
            while (resultados.next()) {
                dados.setValue(resultados.getString("tipo"), resultados.getInt("quantidade"));
            }
            
            return dados;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao criar o relatório.\n\n" + ex.getMessage());
            Navegador.inicio();
        }
        
        return null;
    }
    
    private void criarEventos() {

    }
}
