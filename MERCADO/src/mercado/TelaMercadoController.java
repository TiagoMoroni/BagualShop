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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author 05200244
 */
public class TelaMercadoController implements Initializable {

    @FXML
    private Label nomeusuario;
    private Usuario usuatual;
    
    @FXML
    public void mostraNomeUsuario() throws IOException{
        nomeusuario.setText(usuatual.getNome());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Usuario usuatual = new Usuario("Henrique", "alo");
        
        try {
            mostraNomeUsuario();
        } catch (IOException ex) {
            Logger.getLogger(TelaMercadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
