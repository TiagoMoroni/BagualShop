/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mercado.Mercado;
import mercado.ProdutoFx;
import mercado.TelaInicialController;

public class TelaMostraProdutosController implements Initializable {
    @FXML
    private ImageView fotoproduto;
    @FXML
    private Label precoproduto;
    @FXML
    private Label nomeproduto;
    @FXML
    private Label descproduto;
    private Produto prod = ProdutoFx.prodatual;
    private ComandosBD bd = Mercado.conexaobd;
    private Usuario usu = TelaInicialController.usuarioatual;
    @FXML
    private Button botaocomprar;
    @FXML
    private Label label;
    @FXML
    private Button botaovoltar;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fotoproduto.setImage(prod.getImagemprod());
        precoproduto.setText(Float.toString(prod.getPreco()));
        nomeproduto.setText(prod.getNome());
        descproduto.setText(prod.getDescricao());       
    }
    
    @FXML
    public void clicouComprar(ActionEvent event) throws Exception{
        bd.addCarrinho(prod, usu);
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("BagualShop - " +prod.getNome());
        dialogoInfo.setHeaderText("Compra Realizada com Sucesso");
        dialogoInfo.setContentText("Finalize a sua compra em seu carrinho");
        dialogoInfo.showAndWait();
    }
    
    @FXML
    public void clicouVoltar(ActionEvent event) throws Exception{
        Parent telaLogadoParent = FXMLLoader.load(getClass().getResource("TelaMercado.fxml"));
        Scene telaLogadoScene = new Scene(telaLogadoParent);
        Stage tela = (Stage)((Node)event.getSource()).getScene().getWindow();
        tela.setScene(telaLogadoScene);
        tela.show();    
        tela.setTitle("BagualShop - Mercado");
    }
    
}
