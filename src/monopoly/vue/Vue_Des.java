package monopoly.vue;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import monopoly.modele.Des;

public class Vue_Des {

    private Des des;
    private ImageView imgDe1;
    private ImageView imgDe2;

    public Vue_Des(Des des, ImageView imgDe1, ImageView imgDe2) {
        this.des = des;
        this.imgDe1 = imgDe1;
        this.imgDe2 = imgDe2;
    }

    public void miseAJour() {
        Image spr_de = new Image("file:src/monopoly/vue/data/image/dice_"+this.des.valDe1()+".png", true);
        this.imgDe1.setImage(spr_de);
        spr_de = new Image("file:src/monopoly/vue/data/image/dice_"+this.des.valDe2()+".png", true);
        this.imgDe2.setImage(spr_de);
    }

}
