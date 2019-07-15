/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 05200244
 */
public class TelaMercadoController implements Initializable {

    @FXML private Label nomeusuario;
    @FXML private Button criaranuncio;
    private Usuario usuatual;
    
    @FXML
    public void clicouCriarAnuncio(ActionEvent event) throws IOException{
        Parent telaLogadoParent = FXMLLoader.load(getClass().getResource("CriarAnuncio.fxml"));
        Scene telaLogadoScene = new Scene(telaLogadoParent);
        Stage tela = (Stage)((Node)event.getSource()).getScene().getWindow();
        tela.setScene(telaLogadoScene);
        tela.show();    
        tela.setTitle("BagualShop - Criar Anúncio");
    
    
    }
    
    @FXML
    public void mostraNomeUsuario() throws IOException{
        nomeusuario.setText(usuatual.getNome());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
