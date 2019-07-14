/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private String nome;
    private float preco;
    private String descricao;
    private String tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void clicouRegistrar(ActionEvent event){
        boolean erro;
        do{
            erro = true;
            String nome = recebenome.getText();
            String descricao = recebedescricao.getText();
            String tipo = recebetipo.getValue().toString();
            float preco = Float.parseFloat(recebepreco.getText());
            
        
        }while(erro == true);
    
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
        // TODO
    }    
    
}
