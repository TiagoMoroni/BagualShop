package mercado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import mercado.Mercado;

public class Produto {
    
    private String tipo;
    private String descricao;
    private float preco;
    private String nome;
    private int id;
    private Image imagemprod; 
    private ArrayList<Produto> lista;

    public Image getImagemprod() {
        return imagemprod;
    }

    public void setImagemprod(Image imagemprod) {
        this.imagemprod = imagemprod;
    }

    public ArrayList<Produto> getLista() {
        return lista;
    }

    public void setLista() throws Exception {
        this.lista = Mercado.conexaobd.loadProdutos();
    }

    public String getTipo() {
        return tipo;
    }

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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Produto(String tipo, String descricao, float preco, String nome, Image imagemprod, int id) throws SQLException, Exception {      
        this.tipo = tipo;
        this.descricao = descricao;
        this.preco = preco;
        this.nome = nome;
        this.imagemprod = imagemprod;
        this.id = id;
    }
    
        public Produto(String tipo, String descricao, float preco, String nome, Image imagemprod) throws SQLException, Exception {    
            this.tipo = tipo;
            this.descricao = descricao;
            this.preco = preco;
            this.nome = nome;
            this.imagemprod = imagemprod;
    }

    @Override
    public String toString() {
        return "Produto{" + "tipo=" + tipo + ", descricao=" + descricao + ", preco=" + preco + ", nome=" + nome + ", id=" + id + ", imagemprod=" + imagemprod + '}';
    }
   
}
