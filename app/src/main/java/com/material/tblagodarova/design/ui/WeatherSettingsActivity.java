package com.material.tblagodarova.design.ui;

import com.material.tblagodarova.design.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Switch;

/**
 * Created by tblagodarova on 7/17/15.
 */
@EActivity(R.layout.weather_settings)
public class WeatherSettingsActivity extends AppCompatActivity {

    @ViewById
    Switch allowNotificationSwitch;

    @ViewById
    EditText temperatureComfortableEditText;


}
