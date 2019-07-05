/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


public class TelaNovaContaController implements Initializable {

    @FXML private PasswordField recebesenha;
    @FXML private TextField recebenome;
    private ArrayList<Usuario> usuarios;
    
    @FXML
    public void apertouRegistrar(ActionEvent event){
        boolean temp = true;
        for(Usuario usu : usuarios){
            if (recebenome.getText().equals(usu.getNome())) {
                temp = false;
            }
        }
        if(temp){
            Usuario novousu = new Usuario(recebenome.getText(), recebesenha.getText());
            registrarUsuario(novousu);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void registrarUsuario(Usuario usu) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
