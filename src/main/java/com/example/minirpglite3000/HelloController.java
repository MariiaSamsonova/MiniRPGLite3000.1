package com.example.minirpglite3000;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class HelloController {

    int n;

    @FXML
    void onPlayButtonClick() throws IOException {
        onStartButtonClick();
//        for(int i = 0; i < this.f)
//        TextArea input = new TextArea("hero" + i)
    }

    @FXML
    void onStartButtonClick() throws IOException {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.titleProperty();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void setNumberOfHeroes()
    {
        //String name = numberOfHeroes.getText();
    }
}