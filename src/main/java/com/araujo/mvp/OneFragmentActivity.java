package com.araujo.mvp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.View;

/**
 * Created by Araújo on 27/07/2015.
 */
public class OneFragmentActivity extends BaseActivity {

    private static final int REQUEST_CODE = 0;
    public static final String FRAGMENT_KEY = "base.FRAGMENT_KEY";
    public static final String BUNDLE_KEY = "base.BUNDLE_KEY";

    private BaseFragment mFragment;

    public static void startActivity(Activity context, Class fragmentClass) {
        Intent intent = new Intent(context, OneFragmentActivity.class);
        intent.putExtra(FRAGMENT_KEY, fragmentClass);
        context.startActivityForResult(intent, REQUEST_CODE);
    }

    public static void startActivity(Activity context, Class fragmentClass, Bundle bundle) {
        Intent intent = new Intent(context, OneFragmentActivity.class);
        intent.putExtra(FRAGMENT_KEY, fragmentClass);
        intent.putExtra(BUNDLE_KEY, bundle);
        context.startActivityForResult(intent, REQUEST_CODE);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void startActivity(Activity activity, Class fragmentClass, Bundle bundle, Pair<View, String>... pairs) {
        Intent intent = new Intent(activity, OneFragmentActivity.class);
        intent.putExtra(FRAGMENT_KEY, fragmentClass);
        intent.putExtra(BUNDLE_KEY, bundle);
        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(activity, pairs);
        activity.startActivityForResult(intent, REQUEST_CODE, transitionActivityOptions.toBundle());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_one_fragment);
        setFragment();
    }

    private void setFragment() {
        try {
            Class cl = (Class) getIntent().getExtras().get(FRAGMENT_KEY);
            mFragment = (BaseFragment) cl.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, mFragment)
                    .commit();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onBackPressed() {
        mFragment.onBackPressed();
    }
}