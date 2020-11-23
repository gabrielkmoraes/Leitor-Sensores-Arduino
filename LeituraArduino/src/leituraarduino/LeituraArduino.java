package leituraarduino;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import com.fazecast.jSerialComm.SerialPort;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class LeituraArduino {

    static SerialPort portaEscolhida;
    static int x = 0;
    static int y = 0;

    public static void main(String[] args) throws IOException {

        JFrame JanelaGrafico = new JFrame();
        JFrame JanelaInfo = new JFrame();
        JPanel Info = new JPanel();
        JLabel lb1 = new JLabel("Sensor utilizado: DHT11");
        JLabel lbT = new JLabel("Tensao de Trabalho: 3.3 volts a 5 v (5.5v maximo)");
        JLabel lbC = new JLabel("Corrente: 200uA a 500mA, em stand by de 100uA a 150 uA");
        JLabel lbTr = new JLabel("Tempo de resposta: 2s");
        JLabel lbDim = new JLabel("Dimensões: 23 x 12 x 5mm (incluindo terminais):");
        JLabel lbF = new JLabel("Faixa de medição de temperatura: 0º a 50ºC");
        JLabel lbP = new JLabel("Precisão de umidade de medição: ± 5,0% UR");
        JLabel lbP2 = new JLabel("Precisão de medição de temperatura: ± 2.0 ºC");
        JLabel lbL = new JLabel("Local de Coleta: Universidade São Francisco");
        JButton btVoltar = new JButton("Voltar");


        Date data = GregorianCalendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat();
        JLabel lbData = new JLabel("Data da coleta: " + format.format(data));
               
        Info.setLayout(new GridLayout(12, 1));

        Info.add(lb1);
        Info.add(lbT);
        Info.add(lbC);
        Info.add(lbTr);
        Info.add(lbDim);
        Info.add(lbF);
        Info.add(lbP);
        Info.add(lbP2);
        Info.add(lbL);
        Info.add(lbData);
        Info.add(btVoltar);

        JanelaInfo.setTitle("Informações do Sensor");
        JanelaInfo.setLayout(new BorderLayout());
        JanelaInfo.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JanelaInfo.setContentPane(Info);
        JanelaInfo.setLocationRelativeTo(null); //inicia a janela no centro em relação ao o oque (no caso a tela principal)
        JanelaInfo.pack();

        JanelaGrafico.setTitle("Leitura do Sensor - Projeto");
        JanelaGrafico.setSize(600, 400);
        JanelaGrafico.setLayout(new BorderLayout());
        JanelaGrafico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComboBox<String> cbListaPorta = new JComboBox<>();
        JButton btConectar = new JButton("Conectar");
        JButton btCadastrar = new JButton("Cadastrar");
        JButton btInfo = new JButton("Info");
        JPanel pnFundo = new JPanel();
        pnFundo.add(cbListaPorta);
        pnFundo.add(btConectar);
        pnFundo.add(btCadastrar);
        pnFundo.add(btInfo);
        JanelaGrafico.add(pnFundo, BorderLayout.NORTH);

        btCadastrar.addActionListener((ActionEvent e) -> {
            Janela2 j2 = new Janela2();
        });
        
        btInfo.addActionListener((ActionEvent e) -> {
            JanelaInfo.setVisible(true);
        });
        btVoltar.addActionListener((ActionEvent e) -> {
            JanelaInfo.setVisible(false);
        });

        SerialPort[] portNames = SerialPort.getCommPorts();
        for (int i = 0; i < portNames.length; i++) {
            cbListaPorta.addItem(portNames[i].getSystemPortName());
        }
        XYSeries series = new XYSeries("Temperatura");
        XYSeries series2 = new XYSeries("Umidade"); //^^^^^^^^^
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Leitura do sensor", "Segundos", "Valor do sensor", dataset);
        JanelaGrafico.add(new ChartPanel(chart), BorderLayout.CENTER);

        dataset.addSeries(series2); //*****
        
        ArrayList<Dados> array=new ArrayList<>();

        btConectar.addActionListener((ActionEvent arg0) -> {

            if (btConectar.getText().equals("Conectar")) {
                portaEscolhida = SerialPort.getCommPort(cbListaPorta.getSelectedItem().toString());
                portaEscolhida.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
                if (portaEscolhida.openPort()) {
                    btConectar.setText("Disconectar");
                    cbListaPorta.setEnabled(false);
                }
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try (Scanner scanner = new Scanner(portaEscolhida.getInputStream())) {

                            while (scanner.hasNextLine()) {
                                try {
                                    String line = scanner.nextLine();               //Le a primeira linha
                                    float var = Float.parseFloat(line);             //Converte o dado da primeira linha em float
                                    String line2 = scanner.nextLine();              //Le a segunda linha
                                    float var2 = Float.parseFloat(line2);           //Converte o dado da primeira linha em float
                                    series.add(x++, var);                           //Adiciona o dado na linha
                                    series2.add(x++, var2); 
                                    JanelaGrafico.repaint();
                                    Dados d = new Dados();
                                    d.setTemp(var);
                                    d.setHumi(var2);
                                    array.add(d);
                                } catch (Exception e) {
                                }
                            }
                            
                        }
                        ArquivoTexto at=new ArquivoTexto();
                        try {
                            at.gravar(array);
                        } catch (IOException ex) {
                            Logger.getLogger(LeituraArduino.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
                thread.start();
            } else {
                portaEscolhida.closePort();
                cbListaPorta.setEnabled(true);
                btConectar.setText("Conectar");
                series.clear();

                x = 0;
            }
        });
        JanelaGrafico.setVisible(true);
        
    }
}
