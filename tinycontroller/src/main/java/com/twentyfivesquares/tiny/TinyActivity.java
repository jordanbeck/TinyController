package com.twentyfivesquares.tiny;

import android.support.v7.app.AppCompatActivity;

public class TinyActivity extends AppCompatActivity {

    private TinyController controller;

    protected void setContentController(TinyController controller) {
        this.controller = controller;
        setContentView(controller.getView());
    }
}
