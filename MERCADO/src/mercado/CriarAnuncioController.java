/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import mercado.Mercado;

/**
 * FXML Controller class
 *
 * @author 05200244
 */
public class CriarAnuncioController implements Initializable{

    @FXML private ImageView imagemprod; 
    @FXML private TextField recebenome;
    @FXML private TextField recebepreco;
    @FXML private TextField recebedescricao;
    @FXML private ChoiceBox recebetipo;
    @FXML private Button registrar;
    @FXML private Button voltar;
    @FXML private Button botao;
    String nome;
    String descricao;
    String tipo;
    float preco;
    Image imagem;
    ArrayList<Produto> lista;

    public ArrayList<Produto> getLista() {
        return lista;
    }

    public void setLista() {
        this.lista = new ArrayList();
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
    public void clicouRegistrar(ActionEvent event) throws SQLException, Exception{
        boolean erro;
        this.nome = recebenome.getText();
        this.descricao = recebedescricao.getText();
        this.tipo = recebetipo.getValue().toString();
        this.preco = Float.parseFloat(recebepreco.getText());
        char[] chararray = descricao. toCharArray();
        erro = true;
        if(tipo.equals(null) || tipo.equals("")){
            erro = false;
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("BagualShop - Criar Anúncio");
            dialogoInfo.setHeaderText("Erro ao Cadastrar");
            dialogoInfo.setContentText("Selecione um tipo válido");
            dialogoInfo.showAndWait();
        }else if(chararray.length < 5 || descricao.equals("") || descricao.equals(null)){
            erro = false;
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("BagualShop - Criar Anúncio");
            dialogoInfo.setHeaderText("Erro ao Cadastrar");
            dialogoInfo.setContentText("Adicione uma Descrição Válida");
            dialogoInfo.showAndWait();
        }else if(imagem.equals(null)){
            erro = false;
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("BagualShop - Criar Anúncio");
            dialogoInfo.setHeaderText("Erro ao Cadastrar");
            dialogoInfo.setContentText("Adicione uma imagem do produto");
            dialogoInfo.showAndWait();
        }else if(nome.equals(null) || nome.equals("")){
            erro = false;
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("BagualShop - Criar Anúncio");
            dialogoInfo.setHeaderText("Erro ao Cadastrar");
            dialogoInfo.setContentText("Adicione um nome válido");
            dialogoInfo.showAndWait();
        }else if(preco == 0.0f){
            erro = false;
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("BagualShop - Criar Anúncio");
            dialogoInfo.setHeaderText("Erro ao Cadastrar");
            dialogoInfo.setContentText("Adicione um nome válido");
            dialogoInfo.showAndWait();
        }else if(erro == true){    
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("BagualShop - Criar Anúncio");
            dialogoInfo.setHeaderText("Produto cadastrado com sucesso");
            dialogoInfo.setContentText("");
            dialogoInfo.showAndWait();
            Produto prod = new Produto(tipo, descricao, preco, nome, imagem);
            lista.add(prod);
            Mercado.conexaobd.addProduto(prod);  
        }
    }
    

    
    @FXML
    public void clicouImagem(MouseEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione a imagem do produto");
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null && ImageIO.read(file) != null ) {
            try{   
                Image imagem = new Image(file.toURI().toString());
                imagemprod.setImage(imagem);
                this.imagem = imagem;
            }catch(Exception ex){
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("BagualShop - Criar Anúncio");
            dialogoInfo.setHeaderText("Erro ao Cadastrar");
            dialogoInfo.setContentText("O arquivo não é uma foto");
            dialogoInfo.showAndWait();
            }
        }
    }
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> lista = new ArrayList();
        lista.add("Cuias");
        lista.add("Veículos e Acessórios");
        lista.add("Comidas");
        lista.add("Roupas e Acessórios");
        lista.add("Outros");
        ObservableList<String> list = FXCollections.observableArrayList(lista);
        recebetipo.setItems(list);
        setLista();
    }
    
}
