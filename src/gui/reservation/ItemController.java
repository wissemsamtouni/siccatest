package gui.reservation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import interfaces.MyListener;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Evenement;
import util.Statics;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;
    @FXML
    private Label ddlabel;
    @FXML
    private Label dflabel;
    @FXML
    private Label idevtitm;
    @FXML
    private Button addpanier;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(event);
    }

    private Evenement event;
    private MyListener myListener;
    
    public void setData(Evenement event, MyListener myListener) {
        this.event = event;
        this.myListener = myListener;
        nameLabel.setText(event.getNom_evenement());
        priceLable.setText(String.valueOf(event.getPrix_ticket()));
        idevtitm.setText(String.valueOf(event.getId()));
        ddlabel.setText(String.valueOf(event.getDate_debut()));
      dflabel.setText(String.valueOf(event.getDate_fin()));
     //dd.setValue(LocalDate.parse(event.getDate_debut().toString()));
        String path = event.getImages();
        Image aa = new Image("file:" + path);
        // System.out.println(image);
        img.setImage(aa);
    }

    @FXML
    private void addpanier(ActionEvent event) {
         
        
    }

}
