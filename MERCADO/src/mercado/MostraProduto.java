package mercado;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import mercado.TelaMercadoController;

public class MostraProduto {
    
    private static int TAMANHO = 355;
    private static int ALTURA = 355;
    @FXML private AnchorPane anchor = new AnchorPane();
    @FXML private Label nomeprecoprod;
    @FXML private ImageView imagemprod;
    @FXML private Label precoprod;
    private Produto prod;
    private int locx;
    private Pane grandao = TelaMercadoController.grandao;
    private int locy;

    public MostraProduto(Produto prod, int locx, int locy) {
        this.prod = prod;
        this.locx = locx;
        this.locy = locy;
        anchor.setPrefSize(TAMANHO, ALTURA);
        anchor.setLayoutX(locx);
        anchor.setLayoutY(locy);
        grandao.getChildren().add(anchor);
    }
    
  
}
