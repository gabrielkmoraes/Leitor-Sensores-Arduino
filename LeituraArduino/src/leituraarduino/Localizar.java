package leituraarduino;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Localizar extends JFrame{
    JTable tbTabela= new JTable();
    JPanel pnFundo= new JPanel();
    
    public Localizar(Janela2 principal){
        this.setTitle("Lista de sensores");
        tbTabela = new JTable(0, 5);
        DefaultTableModel tableModel = (DefaultTableModel) tbTabela.getModel();
        for (int i = 0; i < principal.sensors.size(); i++){
            int Id = principal.sensors.get(i).getCodigo();
            String Descricao = principal.sensors.get(i).getDescricao();
            String Precisao = principal.sensors.get(i).getPrecisao();
            String Local = principal.sensors.get(i).getLocal();
            String Data = principal.sensors.get(i).getData();
            Object[] dados = {Id, Descricao,Precisao,Local,Data};
            tableModel.addRow(dados);
        }
        pnFundo.add(tbTabela);
        this.setContentPane(pnFundo);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        tbTabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                            principal.tfDesc.setText(tbTabela.getValueAt(tbTabela.getSelectedRow(),0).toString());
                            principal.tfPres.setText(tbTabela.getValueAt(tbTabela.getSelectedRow(),1).toString());   
                            principal.tfLocal.setText(tbTabela.getValueAt(tbTabela.getSelectedRow(),1).toString());   
                            principal.tfData.setText(tbTabela.getValueAt(tbTabela.getSelectedRow(),1).toString());   
			}
		});
    }   
}