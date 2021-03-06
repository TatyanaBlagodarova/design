package com.material.tblagodarova.design.ui;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import com.material.tblagodarova.design.R;
import com.material.tblagodarova.design.data.weather.WeatherJson;
import com.material.tblagodarova.design.ui.fragments.FragmentDrawer;
import com.material.tblagodarova.design.ui.fragments.HomeFragment;
import com.material.tblagodarova.design.ui.network.RestClientErrorHandler;
import com.material.tblagodarova.design.ui.network.RestClientManager;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.rest.RestService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import timber.log.Timber;

@EActivity
public class HomeActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private FragmentDrawer drawerFragment;
    private Toolbar mToolbar;
    public static final int PLACE_PICKER_REQUEST = 1;

    @RestService
    RestClientManager restClient; //Inject it
    @Bean
    RestClientErrorHandler restErrorHandler;

    @AfterInject
    void afterInject() {
        restClient.setRestErrorHandler(restErrorHandler);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupToolbar();
        setupSlidingMenu();
        displayView(0);
    }

    private void setupSlidingMenu() {
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Application");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        Timber.v("position" + drawerFragment.getSelectedValue(position));
        displayView(position);
    }


    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.hello_world);
                break;
            case 1:
                sendrestApirequest();
                break;
            case 2:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case 3:
                Intent intent2 = new Intent(this, GoogleSignInActivity_.class);
                startActivity(intent2);
                break;
            case 4:
                try {
                    launchPlace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    @Background
    public void sendrestApirequest() {
        WeatherJson obj = restClient.getWeather("Kharkiv", "ua");
        if (obj != null)
            Timber.v(obj.toString());
    }

    // Start Google Place Picker Code **************************************************************
    public void launchPlace() throws GooglePlayServicesNotAvailableException,
            GooglePlayServicesRepairableException {

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        Context context = getApplicationContext();
        Timber.v("launchPlace ");
        startActivityForResult(builder.build(context), PLACE_PICKER_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Timber.v("onActivityResult " + requestCode + "   " + resultCode);
        String badLocation = "That location is not valid for this app, please select a valid location";

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getApplicationContext());
                if (place.getPlaceTypes().contains(34)) {
                    if (place.getPlaceTypes().contains(9) || place.getPlaceTypes().contains(15) ||
                            place.getPlaceTypes().contains(38) ||
                            place.getPlaceTypes().contains(67) ||
                            place.getPlaceTypes().contains(79)) {

                        Toast.makeText(getApplicationContext(),
                                "You choosed " + place.getName(), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(),
                                badLocation, Toast.LENGTH_LONG).show();
                        try {
                            launchPlace();
                        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
