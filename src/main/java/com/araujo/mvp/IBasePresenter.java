package com.araujo.mvp;

import android.os.Bundle;

/**
 * Created by jorge.araujo on 1/2/2016.
 */
public interface IBasePresenter {
    void setView(IBaseView view);

    void setBundle(Bundle bundle);

    Bundle getBundle();

    IBaseView getView();

    void onCreate();
    void onResume();

    void onDestroy();
}
