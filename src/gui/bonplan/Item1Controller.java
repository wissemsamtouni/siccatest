package gui.bonplan;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import interfaces.MyListener;
import model.Bonplans;

public class Item1Controller {

    @FXML
    private Label nameLabel;

    @FXML
    private Label categorieLable;

    @FXML
    private ImageView img;
    @FXML
    private Label labelds;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(bpl);
    }

    private Bonplans bpl;
    private MyListener myListener;

    public void setData(Bonplans bpl, MyListener myListener) {
        this.bpl = bpl;
        this.myListener = myListener;
        nameLabel.setText(bpl.getNom_bonplan());
        categorieLable.setText(bpl.getcategories().getType_categories());
        labelds.setText(bpl.getDescription());

        String path = bpl.getImages();
        Image aa = new Image("file:" + path);
        // System.out.println(image);
        img.setImage(aa);
    }
}
