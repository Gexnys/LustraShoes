package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class falanfilan {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cıkıs_bttn;

    @FXML
    void CloseForm(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert cıkıs_bttn != null : "fx:id=\"cıkıs_bttn\" was not injected: check your FXML file 'falanfilan.fxml'.";

    }

}
