
package mercado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


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

    public ArrayList<Usuario> getLista() {
        return lista;
    }

    public void setLista() throws SQLException {
        try{    
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/BancodeDados","root","root");
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            System.out.println("Conectado!");
            conexao.close();
        }catch(Exception e){
            System.err.println("NAO CONSEGUIU ENCONTRAR O BANCO");
        }
            this.lista = new ArrayList();
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
    }

    public Usuario(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
    }   
    
}
