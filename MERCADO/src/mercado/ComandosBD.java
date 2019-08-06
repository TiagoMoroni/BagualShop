/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
/**
 *
 * @author 05200244
 */
public class ComandosBD {
    private static Connection con = null;

    public static void addUsuario(Usuario usu) throws SQLException {
        Connection con = ConexaoBD.getConnection();
        String sql = "INSERT INTO usuario (Nome, senha) VALUES (?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, usu.getNome() );
            stmt.setString(2, usu.getSenha());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println("Erro " + ex);
            
        } finally {
            ConexaoBD.closeConnection(con, stmt);
        }
        
    }
    
    public static void addUsuario(Usuario usu) throws SQLException {
        Connection con = ConexaoBD.getConnection();
        String sql = "INSERT INTO usuario (Nome, senha) VALUES (?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, usu.getNome() );
            stmt.setString(2, usu.getSenha());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println("Erro " + ex);
            
        } finally {
            ConexaoBD.closeConnection(con, stmt);
        }
        
    }    
    
    public static ArrayList loadUsuarios() throws SQLException, Exception {
        ArrayList<Usuario> lista = new ArrayList();
        try {
            java.sql.Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM usuario");

            while ( rs.next() ) {
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                lista.add(new Usuario(nome, senha));
            }
            con.close();
        } catch (Exception e) {
            System.err.println("exception! ");
            System.err.println(e.getMessage());
        }
        return lista;
    }

    public static ArrayList<Produto> loadProdutos() throws SQLException, Exception {
        ArrayList<Produto> lista = new ArrayList();
        try {
            java.sql.Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM item");
            while ( rs.next() ) {
                String tipo = rs.getString("tipo");
                String descricao = rs.getString("descricao");
                float preco = rs.getFloat("preco");
                String nome = rs.getString("nome");
                Image imagem = (Image) rs.getBlob("imagem_prod");
                lista.add(new Produto(tipo, descricao, preco, nome, imagem));
            }
            con.close();
        } catch (Exception e) {
            System.err.println("exception! ");
            System.err.println(e.getMessage());
        }
        return lista;
    }       


}
