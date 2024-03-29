/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import mercado.Mercado;
import mercado.TelaNovaContaController;

/**
 *
 * @author 05200244
 */
public class TelaInicialController implements Initializable{
    
    @FXML private Label statuslogin;
    @FXML private Button botaologin;
    @FXML private TextField recebeusuario;
    @FXML private PasswordField recebesenha;
    @FXML private Button botaoregistrar;
    public ArrayList<Usuario> usuarios;
    public static Usuario usuarioatual;
   

    
    @FXML
    private void apertouLogin(ActionEvent event) throws IOException {
        for (Usuario usuario : usuarios) {
            if(recebeusuario.getText().equals(usuario.getNome())){
                if(usuario.getSenha().equals(recebesenha.getText())){
                    usuarioatual = usuario;
                    Parent telaLogadoParent = FXMLLoader.load(getClass().getResource("TelaMercado.fxml"));
                    Scene telaLogadoScene = new Scene(telaLogadoParent);
                    Stage tela = (Stage)((Node)event.getSource()).getScene().getWindow();
                    tela.setScene(telaLogadoScene);
                    tela.show();
                    tela.setTitle("BagualShop");
                    
                } else {
                    statuslogin.setText("Senha Errada");
                }
            } else {
                statuslogin.setText("Usuario Não Existe");
            }
        }
        if(usuarios.size() == 0){
            statuslogin.setText("Usuário não existe");
        }
    }
    
    @FXML
    private void apertouRegistrar(ActionEvent event) throws IOException{
        Parent telaLogadoParent = FXMLLoader.load(getClass().getResource("TelaNovaConta.fxml"));
        Scene telaLogadoScene = new Scene(telaLogadoParent);
        Stage tela = (Stage)((Node)event.getSource()).getScene().getWindow();
        tela.setScene(telaLogadoScene);
        tela.show();    
        tela.setTitle("BagualShop - Registro");
    
    }

    private void setLista() throws Exception {
        usuarios = Mercado.conexaobd.loadUsuarios();
    }    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setLista();
        } catch (Exception ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    



}

