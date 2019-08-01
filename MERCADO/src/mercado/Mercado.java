/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;

import javafx.stage.Stage;

public class Mercado extends Application {
    
    public static BagualShopStorage storage; 
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TelaInicial.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setTitle("BagualShop");
    }
    
    
    public static void main(String[] args) {
        storage = new BagualShopStorage("localhost",3306,"bagualshop", "root", "root");
        launch(args);
        
        
    }

}
