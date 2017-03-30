package com.araujo.mvp;

import com.araujo.lightinject.di.BindingConfiguration;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Araújo on 30/07/2015.
 */
public abstract class BaseFragment<HOLDER extends BaseViewHolder, PRESENTER extends IBasePresenter> extends android.support.v4.app.Fragment implements IBaseView {

    private HOLDER mViewHolder;
    private PRESENTER mPresenter;
    private BaseActivity mActivity;

    public BaseFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            mViewHolder = (HOLDER) ((Class<?>) ((ParameterizedType) this.getClass().
                    getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        View view = inflater.inflate(mViewHolder.getLayout(), container, false);
        mViewHolder.generateViews(view);
        mPresenter.onCreate();
        return view;
    }

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<?> cl = (Class<?>) pt.getActualTypeArguments()[1];
        mPresenter = BindingConfiguration.get(cl);
        mPresenter.setView(this);
        mPresenter.setBundle(getArguments());
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    protected void log(String msg) {
        Log.d(this.getContext().getClass().getSimpleName(), msg);
    }

    public void onBackPressed() {
        mActivity.finish();
    }

    @Override
    public Activity getContext() {
        return mActivity;
    }

    @Override
    public PRESENTER getPresenter() {
        return mPresenter;
    }

    @Override
    public HOLDER getViewHolder() {
        return mViewHolder;
    }
}
