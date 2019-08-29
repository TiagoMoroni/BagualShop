package mercado;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.layout.TilePane;
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
    @FXML private Button botaoroupas;
    @FXML private Button botaoalimentos;
    @FXML private Button botaoveiculos;
    @FXML private Button botaocuias;
    @FXML private Button botaooutros;
    private ScrollPane scroll;
    @FXML public TilePane grandao;
    ArrayList<Produto> listaprod;
    public ArrayList<AnchorPane> listapainel;
    public Usuario usuarioatual = TelaInicialController.usuarioatual;
    public static Produto prodatual;
    @FXML
    private Button criaranuncio;
    @FXML 
    private Button botaoprocurar;
    

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
    public void clicouProcurar(ActionEvent event) throws IOException, Exception{
        TextInputDialog dialog = new TextInputDialog("Tran");

        dialog.setTitle("o7planning");
        dialog.setHeaderText("Enter your name:");
        dialog.setContentText("Name:");

        String pesquisa = null;
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            pesquisa = result.get();
        }
        ArrayList<Produto> listinha = new ArrayList();
        for (Produto prod : listaprod) {
            if (prod.getNome().equals(pesquisa)) {
                listinha.add(prod);
            }
        }
        mostrarProdutos(listinha);
    }
    
    @FXML
    public void clicouCarrinho(MouseEvent event) throws IOException{
        Parent telaLogadoParent = FXMLLoader.load(getClass().getResource("TelaCarrinho.fxml"));
        Scene telaLogadoScene = new Scene(telaLogadoParent);
        Stage tela = (Stage)((Node)event.getSource()).getScene().getWindow();
        tela.setScene(telaLogadoScene);
        tela.show();    
        tela.setTitle("BagualShop - Suas Compras");
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
    
    public void mostrarProdutos(ArrayList<Produto> listinha) throws MalformedURLException, Exception{
        grandao.getChildren().clear();
        for(Produto prod:listinha){
            grandao.getChildren().add(new ProdutoFx(prod));
        } 
    
    }    
    
    @FXML
    public void mostraRoupas() throws Exception{
        ArrayList<Produto> lista = new ArrayList();
        for (Produto prod : listaprod) {
            if (prod.getTipo().equals("Roupas e Acessórios")) {
                lista.add(prod);
            }
        }
        mostrarProdutos(lista);
    }
    
    @FXML
    public void mostraAlimentos() throws Exception{
        ArrayList<Produto> lista = new ArrayList();
        for (Produto prod : listaprod) {
            if (prod.getTipo().equals("Comidas")) {
                lista.add(prod);
            }
        }
        mostrarProdutos(lista);
    }
        
    @FXML
    public void mostraCuias() throws Exception{
        ArrayList<Produto> lista = new ArrayList();
        for (Produto prod : listaprod) {
            if (prod.getTipo().equals("Cuias")) {
                lista.add(prod);
            }
        }
        mostrarProdutos(lista);
    }
    
        
    @FXML
    public void mostraVeiculos() throws Exception{
        ArrayList<Produto> lista = new ArrayList();
        for (Produto prod : listaprod) {
            if (prod.getTipo().equals("Veículos e Acessórios")) {
                lista.add(prod);
            }
        }
        mostrarProdutos(lista);
    }
    
    @FXML
    public void mostraOutros() throws Exception{
        ArrayList<Produto> lista = new ArrayList();
        for (Produto prod : listaprod) {
            if (prod.getTipo().equals("Outros")) {
                lista.add(prod);
            }
        }
        mostrarProdutos(lista);
    }
    
    
    public void setarAnchor(){
        Line linhacentro = new Line();
        linhacentro.setStartX(scroll.getLayoutX()+scroll.getWidth()/2);
        linhacentro.setStartY(scroll.getLayoutY());
        linhacentro.setEndY(scroll.getHeight());
        linhacentro.setEndX(scroll.getLayoutX()+scroll.getWidth()/2);
    }
    
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
        botaoveiculos.setText("VEÍCULOS E ACESSÓRIOS");
        System.out.println(usuarioatual.getNome());
        try {
            setListaProd();
            nomeusuario.setText(usuarioatual.getNome());
            mostrarProdutos(listaprod);
        } catch (Exception ex) {
            System.err.println("Deu erro TelaMercadoController");
            ex.printStackTrace();
        }
        

    }    
    
}
