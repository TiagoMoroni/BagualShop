/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import com.sun.glass.ui.Size;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import static javafx.print.PaperSource.MAIN;
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
import javax.imageio.ImageIO;
import static javax.print.attribute.standard.MediaTray.MAIN;
import javax.swing.ImageIcon;
import static javax.swing.text.StyleConstants.Size;
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
    
    public ProdutoFx(Produto produto) throws Exception {
        super();
        listaprod = bd.loadProdutos();
        this.name = produto.getNome();
        this.preco = produto.getPreco();
        this.image = produto.getImagemprod();
        for (Produto prod:listaprod) {
            System.out.println(prod.toString());
        }
        nomeFx = new Label(name);
        precoFx = new Label("R$ " + preco);
        imgFx = new ImageView(image);
        setDefault(produto);
        this.setPrefHeight(355);
        this.setPrefWidth(355);
        this.setMaxWidth(355);
        this.setMaxHeight(355);
        imgFx.setFitHeight(355);
        imgFx.setFitWidth(355);
        imgFx.setPreserveRatio(true);
        imgFx.fitWidthProperty().bind(new SimpleIntegerProperty(250).asObject());
        imgFx.fitHeightProperty().bind(new SimpleIntegerProperty(250).asObject());
    }
    
    private void setDefault(Produto produto) {
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
                if (compareImage(prod.getImagemprod(), produto.getImagemprod())) {
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

    public static boolean compareImage(Image imgA, Image imgB) {        
        if(returnPixelVal().equals(returnPixelVal())){
            return true;
        }
        return false;
    }
    
    public static byte[] returnPixelVal(File in) {

        BufferedImage img = null;
        File f = null;
        byte[] pixels = null;
        // read image
        try {
            f = in;
            img = ImageIO.read(f);
            pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        } catch (IOException e) {
            System.out.println(e);
        }

        return pixels;

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
