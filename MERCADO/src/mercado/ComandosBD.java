/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
/**
 *
 * @author 05200244
 */
public class ComandosBD {
    private static Connection con = null;

    public static void addUsuario(Usuario usu) throws SQLException, Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection con = ConexaoBD.getConnection();
        String sql = "INSERT INTO usuario (nome, senha, usuario_id) VALUES (?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, usu.getNome() );
            stmt.setString(2, usu.getSenha());
            stmt.setInt(3, usu.getId());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println("Erro " + ex);
            
        } finally {
            ConexaoBD.closeConnection(con, stmt);
        }
        
    }
    
    public static void addProduto(Produto prod) throws SQLException, Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection con = ConexaoBD.getConnection();
        String sql = "INSERT INTO item (preco, nome, item_id, tipo, descricao, imagem_prod) VALUES (?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(4, prod.getTipo());
            stmt.setString(5, prod.getDescricao());
            stmt.setFloat(1, prod.getPreco());
            stmt.setString(2, prod.getNome());
            stmt.setInt(3, prod.getId());
            BufferedImage imagembuffered = SwingFXUtils.fromFXImage(prod.getImagemprod(), null);
            File file  = new File("image.jpg");
            ImageIO.write(imagembuffered, "jpg", file );
            FileInputStream fis = new FileInputStream ( file );
            stmt.setBinaryStream(6, fis);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println("Erro " + ex);
            
        } finally {
            ConexaoBD.closeConnection(con, stmt);
        }
        
    } 
    
    
    public static ArrayList loadUsuarios() throws SQLException, Exception {
        ArrayList<Usuario> lista = new ArrayList();
        Connection con = ConexaoBD.getConnection();
        Statement st = con.createStatement();
        String sql = ("SELECT * FROM usuario");
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()) { 
            String nome = rs.getString("nome"); 
            String senha = rs.getString("senha");
            System.out.println(nome + senha);
            int id = rs.getInt("usuario_id");
            lista.add(new Usuario(nome, senha, id));
        }
        con.close();
        return lista;
    }
    
    public static ArrayList<Produto> loadProdutos() throws SQLException, Exception {
        ArrayList<Produto> lista = new ArrayList();
        Connection con = ConexaoBD.getConnection();
        Statement st = con.createStatement();
        String sql = ("SELECT * FROM item");
        ResultSet rs = st.executeQuery(sql);
        while ( rs.next() ) {
            String tipo = rs.getString("tipo");
            String descricao = rs.getString("descricao");
            float preco = rs.getFloat("preco");
            String nome = rs.getString("nome");
            java.sql.Blob blob = rs.getBlob("imagem_prod");
            int id = rs.getInt("item_id");
            InputStream in = blob.getBinaryStream();  
            BufferedImage image = ImageIO.read(in);
            Image imagem = SwingFXUtils.toFXImage(image, null);
            lista.add(new Produto(tipo, descricao, preco, nome, imagem, id));
        }
        con.close();
        return lista;
    } 

    public static void addCarrinho(Produto prod, Usuario usu) throws SQLException, Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection con = ConexaoBD.getConnection();
        String sql = "INSERT INTO carrinho (item_id, usuario_id) VALUES (?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = (PreparedStatement) con.prepareStatement(sql);
            stmt.setInt(1, prod.getId());
            stmt.setInt(2, usu.getId());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println("Erro " + ex);
            
        } finally {
            ConexaoBD.closeConnection(con, stmt);
        }
        
    }     
    
        public static ArrayList<Produto> loadCarrinho(Usuario usu) throws SQLException, Exception {
        ArrayList<Produto> lista = new ArrayList();
        Connection con = ConexaoBD.getConnection();
        Statement st = con.createStatement();
        String sql = ("SELECT item.* FROM carrinho, item, usuario where usuario.usuario_id =" +usu.getId()+ "and carrinho.usuario_id = usuario.usuario_id and carrinho.item_id = item.item_id;");
        ResultSet rs = st.executeQuery(sql);
        while ( rs.next() ) {
            String tipo = rs.getString("tipo");
            String descricao = rs.getString("descricao");
            float preco = rs.getFloat("preco");
            String nome = rs.getString("nome");
            java.sql.Blob blob = rs.getBlob("imagem_prod");
            int id = rs.getInt("item_id");
            InputStream in = blob.getBinaryStream();  
            BufferedImage image = ImageIO.read(in);
            Image imagem = SwingFXUtils.toFXImage(image, null);
            lista.add(new Produto(tipo, descricao, preco, nome, imagem, id));
        }
        con.close();
        return lista;
    }

}
