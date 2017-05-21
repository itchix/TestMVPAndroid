package com.scrachx.foodfacts.checker.ui.product;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.scrachx.foodfacts.checker.R;
import com.scrachx.foodfacts.checker.data.network.model.Product;
import com.scrachx.foodfacts.checker.data.network.model.State;
import com.scrachx.foodfacts.checker.ui.base.BaseActivity;
import com.scrachx.foodfacts.checker.ui.product.ingredients.IngredientsProductFragment;
import com.scrachx.foodfacts.checker.ui.product.nutrition.NutritionProductFragment;
import com.scrachx.foodfacts.checker.ui.product.nutritioninfo.NutritionInfoProductFragment;
import com.scrachx.foodfacts.checker.ui.product.summary.SummaryProductFragment;
import com.scrachx.foodfacts.checker.ui.scanner.ScannerActivity;
import com.scrachx.foodfacts.checker.utils.AllergensUtils;
import com.scrachx.foodfacts.checker.utils.PermissionUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * Created by scots on 08/05/2017.
 */

public class ProductActivity extends BaseActivity implements ProductMvpView {

    @Inject
    ProductPresenter<ProductMvpView> mPresenter;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    private AlertDialog mDialog;

    private ShareActionProvider mShareActionProvider;

    private State mState;

    public static Intent getStartIntent(Context context, Bundle args) {
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra("bundle", args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        String url = " " + getString(R.string.website_product) + mState.getProduct().getCode();
        if (mState.getProduct().getUrl() != null) {
            url = " " + mState.getProduct().getUrl();
        }
        shareIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.msg_share) + url);
        shareIntent.setType("text/plain");
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }

        return true;
    }

    @Override
    protected void setUp() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        if (ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, READ_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)) {
                new MaterialDialog.Builder(this)
                        .title(R.string.about)
                        .content(R.string.permission_storage)
                        .neutralText(R.string.txt_ok)
                        .onNeutral((dialog, which) -> ActivityCompat.requestPermissions(ProductActivity.this, new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PermissionUtils.MY_PERMISSIONS_REQUEST_STORAGE))
                        .show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PermissionUtils.MY_PERMISSIONS_REQUEST_STORAGE);
            }
        }
        mState = (State) getIntent().getBundleExtra("bundle").getParcelable("state");
        checkAllergens();
    }

    private void checkAllergens() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogForm = inflater.inflate(R.layout.dialog_allergens, null, false);

        Button buttonOk = (Button) dialogForm.findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        ArrayList<AllergensItem> arrayOfAllergens = new ArrayList<AllergensItem>();
        AllergensViewAdapter adapter = new AllergensViewAdapter(this, arrayOfAllergens);
        ListView listView = (ListView) dialogForm.findViewById(R.id.list_view_allergens);
        listView.setAdapter(adapter);

        Product product = mState.getProduct();
        if(mPresenter.getDataManager().getAllergensEggs()) {
            if(AllergensUtils.checkForAllergens("eggs", product)) {
                adapter.add(new AllergensItem(getString(R.string.txt_allergens_eggs), getDrawable(R.drawable.ic_egg)));
            }
        }
        if(mPresenter.getDataManager().getAllergensFish()) {
            if(AllergensUtils.checkForAllergens("fish", product)) {
                adapter.add(new AllergensItem(getString(R.string.txt_allergens_fish), getDrawable(R.drawable.ic_fish)));
            }
        }
        if(mPresenter.getDataManager().getAllergensMilk()) {
            if(AllergensUtils.checkForAllergens("milk", product)) {
                adapter.add(new AllergensItem(getString(R.string.txt_allergens_milk), getDrawable(R.drawable.ic_milk)));
            }
        }
        if(mPresenter.getDataManager().getAllergensGluten()) {
            if(AllergensUtils.checkForAllergens("gluten", product)) {
                adapter.add(new AllergensItem(getString(R.string.txt_allergens_gluten), getDrawable(R.drawable.ic_gluten)));
            }
        }
        if(mPresenter.getDataManager().getAllergensNuts()) {
            if(AllergensUtils.checkForAllergens("nuts", product)) {
                adapter.add(new AllergensItem(getString(R.string.txt_allergens_nuts), getDrawable(R.drawable.ic_peanut)));
            }
        }
        if(mPresenter.getDataManager().getAllergensSoy()) {
            if(AllergensUtils.checkForAllergens("soybeans", product)) {
                adapter.add(new AllergensItem(getString(R.string.txt_allergens_eggs), getDrawable(R.drawable.ic_soybean)));
            }
        }
        if(mPresenter.getDataManager().getAllergensPalmOil()) {
            if(AllergensUtils.checkForAllergens("palmoil", product)) {
                adapter.add(new AllergensItem(getString(R.string.txt_allergens_palm_oil), getDrawable(R.drawable.ic_palm)));
            }
        }

        if(adapter.getCount() > 0 ) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(dialogForm);
            builder.create();
            mDialog = builder.create();
            mDialog.show();
        }
    }


    private void setupViewPager(ViewPager viewPager) {
        String[] menuTitles = getResources().getStringArray(R.array.nav_drawer_items_product);
        ProductFragmentPagerAdapter adapterResult = new ProductFragmentPagerAdapter(getSupportFragmentManager());
        adapterResult.addFragment(new SummaryProductFragment(), menuTitles[0]);
        adapterResult.addFragment(new IngredientsProductFragment(), menuTitles[1]);
        adapterResult.addFragment(new NutritionProductFragment(), menuTitles[2]);
        adapterResult.addFragment(new NutritionInfoProductFragment(), menuTitles[3]);
        viewPager.setAdapter(adapterResult);
        viewPager.setOffscreenPageLimit(4);
    }

    @OnClick(R.id.button_scan)
    protected void OnScan() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                new MaterialDialog.Builder(this)
                        .title(R.string.about)
                        .content(R.string.permission_camera)
                        .neutralText(R.string.txt_ok)
                        .onNeutral((dialog, which) -> ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PermissionUtils.MY_PERMISSIONS_REQUEST_CAMERA))
                        .show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PermissionUtils.MY_PERMISSIONS_REQUEST_CAMERA);
            }
        } else {
            mPresenter.onScannerClick();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionUtils.MY_PERMISSIONS_REQUEST_CAMERA:
            case PermissionUtils.MY_PERMISSIONS_REQUEST_STORAGE: {
                if (grantResults.length <= 0 || grantResults[0] != PERMISSION_GRANTED) {
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
            }
        }
    }

    @Override
    public void openScannerActivity() {
        startActivity(ScannerActivity.getStartIntent(this));
        finish();
    }
}
