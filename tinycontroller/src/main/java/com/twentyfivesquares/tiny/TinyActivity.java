package com.twentyfivesquares.tiny;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.Stack;

public class TinyActivity extends AppCompatActivity {

    private Stack<TinyController> controllers;

    private FrameLayout vContentContainer;

    protected void addController(TinyController controller, boolean addToParent) {
        if (vContentContainer == null) {
            controllers = new Stack<>();

            vContentContainer = new FrameLayout(this);
            vContentContainer.setLayoutParams(
                    new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));

            setContentView(vContentContainer);
        }

        controllers.add(controller);
        // TODO: This feels like an awkward hack...
        if (addToParent) {
            vContentContainer.addView(controller.getView());
        }

        // Add controller to lifecycle observer
        getLifecycle().addObserver(controller);
    }

    protected ViewGroup getContentContainer() {
        return vContentContainer;
    }

    /**
     * Pass along the {@link #onSaveInstanceState(Bundle)} call to all controllers
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for (TinyController c : controllers) {
            c.onSavedInstanceState(outState);
        }
    }

    /**
     * Send the {@link #onStart()} lifecycle event to the controller
     */
    @Override
    protected void onStart() {
        super.onStart();
        for (TinyController c : controllers) {
            c.onStart();
        }
    }

    @Override
    public void onBackPressed() {
        if (controllers.size() == 1) {
            super.onBackPressed();
            return;
        }

        TinyController controller = controllers.pop();

        if (controller instanceof DialogController) {
            ((DialogController) controller).hide(true);
        }
    }
}
