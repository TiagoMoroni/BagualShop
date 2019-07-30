/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

/**
 *
 * @author 05200244
 */
public class BagualShopStorage extends Database{
    
    private final String PRODUTOS = "produtos";
    private final String USUARIOS = "usuarios";
    
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
        query("INSERT INTO " + USUARIOS + " (Nome, Senha) VALUES (?, ?);",
                usu.getNome(),
                usu.getSenha());
    }
    
    public void addProduto(Produto prod) {
    query("INSERT INTO " + PRODUTOS + " (Nome, Preco, Tipo, Descrição, ImagemProd) VALUES (?, ?, ?, ?, ?);",
            prod.getNome(),
            prod.getPreco(),
            prod.getTipo(),
            prod.getDescricao(),
            prod.getImagemprod());
    }

}
