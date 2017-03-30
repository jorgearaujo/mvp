package com.araujo.mvp;

import android.app.Activity;

/**
 * Created by jorge.araujo on 1/1/2016.
 */
public interface IBaseView {
    Activity getContext();

    IBasePresenter getPresenter();

    BaseViewHolder getViewHolder();
}
