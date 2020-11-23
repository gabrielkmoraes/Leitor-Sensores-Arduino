package leituraarduino;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArquivoTexto2 {

    private String arquivo="sensores.txt";
    
    public void gravar2(ArrayList<Sensor> array)
            throws IOException{
        BufferedWriter bw = new BufferedWriter(
            new FileWriter(arquivo));
        String newLine = System.getProperty("line.separator");
        for(int i=0;i<array.size();i++){
            String linha=
               array.get(i).getCodigo()+";"+
               array.get(i).getDescricao()+";";
            bw.append(linha);
            String linha2=
               array.get(i).getPrecisao()+";";
            bw.append(linha2);
            String linha3=
               array.get(i).getLocal()+";";
            bw.append(linha3);
            String linha4=
               array.get(i).getData()+"\n" + newLine;
            bw.append(linha4);
        }
        bw.close();
    }
}
