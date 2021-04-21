package dev.controler;

import dev.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private TextField nickField;

    @FXML
    private Button submitButton;

    @FXML
    private Button highScoreButton;

    public void initialize() {
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main main = new Main();
                Stage stage = (Stage) submitButton.getScene().getWindow();
                try {
                    main.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
