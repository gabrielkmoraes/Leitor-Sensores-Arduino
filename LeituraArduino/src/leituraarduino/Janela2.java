package leituraarduino;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Janela2 extends JFrame{
    ArrayList<Sensor> sensors = new ArrayList<>();
    int op=0;
    JPanel pnFundo=new JPanel();
    JPanel pnBotoes=new JPanel();
    JPanel pnDados=new JPanel();
    JPanel pnDadosInterno=new JPanel();
    JPanel pnCabecalho=new JPanel();
    JLabel lbCadastro=new JLabel("Cadastro de Sensor");
    JLabel lbId=new JLabel("Código:");
    JTextField tfId= new JTextField(30);
    JLabel lbDesc=new JLabel("Descricao:");
    JTextField tfDesc= new JTextField(30);
    JLabel lbPres=new JLabel("Precisao:");
    JTextField tfPres= new JTextField();
    JButton btIncluir=new JButton("Incluir");
    JButton btAlterar=new JButton("Alterar");
    JButton btExcluir=new JButton("Excluir");
    JButton btListar=new JButton("Listar");
    JButton btArquivo=new JButton("Gravar Dados");
    JButton btSalvar=new JButton("Salvar");
    JButton btCancelar=new JButton("Cancelar");
    JButton btFechar=new JButton("Fechar");
    JLabel lbLocal=new JLabel("Local:");
    JTextField tfLocal= new JTextField();
    JLabel lbData=new JLabel("Data:");
    JTextField tfData= new JTextField();
    
    public Janela2(){
        pnFundo.setBorder(new EmptyBorder(10,10,10,10)); 
        pnFundo.setLayout(new BorderLayout());
        pnFundo.add(pnCabecalho,BorderLayout.NORTH);
        pnFundo.add(pnDados,BorderLayout.WEST);
        pnFundo.add(pnBotoes,BorderLayout.EAST);
        lbCadastro.setFont(new Font("Arial", Font.BOLD,24));
        pnCabecalho.add(lbCadastro);
        pnDados.setLayout(new GridLayout(6,1));
        pnDados.add(lbId);
        pnDados.add(tfId);
        pnDados.add(lbDesc);
        pnDados.add(tfDesc);
        pnDados.add(lbPres);
        pnDados.add(tfPres);
        pnDados.add(lbLocal);
        pnDados.add(tfLocal);
        pnDados.add(lbData);
        pnDados.add(tfData);
        pnDadosInterno.setLayout(new GridLayout(2,3));
        pnDados.add(pnDadosInterno);
        pnBotoes.setBorder(new EmptyBorder(0,10,0,10));
        pnBotoes.setLayout(new GridLayout(7,1));
        pnBotoes.add(btIncluir);
        pnBotoes.add(btAlterar);
        pnBotoes.add(btExcluir);
        pnBotoes.add(btListar);
        pnBotoes.add(btSalvar);
        pnBotoes.add(btArquivo);
        pnBotoes.add(btCancelar);
        pnBotoes.add(btFechar);
        this.setTitle("Cadastro");
        this.setContentPane(pnFundo);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.limpar();
        this.habilitar();
        this.setVisible(true);
        btIncluir.addActionListener((ActionEvent e) -> {
            limpar();
            desabilitar();
            op=1;
        });  
        btAlterar.addActionListener((ActionEvent e) -> {
            if(tfId.getText().equals(""))
                JOptionPane.showMessageDialog(null,"Localize um sensor!");
            else{
                desabilitar();
                tfId.setEnabled(false);
                op=2;
            }
        });  
        btExcluir.addActionListener((ActionEvent e) -> {
            if(tfId.getText().equals(""))
                JOptionPane.showMessageDialog(null,"Localize um sensor!");
            else{
                for(int i=0;i<sensors.size();i++){
                    if(Integer.parseInt(tfId.getText())==sensors.get(i).getCodigo()){
                        sensors.remove(i);
                        JOptionPane.showMessageDialog(null,"Excluído com sucesso!");
                    }
                }
                limpar();
            }
        });  
        btSalvar.addActionListener((ActionEvent e) -> {
                Sensor s = new Sensor(
                            Integer.parseInt(tfId.getText()),
                            tfDesc.getText(),
                            tfPres.getText(),
                            tfLocal.getText(),
                            tfData.getText()
                );
                if(op==2){
                    for(int i=0;i<sensors.size();i++){
                        if(Integer.parseInt(tfId.getText())==sensors.get(i).getCodigo()){
                            sensors.set(i, s);
                            JOptionPane.showMessageDialog(null,"Alterado com sucesso!");
                            op=0;
                            habilitar();
                        }
                    }
                }else{
                    int inc=0;
                    for(int i=0;i<sensors.size();i++){
                        if(Integer.parseInt(tfId.getText())==sensors.get(i).getCodigo()){
                            JOptionPane.showMessageDialog(null,"Codigo já cadastrado!");
                            inc=1;
                            tfId.setFocusable(true);
                        }
                    }
                    if(inc==0){
                        sensors.add(s);
                        JOptionPane.showMessageDialog(null,"Incluído com sucesso!");
                        op=0;
                        habilitar();
                    }
                }
            
        });
        btArquivo.addActionListener((ActionEvent e) -> {
            ArrayList<Sensor> array2=new ArrayList<>();
            for(int i=0;i<sensors.size();i++){
                        Sensor s2 = new Sensor(
                                sensors.get(i).getCodigo(), 
                                sensors.get(i).getDescricao(), 
                                sensors.get(i).getPrecisao(), 
                                sensors.get(i).getLocal(), 
                                sensors.get(i).getData());
                        array2.add(s2);
                         JOptionPane.showMessageDialog(null,"Gerou o arquivo de texto com sucesso!");
                    }
            ArquivoTexto2 at=new ArquivoTexto2();
            try {

                            at.gravar2(sensors);
                        } catch (IOException ex) {
                        }
                        
        }); 
        btCancelar.addActionListener((ActionEvent e) -> {
            habilitar();
            limpar();
            op=0;
        }); 
        btListar.addActionListener((ActionEvent e) -> {
            if(sensors.isEmpty())
                JOptionPane.showMessageDialog(null,"Nenhum sensor cadastrado!");
            else{
                Localizar loc = new Localizar(this);
            }
        }); 
        btFechar.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }
    public final void habilitar(){
        btIncluir.setEnabled(true);
        btAlterar.setEnabled(true);
        btExcluir.setEnabled(true);
        btListar.setEnabled(true);
        btSalvar.setEnabled(false);
        btCancelar.setEnabled(false);
        btFechar.setEnabled(true);
        tfId.setEnabled(false);
        tfDesc.setEnabled(false);
        tfPres.setEnabled(false);
        tfLocal.setEnabled(false);
        tfData.setEnabled(false);
    }
    
    public final void desabilitar(){
        btIncluir.setEnabled(false);
        btAlterar.setEnabled(false);
        btExcluir.setEnabled(false);
        btListar.setEnabled(false);
        btSalvar.setEnabled(true);
        btCancelar.setEnabled(true);
        btFechar.setEnabled(false);
        tfId.setEnabled(true);
        tfDesc.setEnabled(true);
        tfPres.setEnabled(true);
        tfLocal.setEnabled(true);
        tfData.setEnabled(true);
    }
    
    public final void limpar(){
        tfId.setText("");
        tfDesc.setText("");
        tfPres.setText("");
        tfLocal.setText("");
        tfData.setText("");
    }
}
