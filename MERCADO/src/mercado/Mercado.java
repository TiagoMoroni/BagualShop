/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercado;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class Mercado extends Application {
    

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TelaInicial.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setTitle("BagualShop");
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }

}