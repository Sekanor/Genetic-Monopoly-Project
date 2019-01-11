package monopoly.vue.dialogue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Dialogue {

    public Dialogue(String titre, String fichierFxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fichierFxml));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle(titre);
        stage.getIcons().add(new Image("file:src/monopoly/vue/data/image/Icone.png"));
        stage.setScene(scene);
        stage.show();
    }

}
