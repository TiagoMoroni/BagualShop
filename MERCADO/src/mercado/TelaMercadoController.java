package mercado;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import mercado.Mercado;
import mercado.TelaInicialController;

/**
 * FXML Controller class
 *
 * @author 05200244
 */
public class TelaMercadoController implements Initializable {

    @FXML private Label nomeusuario;
    @FXML private Button criaranuncio;
    @FXML private ScrollPane scroll;
    @FXML public static Pane grandao;
    private ArrayList<Produto> listaprod;
    @FXML private ArrayList<AnchorPane> listapainel;
    @FXML private ImageView imagemtestemete;
    public Usuario usuarioatual = TelaInicialController.usuarioatual;
    

    public ArrayList<AnchorPane> getListapainel() {
        return listapainel;
    }

    public void setListapainel(ArrayList<AnchorPane> listapainel) {
        this.listapainel = listapainel;
    }
    
    public ArrayList<Produto> getListaprod() {
        return listaprod;
    }

    public void setListaProd() throws Exception{
        this.listaprod = Mercado.conexaobd.loadProdutos();
    }   
    
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
    public void mostrarProdutos(){
        for(Produto prod:listaprod){
            
        } 
    
    }
    
    @FXML
    public void setarAnchor(){
        Line linhacentro = new Line();
        linhacentro.setStartX(scroll.getLayoutX()+scroll.getWidth()/2);
        linhacentro.setStartY(scroll.getLayoutY());
        linhacentro.setEndY(scroll.getHeight());
        linhacentro.setEndX(scroll.getLayoutX()+scroll.getWidth()/2);
    }
    
    @FXML
    public void mostraNomeUsuario(String str) throws IOException{
        nomeusuario.setText(str);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        try {
            setListaProd();
            mostraNomeUsuario(usuarioatual.getNome());
        } catch (Exception ex) {
            System.err.println("Deu erro TelaMercadoController");
        }
        

    }    
    
}
