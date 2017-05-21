/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.scrachx.foodfacts.checker.ui.main;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.scrachx.foodfacts.checker.BuildConfig;
import com.scrachx.foodfacts.checker.R;
import com.scrachx.foodfacts.checker.data.network.model.State;
import com.scrachx.foodfacts.checker.ui.about.AboutFragment;
import com.scrachx.foodfacts.checker.ui.allergens.AllergensActivity;
import com.scrachx.foodfacts.checker.ui.base.BaseActivity;
import com.scrachx.foodfacts.checker.ui.custom.RoundedImageView;
import com.scrachx.foodfacts.checker.ui.history.HistoryFragment;
import com.scrachx.foodfacts.checker.ui.history_chart.HistoryChartFragment;
import com.scrachx.foodfacts.checker.ui.login.LoginActivity;
import com.scrachx.foodfacts.checker.ui.product.ProductActivity;
import com.scrachx.foodfacts.checker.ui.scanner.ScannerActivity;
import com.scrachx.foodfacts.checker.ui.search.SearchFragment;
import com.scrachx.foodfacts.checker.utils.PermissionUtils;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_view)
    DrawerLayout mDrawer;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    @BindView(R.id.tv_app_version)
    TextView mAppVersionTextView;

    SearchView mSearchView;
    private TextView mNameTextView;
    private TextView mEmailTextView;
    private RoundedImageView mProfileImageView;
    private ActionBarDrawerToggle mDrawerToggle;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchMenuItem.getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        MenuItemCompat.setOnActionExpandListener(searchMenuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

                // Not replace if no search has been done (no switch of fragment)
                if (currentFragment instanceof SearchFragment) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, SearchFragment.newInstance(), SearchFragment.TAG)
                            .commit();
                }

                return true;
            }
        });

        return true;
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentAbout = fragmentManager.findFragmentByTag(AboutFragment.TAG);
        Fragment fragmentSearch = fragmentManager.findFragmentByTag(SearchFragment.TAG);
        Fragment fragmentHistory = fragmentManager.findFragmentByTag(HistoryFragment.TAG);
        if (fragmentAbout == null && fragmentSearch == null) {
            super.onBackPressed();
        } else if(fragmentAbout != null){
            onFragmentDetached(AboutFragment.TAG);
        } else if(fragmentSearch != null){
            onFragmentDetached(SearchFragment.TAG);
        } else if(fragmentHistory != null){
            onFragmentDetached(HistoryFragment.TAG);
        }
    }

    @Override
    public void updateAppVersion() {
        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
        mAppVersionTextView.setText(version);
    }

    @Override
    public void updateUserName(String currentUserName) {
        mNameTextView.setText(currentUserName);
    }

    @Override
    public void updateUserEmail(String currentUserEmail) {
        mEmailTextView.setText(currentUserEmail);
    }

    @Override
    public void updateUserProfilePic(String currentUserProfilePicUrl) {
        //load profile pic url into ANImageView
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onFragmentAttached() {
        //if (mDrawer != null)
        //    mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
            //if (mDrawer != null)
            //    mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    @Override
    public void showAboutFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view, AboutFragment.newInstance(), AboutFragment.TAG)
                .commit();
    }

    @Override
    public void showSearchFragment() {
        mSearchView.setIconified(false);
        mSearchView.clearFocus();
    }

    @Override
    public void showAllergensActivity() {
        startActivity(AllergensActivity.getStartIntent(this));
    }

    @Override
    public void showHistoryFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view, HistoryFragment.newInstance(), HistoryFragment.TAG)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setupNavMenu();
        mPresenter.onNavMenuCreated();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, HistoryChartFragment.newInstance(), HistoryChartFragment.TAG)
                .commit();

    }

    void setupNavMenu() {
        View headerLayout = mNavigationView.getHeaderView(0);
        mProfileImageView = (RoundedImageView) headerLayout.findViewById(R.id.iv_profile_pic);
        mNameTextView = (TextView) headerLayout.findViewById(R.id.tv_name);
        mEmailTextView = (TextView) headerLayout.findViewById(R.id.tv_email);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        mDrawer.closeDrawer(GravityCompat.START);
                        switch (item.getItemId()) {
                            case R.id.nav_item_search:
                                mPresenter.onDrawerOptionSearchClick();
                                return true;
                            case R.id.nav_item_history:
                                mPresenter.onDrawerOptionHistoryClick();
                                return true;
                            case R.id.nav_item_allergens:
                                mPresenter.onDrawerOptionAllergensClick();
                                return true;
                            case R.id.nav_item_scan:
                                mPresenter.onDrawerOptionScanClick();
                                return true;
                            case R.id.nav_item_about:
                                mPresenter.onDrawerOptionAboutClick();
                                return true;
                            case R.id.nav_item_logout:
                                mPresenter.onDrawerOptionLogoutClick();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
    }

    @Override
    public void openScannerActivity() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CAMERA)) {
                new MaterialDialog.Builder(MainActivity.this)
                        .title(R.string.about)
                        .content(R.string.permission_camera)
                        .neutralText(R.string.txt_ok)
                        .show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, PermissionUtils.MY_PERMISSIONS_REQUEST_CAMERA);
            }
        } else {
            startActivity(ScannerActivity.getStartIntent(this));
        }
    }

    @Override
    public void onError(String message) {
        super.onError(getString(R.string.no_product_found) + " " +message);
    }

    @Override
    public void openPageProduct(State stateProduct) {
        Bundle args = new Bundle();
        args.putParcelable("state", stateProduct);
        startActivity(ProductActivity.getStartIntent(this, args));
    }

    @Override
    public void openLoginActivity() {
        startActivity(LoginActivity.getStartIntent(this));
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Bundle args = new Bundle();
            args.putString("query", query);
            if (EAN13CheckDigit.EAN13_CHECK_DIGIT.isValid(query) && (!query.substring(0, 3).contains("977") || !query.substring(0, 3).contains("978") || !query.substring(0, 3).contains("979"))) {
                mPresenter.loadProduct(query);
            } else {
                mSearchView.clearFocus();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, SearchFragment.newInstance(args), SearchFragment.TAG)
                        .commit();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionUtils.MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(ScannerActivity.getStartIntent(this));
                } else {
                    new MaterialDialog.Builder(this)
                            .title(R.string.permission_title)
                            .content(R.string.permission_denied)
                            .negativeText(R.string.txt_no)
                            .positiveText(R.string.txt_yes)
                            .onPositive((dialog, which) -> {
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                            })
                            .show();
                }
                break;
            }
        }
    }
}
