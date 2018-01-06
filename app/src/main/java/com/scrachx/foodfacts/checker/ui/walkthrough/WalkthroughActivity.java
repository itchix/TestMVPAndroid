package com.scrachx.foodfacts.checker.ui.walkthrough;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.scrachx.foodfacts.checker.R;
import com.scrachx.foodfacts.checker.ui.base.BaseActivity;
import com.scrachx.foodfacts.checker.ui.login.LoginActivity;
import com.scrachx.foodfacts.checker.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by scots on 06/05/2017.
 */

public class WalkthroughActivity extends BaseActivity implements WalkthroughMvpView {

    @Inject
    WalkthroughPresenter<WalkthroughMvpView> mPresenter;

    private ViewPager mViewPager;
    private View mIndicator1;
    private View mIndicator2;
    private View mIndicator3;
    private View mIndicator4;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, WalkthroughActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_walkthrough);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(WalkthroughActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        mIndicator1 = findViewById(R.id.indicator1);
        mIndicator2 = findViewById(R.id.indicator2);
        mIndicator3 = findViewById(R.id.indicator3);
        mIndicator4 = findViewById(R.id.indicator4);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new WizardPageChangeListener());
        mViewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.walkthrough2_viewpager_margin));
        mViewPager.setOffscreenPageLimit(4);

        View image = findViewById(R.id.activity_walkthrough);
        String url = "https://s3-us-west-2.amazonaws.com/material-ui-template/walkthrough/style-14/Welcome-14-960.jpg";
        String urlThumb = "https://s3-us-west-2.amazonaws.com/material-ui-template/walkthrough/style-14/Welcome-14-960-thumb.jpg";
        loadImageRequest(image, url, urlThumb);

        updateIndicators(0);
    }

    private void loadImageRequest(final View bg, String url, String urlThumb) {
        RequestBuilder<Drawable> thumbnailRequest = Glide
                .with(this)
                .load(urlThumb);

        Glide.with(this)
                .load(url)
                .thumbnail(thumbnailRequest)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        bg.setBackground(resource);
                    }
                });
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private int WIZARD_PAGES_COUNT = 4;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new WalkthroughFragment(position);
        }

        @Override
        public int getCount() {
            return WIZARD_PAGES_COUNT;
        }

    }

    private class WizardPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int position) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageSelected(int position) {
            updateIndicators(position);
        }

    }

    public void updateIndicators(int position) {
        switch (position) {
            case 0:
                mIndicator1.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot));
                mIndicator2.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot_grey));
                mIndicator3.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot_grey));
                mIndicator4.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot_grey));
                break;
            case 1:
                mIndicator1.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot_grey));
                mIndicator2.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot));
                mIndicator3.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot_grey));
                mIndicator4.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot_grey));
                break;
            case 2:
                mIndicator1.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot_grey));
                mIndicator2.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot_grey));
                mIndicator3.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot));
                mIndicator4.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot_grey));
                break;
            case 3:
                mIndicator1.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot_grey));
                mIndicator2.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot_grey));
                mIndicator3.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot_grey));
                mIndicator4.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_dot));
                break;
        }
    }

    @OnClick(R.id.sign_in_button)
    void onSignInClick(View v) {
        mPresenter.onSignInClick();
    }

    @OnClick(R.id.skip_button)
    void onSkipClick(View v) {
        mPresenter.onSkipClick();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void openMainActivity() {
        startActivity(MainActivity.getStartIntent(this));
        finish();
    }

    @Override
    public void openLoginActivity() {
        startActivity(LoginActivity.getStartIntent(this));
        finish();
    }
}
