package leituraarduino;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArquivoTexto {
    private String arquivo="dados.txt";
    
    public void gravar(ArrayList<Dados> array)
            throws IOException{
        BufferedWriter bw = new BufferedWriter(
            new FileWriter(arquivo));
        for(int i=0;i<array.size();i++){
            String linha=
               array.get(i).getTemp()+";"+
               array.get(i).getHumi()+"\n";
            bw.append(linha);
        }
        bw.close();
    }
}
