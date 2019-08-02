/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author 05200244
 */
public class BagualShopStorage extends Database{
    
    private final String PRODUTOS = "item";
    private final String USUARIOS = "usuario";
    
    public BagualShopStorage(String host, Integer port, String database, String user, String password) {
    super(host, port, database, user, password);

    query("CREATE TABLE IF NOT EXISTS " + PRODUTOS + "("
            + "name VARCHAR(16) PRIMARY KEY NOT NULL,"
            + "owner VARCHAR(32),"
            + "world VARCHAR(16),"
            + "location_min VARCHAR(100),"
            + "location_max VARCHAR(100));");
    query("CREATE TABLE IF NOT EXISTS " + USUARIOS + "("
            + "region VARCHAR(16) PRIMARY KEY NOT NULL,"
            + "flag VARCHAR(16) NOT NULL,"
            + "value VARCHAR(256) NOT NULL);");
    }

    
    public void addUsuario(Usuario usu) {
        query("INSERT INTO " + USUARIOS + " (nome, senha) VALUES (?, ?);",
                usu.getNome(),
                usu.getSenha());
    }
    
    public void addProduto(Produto prod) {
    query("INSERT INTO " + PRODUTOS + " (nome, preco, tipo, descrição, imagemProd) VALUES (?, ?, ?, ?, ?);",
            prod.getNome(),
            prod.getPreco(),
            prod.getTipo(),
            prod.getDescricao(),
            prod.getImagemprod());
    }
    
    public ArrayList<Usuario> loadUsuarios() {
        ArrayList<Usuario> lista = new ArrayList();
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM " + USUARIOS);


            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Usuario usu = new Usuario(result.getString("nome"), result.getString("senha"));
                lista.add(usu);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("deu erro");
        }
        return lista;
    }
    
    public ArrayList<Produto> loadProdutos() {
    ArrayList<Produto> lista = new ArrayList();
    try {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM " + PRODUTOS);


        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Produto prod = new Produto(result.getString("tipo"), result.getString("descricao"), result.getFloat("preco"), result.getString("nome"), (Image) result.getBlob("imagemprod"));
            lista.add(prod);
        }
        result.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}
}

