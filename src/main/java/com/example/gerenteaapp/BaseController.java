package com.example.gerenteaapp;

import javafx.stage.Stage;

public class BaseController {
    protected Stage usingStage;

    public void setStage(Stage stage) {
        this.usingStage = stage;
    }

    public Stage getStage() {
        return usingStage;
    }

}
