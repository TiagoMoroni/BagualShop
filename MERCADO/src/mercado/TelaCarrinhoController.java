/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import mercado.Mercado;
import mercado.TelaInicialController;

/**
 * FXML Controller class
 *
 * @author Tiago
 */
public class TelaCarrinhoController implements Initializable {
    @FXML
    private Button botaovoltar;
    @FXML
    private Button botaocomprar;
    @FXML
    private TilePane grandao;
    private ArrayList<Produto> lista;
    private ComandosBD bd = Mercado.conexaobd;
    private Usuario usu = TelaInicialController.usuarioatual;
    @FXML
    private Label mostraTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lista = bd.loadCarrinho(usu);
        } catch (Exception ex) {
            Logger.getLogger(TelaCarrinhoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            mostrarProdutos();
        } catch (Exception ex) {
            Logger.getLogger(TelaCarrinhoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        float total = 0.0f;
        for (Produto prod : lista) {
            total += prod.getPreco();
        }
        mostraTotal.setText(mostraTotal.getText() + total);
    }    

    public void mostrarProdutos() throws MalformedURLException, Exception{
        grandao.getChildren().clear();
        for(Produto prod:lista){
            grandao.getChildren().add(new ProdutoFx(prod));
        } 
    
    }
    
    @FXML
    public void clicouVoltar(ActionEvent event) throws IOException{
        Parent telaLogadoParent = FXMLLoader.load(getClass().getResource("TelaMercado.fxml"));
        Scene telaLogadoScene = new Scene(telaLogadoParent);
        Stage tela = (Stage)((Node)event.getSource()).getScene().getWindow();
        tela.setScene(telaLogadoScene);
        tela.show();    
        tela.setTitle("BagualShop - Mercado");
    }

    @FXML
    private void clicouComprar(ActionEvent event) {
        Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
        dialogoInfo.setTitle("BagualShop - Criar Anúncio");
        dialogoInfo.setHeaderText("Não consegui autorização com o banco federal pra vender");
        dialogoInfo.setContentText("É os guri, palmas pra nós");
        dialogoInfo.showAndWait();
    }
    
}
