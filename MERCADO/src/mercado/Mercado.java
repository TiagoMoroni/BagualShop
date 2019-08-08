package mercado;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;

import javafx.stage.Stage;

public class Mercado extends Application {
    
    public ArrayList<Usuario> listausuarios;
    public static ComandosBD conexaobd = new ComandosBD();
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TelaInicial.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setTitle("BagualShop");
    }
    
    
    public static void main(String[] args) throws SQLException {
        launch(args);
        
        
    }

}
