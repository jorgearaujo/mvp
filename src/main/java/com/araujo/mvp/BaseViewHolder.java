package com.araujo.mvp;

import android.view.View;

/**
 * Created by jorge.araujo on 1/1/2016.
 */
public interface BaseViewHolder {

    public abstract int getLayout();
    public abstract void generateViews(View view);
}
