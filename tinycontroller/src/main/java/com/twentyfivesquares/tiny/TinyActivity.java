package com.twentyfivesquares.tiny;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TinyActivity extends AppCompatActivity {

    private TinyController controller;

    protected void setContentController(TinyController controller) {
        this.controller = controller;
        setContentView(controller.getView());
    }

    /**
     * Pass along the {@link #onSaveInstanceState(Bundle)} call to the controller
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (controller != null) {
            controller.onSavedInstanceState(outState);
        }
    }

    /**
     * Send the {@link #onStart()} lifecycle event to the controller
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (controller != null) {
            controller.onStart();
        }
    }

    /**
     * Send the {@link #onResume()} lifecycle event to the controller
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (controller != null) {
            controller.onResume();
        }
    }

    /**
     * Send the {@link #onPause()} lifecycle event to the controller
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (controller != null) {
            controller.onPause();
        }
    }

    /**
     * Send the {@link #onStop()} lifecycle event to the controller
     */
    @Override
    protected void onStop() {
        super.onStop();
        if (controller != null) {
            controller.onStop();
        }
    }

    /**
     * Send the {@link #onDestroy()} lifecycle event to the controller
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (controller != null) {
            controller.onDestroy();
        }
    }
}
