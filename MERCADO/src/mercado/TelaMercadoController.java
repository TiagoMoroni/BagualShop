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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 05200244
 */
public class TelaMercadoController implements Initializable {

    @FXML private Label nomeusuario;
    @FXML private Button criaranuncio;
    @FXML private ScrollPane scroll;
    @FXML private Pane grandao;
    private Usuario usuatual;
    private ArrayList<Produto> listaprod;
    @FXML private ArrayList<AnchorPane> listapainel;
    

    public ArrayList<AnchorPane> getListapainel() {
        return listapainel;
    }

    public void setListapainel(ArrayList<AnchorPane> listapainel) {
        this.listapainel = listapainel;
    }

    public Usuario getUsuatual() {
        return usuatual;
    }

    public void setUsuatual(Usuario usuatual) {
        this.usuatual = usuatual;
    }

    public ArrayList<Produto> getListaprod() {
        return listaprod;
    }

    public void setListaprod() throws SQLException {
        try{    
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/BancodeDados","root","root");
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            System.out.println("Conectado!");
            conexao.close();
        }catch(Exception e){
            System.err.println("NAO CONSEGUIU ENCONTRAR O BANCO");
        }
            this.listaprod = new ArrayList();
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
    public void SetarAnchor(){
        Line linhacentro = new Line();
        linhacentro.setStartX(scroll.getLayoutX()+scroll.getWidth()/2);
        linhacentro.setStartY(scroll.getLayoutY());
        linhacentro.setEndY(scroll.getHeight());
        linhacentro.setEndX(scroll.getLayoutX()+scroll.getWidth()/2);
    }
    
    @FXML
    public void mostraNomeUsuario() throws IOException{
        nomeusuario.setText(usuatual.getNome());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        try {
            setListaprod();
        } catch (SQLException ex) {
            Logger.getLogger(TelaMercadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        SetarAnchor();
        mostrarProdutos();
    }    
    
}
