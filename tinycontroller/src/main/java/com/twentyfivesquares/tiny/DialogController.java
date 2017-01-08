package com.twentyfivesquares.tiny;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class DialogController extends TinyController {

    private OnHideListener onHideListener;
    private boolean showing;
    private boolean backgroundTouchEnabled;
    private ViewGroup vDialog;

    public interface OnHideListener {
        void onHide();
    }

    public DialogController(Context context, ViewGroup parent) {
        super(context, parent);

        backgroundTouchEnabled = true;

        // Build the dialog container
        LayoutInflater inflater = LayoutInflater.from(context);
        vDialog = (ViewGroup) inflater.inflate(R.layout.controller_dialog, parent, false);
        vDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backgroundTouchEnabled) {
                    hide();
                }
            }
        });

        // Inflate the content and set the margins
        View content = inflater.inflate(getDialogLayoutRes(), vDialog, false);
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do nothing. Just prevents clicks from going through to parent.
                // TODO: figured out another way to do this?
            }
        });
        // Overwrite the layout params to MATCH_PARENT
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) content.getLayoutParams();
        lp.setMargins(getHorizontalMargin(context), getVerticalMargin(context), getHorizontalMargin(context), getVerticalMargin(context));
        content.setLayoutParams(lp);

        // Add the content and set the view for the controller
        parent.addView(vDialog);
        vDialog.addView(content);
        setView(vDialog);

        vDialog.setAlpha(0f);
        vDialog.setVisibility(View.INVISIBLE);
        showing = false;
    }

    /**
     * Get the layout resource for the content of the dialog.
     *
     * @return The resource id for the layout being used
     */
    protected abstract @LayoutRes
    int getDialogLayoutRes();

    @Override
    protected int getLayoutRes() {
        return NO_VIEW;
    }

    /**
     * Gets the margin to be used for the sides of a dialog. Override this to change the horizontal
     * margins of the dialog.
     *
     * @param context
     * @return The margin used for left/right of the dialog.
     */
    protected int getHorizontalMargin(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.generic_xlarge);
    }

    /**
     * Gets the margin to be used for the top and bottom of a dialog. Override this to change the
     * vertical margins of the dialog.
     *
     * @param context
     * @return The margin used for top/bottom of the dialog.
     */
    protected int getVerticalMargin(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.generic_xlarge);
    }

    public void show() {
        show(true);
    }

    public void show(boolean animate) {
        showing = true;
        if (animate) {
            vDialog.animate().alpha(1f).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    vDialog.setVisibility(View.VISIBLE);
                }
            }).start();
        } else {
            vDialog.setAlpha(1f);
            vDialog.setVisibility(View.VISIBLE);
        }
    }

    public void setOnHideListener(OnHideListener onHideListener) {
        this.onHideListener = onHideListener;
    }

    public void hide() {
        hide(true);
    }

    /**
     * Triggers the listener
     * @param animate
     */
    public void hide(boolean animate) {
        showing = false;
        if (animate) {
            vDialog.animate()
                    .alpha(0f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            vDialog.setVisibility(View.INVISIBLE);
                        }
                    }).start();
        } else {
            vDialog.setAlpha(0f);
            vDialog.setVisibility(View.INVISIBLE);
        }
        if (onHideListener != null) {
            onHideListener.onHide();
        }
    }

    public boolean isShowing() {
        return showing;
    }

    public void disableOutsideTouch() {
        backgroundTouchEnabled = false;
    }

    public void enableOutsideTouch() {
        backgroundTouchEnabled = true;
    }
}
