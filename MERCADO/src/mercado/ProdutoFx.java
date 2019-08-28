/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
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

/*
 * @author 05200244
 */
public class ProdutoFx extends VBox{
    
    private ComandosBD bd = Mercado.conexaobd;
    private ArrayList<Produto> listaprod;
    private String name;
    private float preco;
    @FXML private Label nomeFx;
    @FXML private Image image;
    @FXML private Label precoFx;
    @FXML private ImageView imgFx;
    @FXML private Font defaultFont = new Font("Century Gothic", 30); 
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
                      "-fx-border-insets: 5;\n" +
                      "-fx-border-right: 5;\n" +
                      "-fx-border-right-style: solid;\n");
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
            
        });
        centerImage();
    }
    
    public void centerImage() {
        Image img = imgFx.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imgFx.getFitWidth() / img.getWidth();
            double ratioY = imgFx.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imgFx.setX((imgFx.getFitWidth() - w) / 2);
            imgFx.setY((imgFx.getFitHeight() - h) / 2);

        }
    }
    
    
    public static boolean compareImage(Image a, Image b) {        
        try {
            // take buffer data from botm image files //
            BufferedImage biA = toBufferedImage(a);
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();                      
            BufferedImage biB = toBufferedImage(b);
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();
            // compare data-buffer objects //
            if(sizeA == sizeB) {
                for(int i=0; i<sizeA; i++) { 
                    if(dbA.getElem(i) != dbB.getElem(i)) {
                        return false;
                    }
                }
                return true;
            }
            else {
                return false;
            }
        } 
        catch (Exception e) { 
            System.out.println("Failed to compare image files ...");
            return  false;
        }
    }
    
    public static BufferedImage toBufferedImage(Image img){
        BufferedImage image = SwingFXUtils.fromFXImage(img, null);
        return image;
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
