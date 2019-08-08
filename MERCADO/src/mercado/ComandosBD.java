/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.awt.image.BufferedImage;
import java.io.File;
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
        String sql = "INSERT INTO usuario (tipo, descricao, preco, nome, item_id, imagem_prod) VALUES (?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, prod.getTipo());
            stmt.setString(2, prod.getDescricao());
            stmt.setFloat(3, prod.getPreco());
            stmt.setString(4, prod.getNome());
            stmt.setInt(5, prod.getId());
            stmt.setBlob(6, (Blob) prod.getImagemprod());
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
            InputStream in = blob.getBinaryStream();  
            BufferedImage image = ImageIO.read(in);
            BufferedImage imagem = image;
            lista.add(new Produto(tipo, descricao, preco, nome, imagem));
        }
        con.close();
        return lista;
    }       


}
