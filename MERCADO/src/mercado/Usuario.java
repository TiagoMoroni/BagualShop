
package mercado;

import java.util.ArrayList;
import java.util.HashMap;


public class Usuario {
    
    private String nome;
    private String senha;
    private HashMap<String, ArrayList<String>> horaativ;
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public HashMap<String, ArrayList<String>> getHoraativ() {
        return horaativ;
    }

    public void setHoraativ(HashMap<String, ArrayList<String>> horaativ) {
        this.horaativ = horaativ;
    }

    public Usuario(String nome, String senha, HashMap<String, ArrayList<String>> horaativ) {
        this.nome = nome;
        this.senha = senha;
        this.horaativ = horaativ;
    }

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }
    
    public void addAtividade(String hora, String atividade){
        if (horaativ.containsKey(hora)) {
            ArrayList<String> lista = horaativ.get(hora);
            lista.add(atividade);
            horaativ.put(hora, lista);
        }
        else{
            ArrayList<String> lista = new ArrayList();
            lista.add(atividade);
            horaativ.put(hora, lista);
        }
    }
    
    
    
}
