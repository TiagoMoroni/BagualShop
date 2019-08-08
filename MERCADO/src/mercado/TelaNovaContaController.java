/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mercado.Mercado;


public class TelaNovaContaController implements Initializable {

    @FXML private PasswordField recebesenha;
    @FXML private TextField recebenome;
    @FXML private Label alo;
    @FXML private Button voltar;
    public ArrayList<Usuario> usuarios;
    
    
    @FXML
    public void apertouRegistrar(ActionEvent event) throws SQLException, Exception{
        boolean temp = true;
        for(Usuario usu : usuarios){
            if (recebenome.getText().equals(usu.getNome())) {
                temp = false;
                alo.setText("DIGITE UM NOME DIFERENTE!!!");
                break;
            }
        }
        if(temp){
            Usuario novousu = new Usuario(recebenome.getText(), recebesenha.getText(), "cliente");
            registrarUsuario(novousu);
            alo.setText("CADASTRADO COM SUCESSO!!!");
            System.out.println(usuarios);
        }
    }
    
    @FXML
    public void apertouVoltar(ActionEvent event) throws IOException{
        Parent telaLogadoParent = FXMLLoader.load(getClass().getResource("TelaInicial.fxml"));
        Scene telaLogadoScene = new Scene(telaLogadoParent);
        Stage tela = (Stage)((Node)event.getSource()).getScene().getWindow();
        tela.setScene(telaLogadoScene);
        tela.show();    
        tela.setTitle("BagualShop - Menu Inicial");
    
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setLista();
        } catch (Exception ex) {
            Logger.getLogger(TelaNovaContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrarUsuario(Usuario usu) throws SQLException, Exception {
        Mercado.conexaobd.addUsuario(usu);
    }

    private void setLista() throws Exception {
        usuarios = Mercado.conexaobd.loadUsuarios();
    }
    
}
