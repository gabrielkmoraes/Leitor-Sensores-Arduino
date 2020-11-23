
package leituraarduino;
public class Sensor {
    int codigo;
    String descricao;
    String precisao;
    String local;
    String data;
    

    public Sensor(int codigo, String descricao, String precisao, String local, String data) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.precisao = precisao;
        this.local = local;
        this.data = data;
        
    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrecisao() {
        return precisao;
    }

    public void setPrecisao(String precisao) {
        this.precisao = precisao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
}
