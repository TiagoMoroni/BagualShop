/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author 05200244
 */
public class CriarAnuncioController implements Initializable {

    @FXML private ImageView imagemprod; 
    @FXML private TextField recebenome;
    @FXML private TextField recebepreco;
    @FXML private TextField recebedescricao;
    @FXML private ChoiceBox recebetipo;
    @FXML private Button registrar;
    @FXML private Button voltar;
    @FXML private Label mensagem;
    
    public void clicouVoltar(ActionEvent event) throws IOException{
        Parent telaLogadoParent = FXMLLoader.load(getClass().getResource("TelaMercado.fxml"));
        Scene telaLogadoScene = new Scene(telaLogadoParent);
        Stage tela = (Stage)((Node)event.getSource()).getScene().getWindow();
        tela.setScene(telaLogadoScene);
        tela.show();    
        tela.setTitle("BagualShop - Mercado");
    }
    
    public void clicouRegistrar(ActionEvent event){
        boolean erro;
        String nome;
        String descricao;
        String tipo;
        float preco;
        preco = (float) 12.4;
        do{
            erro = true;
            nome = recebenome.getText();
            descricao = recebedescricao.getText();
            tipo = recebetipo.getValue().toString();
            preco = Float.parseFloat(recebepreco.getText());
            char[] chararray = descricao. toCharArray();
            if(tipo == null || tipo.equals("")){
                erro = false;
                mensagem.setText("Selecione um tipo válido");
            }else if(chararray.length < 5){
                erro = false;
                mensagem.setText("Adicione uma descrição válida"); 
            }
        
        }while(erro == false);
    
    }
    
    @FXML
    public void clicouImagem(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione a imagem do produto");
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null && ImageIO.read(file) == null ) {
            Image image = new Image(file.toURI().toString());
            imagemprod.setImage(image);
        }
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> lista = new ArrayList();
        lista.add("Cuias");
        lista.add("Carroças");
        lista.add("Cavalos");
        lista.add("Comidas");
        lista.add("Roupas e Acessórios");
        ObservableList<String> list = FXCollections.observableArrayList(lista);
        recebetipo.setItems(list);
    }    
    
}
