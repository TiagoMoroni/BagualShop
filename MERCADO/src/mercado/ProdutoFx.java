/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mercado.Mercado;

/**
 * oovelhA + QUE + DO +NOW + REFRI
 * @author 05200244
 */
public class ProdutoFx extends VBox{
    
    private ComandosBD bd = Mercado.conexaobd;
    private ArrayList<Produto> listaprod;
    private String name;
    private float preco;
    private Label nomeFx;
    private Image image;
    private Label precoFx;
    private ImageView imgFx;
    private Font defaultFont = new Font("Helvetica", 40); 
    public static Produto prodatual;

    
    
    public ProdutoFx(String nome, Float preco, String URL) throws Exception {
        super();
        
        this.name = nome;
        this.preco = preco;
        
        nomeFx = new Label(nome);
        precoFx = new Label("R$ "+preco.toString());
        image = new Image(TelaMercadoController.class.getResourceAsStream(URL));
        imgFx = new ImageView(image);
        listaprod = bd.loadProdutos();
        setDefault();
    }
    
    public ProdutoFx(String nome, Float preco, Image img) throws Exception {
        super();
        listaprod = bd.loadProdutos();
        this.name = nome;
        this.preco = preco;
        this.image = img;
        for (Produto prod:listaprod) {
            System.out.println(prod.toString());
        }
        nomeFx = new Label(nome);
        precoFx = new Label("R$ "+preco.toString());
        imgFx = new ImageView(image);

        
        setDefault();
        
    }
    
    private void setDefault() {
        super.getChildren().add(imgFx);
        super.getChildren().add(nomeFx);
        super.getChildren().add(precoFx);
        
        this.setStyle("-fx-border-color: black;\n" +
                      "-fx-border-insets: 5;\n");
        nomeFx.setFont(defaultFont);
        precoFx.setFont(defaultFont);
        
        this.setSpacing(5);
        
        this.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("asdasdas");
            for(Produto prod : listaprod){
                if (prod.getImagemprod().equals(imgFx)) {
                    System.out.println("aassddadaadasdsdiferente");
                    prodatual = prod;
                    Parent telaLogadoParent = null;
                    try {
                        telaLogadoParent = FXMLLoader.load(getClass().getResource("TelaMostraProdutos.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(ProdutoFx.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene telaLogadoScene = new Scene(telaLogadoParent);
                    Stage tela = (Stage)((Node)event.getSource()).getScene().getWindow();
                    tela.setScene(telaLogadoScene);
                    tela.show();    
                    tela.setTitle("BagualShop - Criar Anúncio");
                    tela.setTitle("BagualShop - " + prodatual.getNome());
                }
            }
            
        ;});
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
    public Label getNomeFx() {
        return nomeFx;
    }

    public void setNomeFx(Label nomeFx) {
        this.nomeFx = nomeFx;
    }

    public Label getPrecoFx() {
        return precoFx;
    }

    public void setPrecoFx(Label precoFx) {
        this.precoFx = precoFx;
    }

    public ImageView getImgFx() {
        return imgFx;
    }

    public void setImgFx(ImageView imgFx) {
        this.imgFx = imgFx;
    }

}
