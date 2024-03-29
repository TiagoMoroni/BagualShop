
package mercado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import mercado.Mercado;


public class Usuario {
    
    private String nome;
    private String senha;
    private String tipo;
    private int id;
    private ArrayList<Usuario> lista;

    public int getId() {
        return id;
    }

    public void setId() {
        if (lista.size() == 0) {
            this.id = 0;
        }else{
            this.id = lista.get(lista.size()).getId() + 1;
        }    
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", senha=" + senha + "}";
    }

    public ArrayList<Usuario> getLista() {
        return lista;
    }

    public void setLista() throws SQLException, Exception {
        this.lista = Mercado.conexaobd.loadUsuarios();
    }
    
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
    
    public Usuario(String nome, String senha, String tipo) {
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Usuario(String nome, String senha, String tipo, int id) throws SQLException{
        this.nome = nome;
        this.senha = senha;
        this.id = id;
        this.tipo = tipo;
    }   
    
}
