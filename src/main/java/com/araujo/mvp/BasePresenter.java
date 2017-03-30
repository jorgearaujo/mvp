package com.araujo.mvp;

import android.os.Bundle;

import de.greenrobot.event.EventBus;

/**
 * Created by jorge.araujo on 1/2/2016.
 */
public class BasePresenter <VIEW extends IBaseView> implements IBasePresenter {

	private VIEW mView;
	private Bundle mBundle;
	private EventBus bus = EventBus.getDefault();

	public BasePresenter() {

	}

	@Override
	public void setView(IBaseView view) {
		mView = (VIEW)view;
	}

	@Override
	public void setBundle(Bundle bundle) {
		mBundle = bundle;
	}

	@Override
	public Bundle getBundle() {
		return mBundle;
	}

	@Override
	public VIEW getView() {
		return mView;
	}

	@Override
	public void onCreate() {
		getEventBus().register(this);
	}

	@Override
	public void onResume() {
	}

	@Override
	public void onDestroy() {
		getEventBus().unregister(this);
	}

	public EventBus getEventBus() {
		return bus;
	}

	protected void onEvent(Object object) {}
}
