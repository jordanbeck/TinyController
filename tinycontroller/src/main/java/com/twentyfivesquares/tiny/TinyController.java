package com.twentyfivesquares.tiny;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twentyfivesquares.tiny.exception.ControllerInstantiationException;

import java.lang.reflect.Constructor;

/**
 * This is the main class for this implementation of controllers. Its a work in progress. There
 * are three scenarios I'm trying to solve:
 *
 *  1.  The view that the controller will use is passed in to the controller. This would be
 *      useful when dealing with multiple form factors. It would be used with <include> tags
 *      in the layout files.
 *  2.  Pass in a parent view that the controller should inflate itself into. This is similar
 *      to #1 except there would be no <include> tags. It would be a simple {@link ViewGroup}
 *      instead.
 *  3.  Nothing is passed in and the controller inflates its own layout. This would be used
 *      for things like ViewPagers where all you need to is get the view and insert it.
 *
 *  I'm still not sure if we need to support all of these use-cases or just a subset.
 *
 *  UPDATE: I think the best approach right now is to go with #2. But that does make there more
 *  overhead for the ViewPager approach...
 */
public abstract class TinyController {

    protected final int NO_VIEW = -1;

    private final Context context;
    private View view;

    /**
     * Get the layout resource that this controller will be using. This will be used when
     * the view is being built inside the controller instead of being passed in.
     *
     * @return The resource id for the layout being used.
     */
    protected abstract @LayoutRes int getLayoutRes();

    public static TinyController instantiate(Context context, Class<? extends TinyController> klass, ViewGroup parent)
            throws ControllerInstantiationException {
        try {
            Constructor<? extends TinyController> constructor = klass.getConstructor(Context.class, ViewGroup.class);
            return constructor.newInstance(context, parent);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ControllerInstantiationException();
        }
    }

    public static TinyController instantiate(Context context,
                                             Class<? extends TinyController> klass,
                                             ViewGroup parent,
                                             Bundle savedInstanceState)
            throws ControllerInstantiationException {
        try {
            Constructor<? extends TinyController> constructor
                    = klass.getConstructor(Context.class, ViewGroup.class, Bundle.class);
            return constructor.newInstance(context, parent, savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ControllerInstantiationException();
        }
    }

    public TinyController(Context context) {
        this(context, null, null);
    }

    public TinyController(Context context, ViewGroup parent) {
        this(context, parent, null);
    }

    public TinyController(Context context, ViewGroup parent, Bundle savedInstanceState) {
        this.context = context;
        if (getLayoutRes() != NO_VIEW) {
            View view = LayoutInflater.from(context).inflate(getLayoutRes(), parent, parent != null);
            setView(view);
        }
        if (savedInstanceState != null && savedInstanceState.size() > 0) {
            resumeSavedState(savedInstanceState);
        }
    }

    /**
     * Set a main view that will be used for this controller.
     *
     * @param view The main View for this controller.
     */
    protected void setView(View view) {
        this.view = view;
    }

    /**
     * Called when a constructor is called with a {@link Bundle}. Should be used in conjunction
     * with {@link #onSavedInstanceState(Bundle)}.
     *
     * @param savedInstanceState    Bundle which contains saved state information.
     */
    protected void resumeSavedState(Bundle savedInstanceState) {
        // TODO: do anything here for all controllers?
    }

    /**
     * Save any state specific information. You can then pass it into the constructor which will
     * forward it on to {@link #resumeSavedState}. This is meant to mimic the
     * {@link android.app.Activity#onSaveInstanceState(Bundle)} and be called at the same time.
     *
     * @param outState  Bundle in which to place your saved state.
     */
    public void onSavedInstanceState(Bundle outState) {
        // TODO: do anything here for all controllers?
    }

    /**
     * Properly start this controller and associated view. This is meant to mimic the
     * {@link Activity#onStart()} and be called at the same time.
     */
    public void onStart() {
        // TODO: implement everything to properly start the views
    }

    /**
     * Properly resume this controller and associated view. This is meant to mimic the
     * {@link Activity#onResume()} and be called at the same time.
     */
    public void onResume() {
        // TODO: implement everything to properly resume the views
    }

    /**
     * Properly pause this controller and associated view. This is meant to mimic the
     * {@link Activity#onPause()} and be called at the same time.
     */
    public void onPause() {
        // TODO: implement everything to properly pause the views
    }

    /**
     * Properly stop this controller and associated view. This is meant to mimic the
     * {@link Activity#onStop()} and be called at the same time.
     */
    public void onStop() {
        // TODO: implement everything to properly stop the views
    }

    /**
     * Properly destroy this controller and associated view. This is meant to mimic the
     * {@link android.app.Activity#onDestroy()} and be called at the same time.
     */
    @CallSuper
    public void onDestroy() {
        // TODO: implement everything to properly destroy the views
    }

    /**
     * Get the context used to create the views.
     *
     * @return Context object
     */
    protected Context getContext() {
        return this.context;
    }

    /**
     * Get the view associated with this controller
     *
     * @return The view for this controller
     */
    public View getView() {
        return this.view;
    }

    /**
     * Shows the controller
     */
    public void show() {
        getView().setVisibility(View.VISIBLE);
    }

    /**
     * Hides the controller
     */
    public void hide() {
        getView().setVisibility(View.INVISIBLE);
    }

    /**
     * Look for a child view contained within this controller. If none is found, return null.
     *
     * @param id The id for the view you are looking for.
     *
     * @return the view with the given id or null if no View is found.
     */
    public View findViewById(int id) {
        if (view == null) {
            throw new IllegalStateException("View has not been set for this controller.");
        }

        return view.findViewById(id);
    }

    public void runOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
