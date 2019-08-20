package mercado;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import mercado.Mercado;
import mercado.TelaInicialController;


/**
 * FXML Controller class
 *
 * @author 05200244
 */
public class TelaMercadoController implements Initializable {

    @FXML private Label nomeusuario;
    @FXML private ScrollPane scroll;
    @FXML public TilePane grandao;
    ArrayList<Produto> listaprod;
    @FXML public ArrayList<AnchorPane> listapainel;
    public Usuario usuarioatual = TelaInicialController.usuarioatual;
    public static Produto prodatual;
    @FXML
    private ImageView imagemprod;
    @FXML
    private Label nomeprod;
    @FXML
    private Label precoprod;
    @FXML
    private AnchorPane painel;
    

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
    
    public void clicouCriarAnuncio(ActionEvent event) throws IOException{
        Parent telaLogadoParent = FXMLLoader.load(getClass().getResource("CriarAnuncio.fxml"));
        Scene telaLogadoScene = new Scene(telaLogadoParent);
        Stage tela = (Stage)((Node)event.getSource()).getScene().getWindow();
        tela.setScene(telaLogadoScene);
        tela.show();    
        tela.setTitle("BagualShop - Criar Anúncio");
    }
    
    public void mostrarProdutos() throws MalformedURLException, Exception{
        for(Produto prod:listaprod){
            grandao.getChildren().add(new ProdutoFx(prod.getNome(), prod.getPreco(),prod.getImagemprod()));
        } 
    
    }    
    
    public void setarAnchor(){
        Line linhacentro = new Line();
        linhacentro.setStartX(scroll.getLayoutX()+scroll.getWidth()/2);
        linhacentro.setStartY(scroll.getLayoutY());
        linhacentro.setEndY(scroll.getHeight());
        linhacentro.setEndX(scroll.getLayoutX()+scroll.getWidth()/2);
    }
    
    @FXML
    public void clicouPainel(MouseEvent event) throws IOException{
        System.out.println("LCICOICUIOUAD");
        Image imagem = null;
        AnchorPane painel = (AnchorPane) event.getSource();
        AnchorPane painelgrandao = (AnchorPane) painel.getParent();
        ObservableList<Node> lista= painelgrandao.getChildren();
        for (Node node : lista) {
            if (node.getClass().getName().equals("javafx.scene.image.Image")) {
                imagem = ((ImageView) node).getImage();
            }
        }
        for(Produto prod : listaprod){
            if (prod.getImagemprod().equals(imagem)) {
                prodatual = prod;
                Parent telaLogadoParent = FXMLLoader.load(getClass().getResource("TelaMostraProdutos.fxml"));
                Scene telaLogadoScene = new Scene(telaLogadoParent);
                Stage tela = (Stage)((Node)event.getSource()).getScene().getWindow();
                tela.setScene(telaLogadoScene);
                tela.show();    
                tela.setTitle("BagualShop - " +prodatual.getNome());
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        
        System.out.println(usuarioatual.getNome());
        try {
            setListaProd();
            System.out.println(listaprod);
            nomeusuario.setText(usuarioatual.getNome());
            mostrarProdutos();
        } catch (Exception ex) {
            System.err.println("Deu erro TelaMercadoController");
            ex.printStackTrace();
        }
        

    }    
    
}
